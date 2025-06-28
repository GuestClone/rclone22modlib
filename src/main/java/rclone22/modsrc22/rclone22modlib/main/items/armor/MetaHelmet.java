package rclone22.modsrc22.rclone22modlib.main.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import rclone22.modsrc22.rclone22modlib.client.IHasModel;
import rclone22.modsrc22.rclone22modlib.main.Main;

public class MetaHelmet extends ItemArmor implements IHasModel
{

    public MetaHelmet(String name, String modid, ArmorMaterial materialIn, CreativeTabs tabs, int rendIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, rendIndexIn,equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(modid, name);
        setCreativeTab(tabs);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
