package rclone22.modsrc22.rclone22modlib.api.item.harvestblock;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import rclone22.modsrc22.rclone22modlib.main.oredict.ItemHarvestOreDict;

import javax.annotation.Nullable;

public class CanHarvestUtil
{

    public static final String canHarvestBlockTool = "canHarvestBlockTool";

    public static final String canEnchantHarvestBlockTool = "canEnchantHarvestBlockTool";

    public static boolean canForceHarvest(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) return false;

        if (getNbtTagString(stack, canHarvestBlockTool) || getNbtTagString(stack, canEnchantHarvestBlockTool)){
            return true;
        }

        int[] oreIDs = OreDictionary.getOreIDs(stack);
        for (int id : oreIDs) {
            String oreName = OreDictionary.getOreName(id);
            if (ItemHarvestOreDict.isAbleToHarvestOreDictFinal.equals(oreName)) {
                return true;
            }
        }

        Item item = stack.getItem();
        if (item instanceof ISetCanHarvest.ICustomHarvestCheck) {
            return ((ISetCanHarvest.ICustomHarvestCheck) item).canAlwaysHarvest(stack);
        }

        return false;
    }


    private static boolean getNbtTagString(ItemStack stack, String nbt) {
        if (stack == null || stack.isEmpty() || !stack.hasTagCompound()) {
            return false;
        }

        NBTTagCompound tag = stack.getTagCompound();
        return tag != null && tag.getBoolean(nbt);
    }

}
