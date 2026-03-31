package rclone22.modsrc22.rclone22modlib.main.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.ISetCanHarvest;
import rclone22.modsrc22.rclone22modlib.api.item.harvestlevel.IGetSetHarvestLevel;

import javax.annotation.Nullable;

public class ItemHarvestEnchant extends Enchantment implements ISetCanHarvest.ICustomHarvestCheckEnchant
{

    public ItemHarvestEnchant(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots, String modid, String name) {
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
    public boolean canAlwaysHarvest(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@Nullable ItemStack stack)
    {
        return false;
    }

}