package rclone22.modsrc22.rclone22modlib.main.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.main.Constant;


public class CheckItemUtil
{

    /** Yes thats alot of safety checks in a single class */


    public static boolean isWearingSpecificArmor(EntityLivingBase entity, EntityEquipmentSlot slot, Item itemToCheck) {
        if (entity == null) return false;
        if (slot == null) return false;
        if (itemToCheck == null) return false;

        ItemStack armorStack = entity.getItemStackFromSlot(slot);

        if (armorStack.isEmpty()) return false;

        return armorStack.getItem() == itemToCheck;

    }

    public static boolean isWearingItemByModId(EntityLivingBase entity, EntityEquipmentSlot slot, String modid, String itemName) {
        if (entity == null || slot == null || modid == null || itemName == null) {
            return false;
        }

        Item targetItem = CheckItemUtil.tryLoadItem(modid, itemName);
        if (targetItem == null) {
            return false;
        }

        ItemStack stack = entity.getItemStackFromSlot(slot);

        if (stack.isEmpty()) return false;

        return stack.getItem() == targetItem;
    }


    /**
     * */
    public static Item tryLoadItem(String modid, String registryName) {
        if (ModChecker.isNullOrBlank(modid)) {
            return null;
        }

        if (!ModChecker.isModPresent(modid)) {
            return null;
        }

        ResourceLocation rl;
        try {
            rl = new ResourceLocation(modid, registryName);
        } catch (Exception e) {
            System.err.println("[ModCompat:" + Constant.MODID + "]: Optional Dependant Item not loaded = " + modid + ":" + registryName);
            e.printStackTrace();
            return null;
        }

        if (!Item.REGISTRY.containsKey(rl)) {
            return null;
        }

        return Item.REGISTRY.getObject(rl);
    }

}
