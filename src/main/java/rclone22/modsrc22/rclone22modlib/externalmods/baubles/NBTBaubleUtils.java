package rclone22.modsrc22.rclone22modlib.externalmods.baubles;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import rclone22.modsrc22.rclone22modlib.api.item.IIsInvulnerableUtil;
import rclone22.modsrc22.rclone22modlib.main.oredict.ItemInvulOreDict;

import javax.annotation.Nullable;

public class NBTBaubleUtils
{

    public static final String IsItemBaubleSBP = "itemBaubleSBP";

    public static final String IsEnchBaubleSBP = "enchBaubleSBP";

    public static boolean isBaubleHasNbt(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }


        if (getNbtTagString(stack, IsEnchBaubleSBP) || getNbtTagString(stack, IsItemBaubleSBP))
        {
            return true;
        }

        Item item = stack.getItem();
        if (item instanceof IIsBaubleCancelsDanger.IItemBaubleCancelDanger) {
            return ((IIsBaubleCancelsDanger.IItemBaubleCancelDanger) item).shouldCancelDanger(stack);
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
