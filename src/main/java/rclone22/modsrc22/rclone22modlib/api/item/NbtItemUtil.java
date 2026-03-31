package rclone22.modsrc22.rclone22modlib.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.oredict.OreDictionary;
import rclone22.modsrc22.rclone22modlib.main.items.CheckItemUtil;
import rclone22.modsrc22.rclone22modlib.main.oredict.ItemInvulOreDict;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class NbtItemUtil
{

    /** Use this if you want your item to be resistant to even the /kill command
     * and to prevent item from using durability
     * */
    public static final String itemIsInvulnerable = "itemIsInvulnerable";

    ///  Required instead of looking for enchantment, it looks for nbt
    public static final String isEnchantItemInvul = "isEnchantItemInvul";

    private static final Set<Item> isInvulnerableItems = new HashSet<>();

    ///  Register items through the use of init, postInit and preInit
    public static void registerItemToInvulnerable(Item item) {
        if (item != null) {
            isInvulnerableItems.add(item);
        }
    }

    ///  Register items using its Modid and its registry name in string
    public static void registerItemToInvulByString(String modid, String Item) {
        Item item = CheckItemUtil.tryLoadItem(modid, Item);
        if (item != null) {
            registerItemToInvulnerable(item);
        }
    }

    public static boolean isItemInvulnerable(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        Item item = stack.getItem();

        int[] oreIDs = OreDictionary.getOreIDs(stack);
        for (int id : oreIDs) {
            String oreName = OreDictionary.getOreName(id);
            if (ItemInvulOreDict.isInvulOreDictFinal.equals(oreName)) {
                return true;
            }
        }



        if (getBooleanNbtTagString(stack, itemIsInvulnerable) || getBooleanNbtTagString(stack, isEnchantItemInvul))
        {
            return true;
        }

        if (isInvulnerableItems.contains(item)) {
            return true;
        }

        if (item instanceof IIsInvulnerableUtil.IIsItemInvulnerable) {
            return ((IIsInvulnerableUtil.IIsItemInvulnerable) item).isItemInvulnerable(stack);
        }

        return false;
    }



    public static boolean getBooleanNbtTagString(ItemStack stack, String nbtBoolean) {
        if (stack == null || stack.isEmpty() || !stack.hasTagCompound()) {
            return false;
        }

        NBTTagCompound tag = stack.getTagCompound();
        return tag != null && tag.getBoolean(nbtBoolean);
    }

    public static boolean getNbtTagValue(ItemStack stack, String nbt, boolean defaultValue) {
        if (stack == null || stack.isEmpty()) return defaultValue;

        NBTTagCompound tag = stack.getTagCompound();
        return tag != null && tag.hasKey(nbt, Constants.NBT.TAG_BYTE) ? tag.getBoolean(nbt) : defaultValue;
    }

    public static void setNbtTag(ItemStack stack, String nbt, boolean value) {
        if (stack == null || stack.isEmpty()) return;

        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        tag.setBoolean(nbt, value);
    }

}
