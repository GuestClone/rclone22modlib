package rclone22.modsrc22.rclone22modlib.main.oredict;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import rclone22.modsrc22.rclone22modlib.main.items.ItemInit;

public class ItemHarvestOreDict
{


    public static final String isAbleToHarvest = "isAbleToHarvest";


    public static final String isAbleToHarvestOreDictFinal = OreDictInit.item+isAbleToHarvest;


    public static void registerOresItemHarvest() {
        registerOreDictForItemHarvest(

        );
    }

    public static void registerOreDictItemHarvest(String prefix, String baseName, ItemStack... stacks) {
        if (prefix == null || baseName == null || stacks == null) return;

        String oreName = prefix + baseName;
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                OreDictionary.registerOre(oreName, stack);
            }
        }
    }

    public static void registerOreDictForItemHarvest(ItemStack... stacks) {
        if (stacks == null) return;

        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
               registerOreDictItemHarvest(OreDictInit.item, isAbleToHarvest, stack);
            }
        }
    }

}
