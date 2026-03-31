package rclone22.modsrc22.rclone22modlib.main.enchantments;

import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.Arrays;
import java.util.stream.Stream;

public class EnchUtil
{



    public static final EntityEquipmentSlot[] ARMOR = new EntityEquipmentSlot[] {
            EntityEquipmentSlot.HEAD,
            EntityEquipmentSlot.CHEST,
            EntityEquipmentSlot.LEGS,
            EntityEquipmentSlot.FEET
    };

    public static final EntityEquipmentSlot[] HANDS = new EntityEquipmentSlot[] {
            EntityEquipmentSlot.MAINHAND,
            EntityEquipmentSlot.OFFHAND
    };

    public static final EntityEquipmentSlot[] ALL = Stream.concat(
            Arrays.stream(ARMOR),
            Arrays.stream(HANDS)
    ).toArray(EntityEquipmentSlot[]::new);

}
