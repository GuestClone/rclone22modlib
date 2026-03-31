package rclone22.modsrc22.rclone22modlib.externalmods.baubles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

///  Can Be put on trinket items
public interface IIsBaubleCancelsDanger
{

    default void doSomething()
    {

    }


    interface IItemBaubleCancelDanger extends IIsBaubleCancelsDanger {
        boolean shouldCancelDanger(ItemStack stack);
    }


    interface IEnchBaubleCancelDanger extends IIsBaubleCancelsDanger{
        boolean shouldCancelDangerEnch(ItemStack stack);
    }



}


