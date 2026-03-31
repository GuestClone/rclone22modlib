package rclone22.modsrc22.rclone22modlib.main.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import rclone22.modsrc22.rclone22modlib.client.IHasModel;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.Main;

public class ItemArmorBase extends ItemArmor implements IHasModel {

    public ItemArmorBase(String name, ArmorMaterial materialIn, CreativeTabs tabs, int rendIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, rendIndexIn,equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(Constant.MODID, name);
        setCreativeTab(tabs);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
