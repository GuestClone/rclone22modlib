package rclone22.modsrc22.rclone22modlib.api.event.itemevents;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class EventItemHook
{

    public static EventCancelsItemDamage cancelItemDamageHook(Entity entity, ItemStack stack)
    {
        EventCancelsItemDamage eventCancelsItemDamage = new EventCancelsItemDamage(stack, entity);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(eventCancelsItemDamage);
        return eventCancelsItemDamage;
    }

    public static EvenCancelsItemStackDamage cancelItemStackDamageHook(ItemStack stack)
    {
        EvenCancelsItemStackDamage eventCancelsItemDamage = new EvenCancelsItemStackDamage(stack);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(eventCancelsItemDamage);
        return eventCancelsItemDamage;
    }

    public static EventCancelsEntityItemDamage cancelEntityItemDamageHook(Entity entity, ItemStack stack)
    {
        EventCancelsEntityItemDamage eventCancelsEntityItemDamage = new EventCancelsEntityItemDamage(entity, stack);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(eventCancelsEntityItemDamage);
        return eventCancelsEntityItemDamage;

    }




}
