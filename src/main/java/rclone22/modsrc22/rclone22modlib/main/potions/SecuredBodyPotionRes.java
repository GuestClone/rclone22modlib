package rclone22.modsrc22.rclone22modlib.main.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.potion.IPotionMethodsRes;
import rclone22.modsrc22.rclone22modlib.api.potion.RemoveBadEffects;

import java.util.ArrayList;
import java.util.List;

import static rclone22.modsrc22.rclone22modlib.main.Constant.MODID;
import static toughasnails.util.RenderUtils.drawTexturedModalRect;

public class SecuredBodyPotionRes extends Potion implements IPotionMethodsRes
{

    public SecuredBodyPotionRes(boolean isBadEffectIn, int liquidColorIn, String modid, String name) {
        super(isBadEffectIn, liquidColorIn);
        setRegistryName(modid, name);
        setPotionName("potion."+name);
    }

    @Override
    public boolean isPotionAbsoluteResistance(){
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        return super.getStatusIconIndex();
    }

    /*
    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.getTextureManager().bindTexture(new ResourceLocation(MODID, "textures/gui/potion_icons.png"));
        drawTexturedModalRect(x + 6, y + 7, 0, 0, 18, 18);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        mc.getTextureManager().bindTexture(new ResourceLocation(MODID, "textures/gui/potion_icons.png"));
        drawTexturedModalRect(x + 3, y + 3, 0, 0, 18, 18);
    }
     */

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
    }



}
