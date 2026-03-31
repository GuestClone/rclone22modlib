package rclone22.modsrc22.rclone22modlib.api.event.itemevents;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class EventCancelsItemDamage extends EvenCancelsItemStackDamage {


    private final Entity entity;
    boolean isItemDamageCancelled;

    public EventCancelsItemDamage(ItemStack stack, Entity entity) {
        super(stack);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean isEventCancelled() {

        if (this.isCanceled())
        {
            return true;
        }

        if (isEventCancelledFirst())
        {
            return true;
        }

        return false;
    }

}
