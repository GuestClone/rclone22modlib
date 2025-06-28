package rclone22.modsrc22.rclone22modlib.main.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import rclone22.modsrc22.rclone22modlib.main.Constant;

import java.util.ArrayList;
import java.util.List;

public class EnchantInit
{

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();

    public static final Enchantment ITEM_INVUL_ENCH = new ItemInvulnerability(Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.ALL,
            EnchUtil.ALL, Constant.MODID, "item_invul_ench");

    static {
        EnchantInit.ENCHANTMENTS.add(ITEM_INVUL_ENCH);
    }

}
