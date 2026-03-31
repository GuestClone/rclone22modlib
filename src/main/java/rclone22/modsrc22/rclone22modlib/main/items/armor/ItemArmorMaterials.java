package rclone22.modsrc22.rclone22modlib.main.items.armor;

import net.minecraft.item.ItemArmor;
import rclone22.modsrc22.rclone22modlib.main.Constant;

public class ItemArmorMaterials
{

    private static final Integer maxInt = Integer.MAX_VALUE;

    private static final Integer intInt = 1;

    public static final ItemArmor.ArmorMaterial ItemArmorMaterials =
            net.minecraftforge.common.util.EnumHelper.addArmorMaterial(
                    "META_HELMET",                      // Material name
                    Constant.MODID+":textures/models/armor/meta_helmet_layer_1.png", // Armor texture path (used for armor in-game)
                    intInt,                                  // Durability factor (helmet base durability * 15)
                    new int[]{100 /*helmet*/,
                            100/*chestplate*/,
                            100/*leggings*/,
                            100/*boots*/},                // Damage reduction amounts
                    maxInt,                                 // Enchantability
                    net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_IRON, // Equip sound
                    100.0F                                 // Toughness
            );

}
