package rclone22.modsrc22.rclone22modlib.api.event.itemevents;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class EventCancelsEntityItemDamage extends Event {

    private final Entity entity;
    private final ItemStack itemStack;
    boolean isEntityItemDamageCancelled;

    public EventCancelsEntityItemDamage(Entity entity, ItemStack itemStack) {
        this.entity = entity;
        this.itemStack = itemStack;
    }

    public Entity getEntity() {
        return entity;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public boolean isEventCancelled() {

        if (this.isCanceled())
        {
            return true;
        }

        return false;
    }



}
