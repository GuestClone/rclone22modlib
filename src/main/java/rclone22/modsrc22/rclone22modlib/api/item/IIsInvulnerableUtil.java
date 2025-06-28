package rclone22.modsrc22.rclone22modlib.api.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IIsInvulnerableUtil
{

    interface IIsEnchantItemInvul
    {

        /** Use this if you want your enchantment to apply the item invulnerability effect
         * (Makes item resistant to even /kill command)
         * Note: enchantment must be min and max level of 1
         * */
        boolean isEnchInvulnerable(ItemStack stack);

    }

    interface IIsItemInvulnerable
    {

        /** Makes item (both item and entityItem) invulnerable to all damage sources and despawn
         * */
        boolean isItemInvulnerable(ItemStack stack);

    }

}
