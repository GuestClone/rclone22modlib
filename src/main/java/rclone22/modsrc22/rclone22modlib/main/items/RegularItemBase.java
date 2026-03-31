package rclone22.modsrc22.rclone22modlib.main.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import rclone22.modsrc22.rclone22modlib.client.IHasModel;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.Main;

public class RegularItemBase extends Item implements IHasModel {

    public RegularItemBase(String name, CreativeTabs tabs, int maxStackSize) {
        super();
        setUnlocalizedName(name);
        setRegistryName(Constant.MODID, name);
        setCreativeTab(tabs);
        setMaxStackSize(maxStackSize);


    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
