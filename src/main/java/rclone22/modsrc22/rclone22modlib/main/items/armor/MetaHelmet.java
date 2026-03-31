package rclone22.modsrc22.rclone22modlib.main.items.armor;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.BaublesContainer;
import baubles.api.cap.BaublesContainerProvider;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.item.IIsInvulnerableUtil;
import rclone22.modsrc22.rclone22modlib.client.IHasModel;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.Main;
import rclone22.modsrc22.rclone22modlib.main.items.CheckItemUtil;
import rclone22.modsrc22.rclone22modlib.main.items.ItemInit;
import rclone22.modsrc22.rclone22modlib.main.potions.PotionInit;
import rclone22.modsrc22.rclone22modlib.main.potions.PotionUtil;

import javax.annotation.Nullable;


public class MetaHelmet extends ItemArmorBase implements IHasModel
{

    public MetaHelmet(String name, ArmorMaterial materialIn, CreativeTabs tabs, int rendIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, tabs, rendIndexIn, equipmentSlotIn);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public boolean hasOverlay(@Nullable ItemStack stack)
    {
        return true;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public String getArmorTexture(@Nullable ItemStack stack, @Nullable Entity entity,@Nullable EntityEquipmentSlot slot, @Nullable String type) {
        return (Constant.MODID+":textures/models/armor/meta_helmet_layer_1.png");
    }


}
