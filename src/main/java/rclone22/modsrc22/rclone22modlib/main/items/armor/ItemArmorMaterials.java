package rclone22.modsrc22.rclone22modlib.main.items.armor;

import net.minecraft.item.ItemArmor;
import rclone22.modsrc22.rclone22modlib.main.Constant;

public class ItemArmorMaterials
{

    private static final Integer maxInt = 2147483647;

    public static final ItemArmor.ArmorMaterial ItemArmorMaterials =
            net.minecraftforge.common.util.EnumHelper.addArmorMaterial(
                    "META_HELMET",                      // Material name
                    Constant.MODID+":textures/models/armor/gasmask_1.png", // Armor texture path (used for armor in-game)
                    maxInt,                                  // Durability factor (helmet base durability * 15)
                    new int[]{100 /*helmet*/,
                            100/*chestplate*/,
                            100/*leggings*/,
                            100/*boots*/},                // Damage reduction amounts
                    maxInt,                                 // Enchantability
                    net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_IRON, // Equip sound
                    100.0F                                 // Toughness
            );

}
