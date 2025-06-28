package rclone22.modsrc22.rclone22modlib.api.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.oredict.OreDictionary;
import rclone22.modsrc22.rclone22modlib.main.oredict.ItemOreDict;

import java.util.Map;

public class NbtItemUtil
{

    /** Use this if you want your item to be resistant to even the /kill command
     * and to prevent item from using durability
     * */
    public static final String itemIsInvulnerable = "itemIsInvulnerable";

    public static boolean isItemInvulnerable(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }

        Item item = stack.getItem();
        if (item instanceof IIsInvulnerableUtil.IIsItemInvulnerable) {
            IIsInvulnerableUtil.IIsItemInvulnerable invulnerable = (IIsInvulnerableUtil.IIsItemInvulnerable) item;
            if (invulnerable.isItemInvulnerable(stack)) {
                return true;
            }
        }


        Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
        for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
            Enchantment enchantment = entry.getKey();
            if (enchantment instanceof IIsInvulnerableUtil.IIsEnchantItemInvul) {
                IIsInvulnerableUtil.IIsEnchantItemInvul invul = (IIsInvulnerableUtil.IIsEnchantItemInvul) enchantment;
                if (invul.isEnchInvulnerable(stack)) {
                    return true;
                }
            }
        }

        int[] oreIDs = OreDictionary.getOreIDs(stack);
        for (int id : oreIDs) {
            String oreName = OreDictionary.getOreName(id);
            if (ItemOreDict.isInvulOreDictFinal.equals(oreName)) {
                return true;
            }
        }

        return NbtItemUtil.getNbtTagString(stack, NbtItemUtil.itemIsInvulnerable);
    }

    public static boolean getNbtTagString(ItemStack stack, String nbt) {
        if (stack == null || stack.isEmpty() || !stack.hasTagCompound()) {
            return false;
        }

        NBTTagCompound tag = stack.getTagCompound();
        return tag != null && tag.getBoolean(nbt);
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
