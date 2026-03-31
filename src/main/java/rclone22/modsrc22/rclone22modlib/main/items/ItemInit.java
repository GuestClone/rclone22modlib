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
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.items.armor.ItemArmorMaterials;
import rclone22.modsrc22.rclone22modlib.main.items.armor.MetaHelmet;
import rclone22.modsrc22.rclone22modlib.main.registryutils.RegistryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemInit
{

    public static final List<Item> ITEMS = new ArrayList<Item>();

    /// renderIndex 1 = helmet, chesplate, feet
    /// renderIndex 2 = leggings
    public static final Item META_HELMET = register(new MetaHelmet("meta_helmet", ItemArmorMaterials.ItemArmorMaterials, CreativeTabs.COMBAT, 1, EntityEquipmentSlot.HEAD));

    public static final Item ITEM_WEARABLE_SBP = register(new ItemWearingSBP("item_wearable_sbp", CreativeTabs.COMBAT, 1));

    public static final Item ITEM_BIG_BATTERY = register(new ItemBigBattery("item_big_battery", CreativeTabs.MISC, 1));


    private static <T extends Item> T register(T item) {
        ITEMS.add(item);
        return item;
    }






}
