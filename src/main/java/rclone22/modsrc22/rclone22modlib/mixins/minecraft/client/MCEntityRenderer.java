package rclone22.modsrc22.rclone22modlib.mixins.minecraft.client;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GLContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import rclone22.modsrc22.rclone22modlib.main.potions.PotionInit;

@Mixin(value = EntityRenderer.class, remap = false)
@SideOnly(Side.CLIENT)
public abstract class MCEntityRenderer {

    @Shadow
    public abstract void setupFogColor(boolean black);

    @Final
    @Shadow
    private Minecraft mc;

    @Shadow
    private boolean cloudFog;

    @Shadow
    private float farPlaneDistance;


    /**
     * @author b
     * @reason b
     */
    @Overwrite(remap = false)
    private void setupFog(int startCoords, float partialTicks)
    {
        Entity entity = this.mc.getRenderViewEntity();
        this.setupFogColor(false);
        GlStateManager.glNormal3f(0.0F, -1.0F, 0.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        IBlockState iblockstate = ActiveRenderInfo.getBlockStateAtEntityViewpoint(this.mc.world, entity, partialTicks);
        float hook = net.minecraftforge.client.ForgeHooksClient.getFogDensity((EntityRenderer)(Object)this, entity, iblockstate, partialTicks, 0.1F);
        if (hook >= 0) GlStateManager.setFogDensity(hook);
        else
        if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isPotionActive(MobEffects.BLINDNESS))
        {
            float f1 = 5.0F;
            int i = ((EntityLivingBase)entity).getActivePotionEffect(MobEffects.BLINDNESS).getDuration();

            if (i < 20)
            {
                f1 = 5.0F + (this.farPlaneDistance - 5.0F) * (1.0F - (float)i / 20.0F);
            }

            GlStateManager.setFog(GlStateManager.FogMode.LINEAR);

            if (startCoords == -1)
            {
                GlStateManager.setFogStart(0.0F);
                GlStateManager.setFogEnd(f1 * 0.8F);
            }
            else
            {
                GlStateManager.setFogStart(f1 * 0.25F);
                GlStateManager.setFogEnd(f1);
            }

            if (GLContext.getCapabilities().GL_NV_fog_distance)
            {
                GlStateManager.glFogi(34138, 34139);
            }
        }
        else if (this.cloudFog)
        {
            GlStateManager.setFog(GlStateManager.FogMode.EXP);
            GlStateManager.setFogDensity(0.1F);
        }
        else if (iblockstate.getMaterial() == Material.WATER)
        {
            GlStateManager.setFog(GlStateManager.FogMode.EXP);

            if (entity instanceof EntityLivingBase)
            {
                if (((EntityLivingBase)entity).isPotionActive(MobEffects.WATER_BREATHING))
                {
                    GlStateManager.setFogDensity(0.01F);
                }
                else if (((EntityLivingBase)entity).isPotionActive(PotionInit.WATERVISION)) {
                    GlStateManager.setFogDensity(0.0F);
                }
                else
                {
                    GlStateManager.setFogDensity(0.1F - (float) EnchantmentHelper.getRespirationModifier((EntityLivingBase)entity) * 0.03F);
                }
            }
            else
            {
                GlStateManager.setFogDensity(0.1F);
            }
        }
        else if (iblockstate.getMaterial() == Material.LAVA)
        {
            GlStateManager.setFog(GlStateManager.FogMode.EXP);


            if (entity instanceof EntityLivingBase &&
                iblockstate.getMaterial() == Material.LAVA && ((EntityLivingBase)entity).isPotionActive(PotionInit.WATERVISION)) {

                GlStateManager.setFogDensity(0.0F);
            } else
            {
                GlStateManager.setFogDensity(2.0F);
            }
        }
        else
        {
            float f = this.farPlaneDistance;
            GlStateManager.setFog(GlStateManager.FogMode.LINEAR);

            if (startCoords == -1)
            {
                GlStateManager.setFogStart(0.0F);
                GlStateManager.setFogEnd(f);
            }
            else
            {
                GlStateManager.setFogStart(f * 0.75F);
                GlStateManager.setFogEnd(f);
            }

            if (GLContext.getCapabilities().GL_NV_fog_distance)
            {
                GlStateManager.glFogi(34138, 34139);
            }

            if (this.mc.world.provider.doesXZShowFog((int)entity.posX, (int)entity.posZ) || this.mc.ingameGUI.getBossOverlay().shouldCreateFog())
            {
                GlStateManager.setFogStart(f * 0.05F);
                GlStateManager.setFogEnd(Math.min(f, 192.0F) * 0.5F);
            }
            net.minecraftforge.client.ForgeHooksClient.onFogRender((EntityRenderer)(Object)this, entity, iblockstate, partialTicks, startCoords, f);
        }

        GlStateManager.enableColorMaterial();
        GlStateManager.enableFog();
        GlStateManager.colorMaterial(1028, 4608);
    }



}
