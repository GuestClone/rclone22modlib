package rclone22.modsrc22.rclone22modlib.main.oredict;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import rclone22.modsrc22.rclone22modlib.main.items.ItemInit;

public class ItemOreDict
{

    public static final String item = "item";
    public static final String isInvulOreDict = "IsInvulOreDict";


    public static final String isInvulOreDictFinal = item+isInvulOreDict; /// itemIsInvulOreDict

    public static void registerOres() {
        registerOreDictForItemInvul(new ItemStack(ItemInit.META_HELMET));
    }

    public static void registerOreDict(String prefix, String baseName, ItemStack... stacks) {
        if (prefix == null || baseName == null || stacks == null) return;

        String oreName = prefix + baseName;
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                OreDictionary.registerOre(oreName, stack);
            }
        }
    }

    /// Use this if you dont want to implement IIsInvulnerableUtil.IIsItemInvulnerable isItemInvulnerable(ItemStack stack){}
    public static void registerOreDictForItemInvul(ItemStack... stacks) {
        if (stacks == null) return;

        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                ItemOreDict.registerOreDict(item, isInvulOreDict, stack);
            }
        }
    }

}
