package rclone22.modsrc22.rclone22modlib.main.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rclone22.modsrc22.rclone22modlib.api.item.IIsInvulnerableUtil;
import rclone22.modsrc22.rclone22modlib.api.item.harvestlevel.IGetSetHarvestLevel;

public class ItemHarvestLevel extends Enchantment implements IGetSetHarvestLevel.IEnchantHarvestLevel
{

    public ItemHarvestLevel(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots, String modid, String name) {
        super(rarityIn, typeIn, slots);
        this.setRegistryName(modid, name);
        this.setName(name);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof Item;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getHarvestLevelFromEnchant(ItemStack stack, int enchantLevel) {
        return enchantLevel;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem() instanceof Item;
    }


}
