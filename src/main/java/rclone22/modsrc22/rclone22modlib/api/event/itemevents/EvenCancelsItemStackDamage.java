package rclone22.modsrc22.rclone22modlib.api.event.itemevents;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class EvenCancelsItemStackDamage extends Event {

    private final ItemStack stack;

    boolean isItemDamageCancelled;

    public EvenCancelsItemStackDamage(ItemStack stack) {
        this.stack = stack;

    }

    public ItemStack getStack() {
        return stack;
    }

    public boolean isEventCancelledFirst() {

        if (this.isCanceled())
        {
            return true;
        }

        return false;
    }

}
