package rclone22.modsrc22.rclone22modlib.api.item.harvestlevel;

import net.minecraft.item.ItemStack;

public interface IGetSetHarvestLevel {


    interface IEnchantHarvestLevel
    {

        /** Use this if you want your enchantment to apply the item invulnerability effect
         * (Makes item resistant to even /kill command)
         * Note: enchantment must be min and max level of 1
         * */
        int getHarvestLevelFromEnchant(ItemStack stack, int enchantLevel);

    }

    interface IItemHarvestLevel
    {

        /**
         * */
        int getHarvestLevel(ItemStack stack, String toolClass);

    }


}
