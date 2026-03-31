package rclone22.modsrc22.rclone22modlib.main.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.registryutils.RegistryUtils;

import java.util.ArrayList;
import java.util.List;

public class EnchantInit
{

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();

    public static final Enchantment ITEM_INVUL_ENCH = register(new ItemInvulnerability(
            Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.ALL, EnchUtil.ALL,
            Constant.MODID, "item_invul_ench"
    ));

    public static final Enchantment HARVEST_LVL_ENCH = register(new ItemHarvestLevel(
            Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL, EnchUtil.ALL,
            Constant.MODID, "harvest_lvl_ench"
    ));

    public static final Enchantment HARVEST_ANY_ENCH = register(new ItemHarvestEnchant(
            Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.ALL, EnchUtil.ALL,
            Constant.MODID, "harvest_any_ench"
    ));

    public static final Enchantment ENCH_BAUBLE_SBP = register(new EnchantBaubleSBP(
            Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.ALL, EnchUtil.ALL,
            Constant.MODID, "enchant_bauble_sbp"
    ));

    private static <T extends Enchantment> T register(T enchantment) {
        ENCHANTMENTS.add(enchantment);
        return enchantment;
    }



}
