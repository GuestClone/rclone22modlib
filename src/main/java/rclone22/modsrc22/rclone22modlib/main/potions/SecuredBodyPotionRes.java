package rclone22.modsrc22.rclone22modlib.main.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rclone22.modsrc22.rclone22modlib.api.potion.IPotionMethodsRes;

import javax.annotation.Nullable;

import static rclone22.modsrc22.rclone22modlib.main.Constant.MODID;

public class SecuredBodyPotionRes extends PotionBase implements IPotionMethodsRes
{

    public SecuredBodyPotionRes(boolean isBadEffectIn, int liquidColorIn, String name) {
        super(isBadEffectIn, liquidColorIn, name);
    }

    @Override
    public boolean isPotionAbsoluteResistance(){
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        return super.getStatusIconIndex();
    }

    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, @Nullable PotionEffect effect, Minecraft mc) {
        mc.getTextureManager().bindTexture(new ResourceLocation(MODID, "textures/gui/potions/securebody_matter_2.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, @Nullable PotionEffect effect, Minecraft mc, float alpha) {
        mc.getTextureManager().bindTexture(new ResourceLocation(MODID, "textures/gui/potions/securebody_matter_2.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }


    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public void performEffect(@Nullable EntityLivingBase entityLivingBaseIn, int amplifier)
    {

    }



}
