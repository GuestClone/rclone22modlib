package rclone22.modsrc22.rclone22modlib.main.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.items.armor.ItemArmorMaterials;
import rclone22.modsrc22.rclone22modlib.main.items.armor.MetaHelmet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemInit
{

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item META_HELMET = new MetaHelmet("meta_helmet", Constant.MODID,
            ItemArmorMaterials.ItemArmorMaterials, CreativeTabs.COMBAT, 1, EntityEquipmentSlot.HEAD);

    static {
        ItemInit.ITEMS.add(META_HELMET);
    }




}
