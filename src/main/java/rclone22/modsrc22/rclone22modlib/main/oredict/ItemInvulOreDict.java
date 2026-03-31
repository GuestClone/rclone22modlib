package rclone22.modsrc22.rclone22modlib.main.oredict;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.main.items.CheckItemUtil;

public class ItemInvulOreDict
{


    public static final String isInvulOreDict = "IsInvulOreDict";

    public static final Item itemAvaritia1 = CheckItemUtil.tryLoadItem(ModChecker.AVARITIA, "infinity_helmet");
    public static final Item itemAvaritia2 = CheckItemUtil.tryLoadItem(ModChecker.AVARITIA, "infinity_chestplate");
    public static final Item itemAvaritia3 = CheckItemUtil.tryLoadItem(ModChecker.AVARITIA, "infinity_pants");
    public static final Item itemAvaritia4 = CheckItemUtil.tryLoadItem(ModChecker.AVARITIA, "infinity_boots");

    public static final String isInvulOreDictFinal = OreDictInit.item+isInvulOreDict; /// itemIsInvulOreDict

    public static void registerOres() {
        registerOreDictForItemInvul(
              ///  new ItemStack(Items.NETHER_STAR),
                ///  Avaritia
              ///  new ItemStack(ItemInvulOreDict.itemAvaritia1),
              ///  new ItemStack(ItemInvulOreDict.itemAvaritia2),
              ///  new ItemStack(ItemInvulOreDict.itemAvaritia3),
              ///  new ItemStack(ItemInvulOreDict.itemAvaritia4)
        );

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

    /// Use this if you dont want to implement IIsInvulnerableUtil.IItemHarvestLevel isItemInvulnerable(ItemStack stack){}
    /// Or dont want to manually apply nbt tags in your item
    public static void registerOreDictForItemInvul(ItemStack... stacks) {
        if (stacks == null) return;

        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                ItemInvulOreDict.registerOreDict(OreDictInit.item, isInvulOreDict, stack);
            }
        }
    }

}
