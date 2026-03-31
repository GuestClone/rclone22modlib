package rclone22.modsrc22.rclone22modlib.api.item.harvestlevel;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class NBTHarvestUtil
{

    public static final String modifyHarvestLevel = "modifyHarvestLevel";

    public static final String modifyEnchantHarvestLevel = "modifyEnchantHarvestLevel";

    /// -2 Harvest Level of NBT will be automatically removed by event
    public static int doGetCustomHarvestLevel(ItemStack stack, String toolClass) {
        if (stack == null || stack.isEmpty()) {
            return -1;
        }

        Item item = stack.getItem();


        if (item instanceof IGetSetHarvestLevel.IItemHarvestLevel) {
            int level = ((IGetSetHarvestLevel.IItemHarvestLevel) item).getHarvestLevel(stack, toolClass);
            if (level >= 0) {
                return level;
            }
        }


        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null) {

            if (tag.hasKey(modifyHarvestLevel)) {
                return tag.getInteger(modifyHarvestLevel);
            }


            if (tag.hasKey(modifyHarvestLevel, Constants.NBT.TAG_COMPOUND)) {
                NBTTagCompound sub = tag.getCompoundTag(modifyHarvestLevel);
                if (sub.hasKey(toolClass)) {
                    return sub.getInteger(toolClass);
                }
            }
        }

        if (tag != null) {

            if (tag.hasKey(modifyEnchantHarvestLevel)) {
                return tag.getInteger(modifyEnchantHarvestLevel);
            }


            if (tag.hasKey(modifyEnchantHarvestLevel, Constants.NBT.TAG_COMPOUND)) {
                NBTTagCompound sub = tag.getCompoundTag(modifyEnchantHarvestLevel);
                if (sub.hasKey(toolClass)) {
                    return sub.getInteger(toolClass);
                }
            }
        }

        return -1;
    }

}
