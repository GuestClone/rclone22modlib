package rclone22.modsrc22.rclone22modlib.main.event.minecraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;
import rclone22.modsrc22.rclone22modlib.api.event.entityevent.EntityDeathEvent;
import rclone22.modsrc22.rclone22modlib.api.event.entityevent.EntityUpdateEvent;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;

public class EntityEvent {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void EntityUpdateEvent(EntityUpdateEvent event)
    {
        Entity entity = event.getEntity();

        if (!entity.world.isRemote)
        {
            if (entity instanceof EntityItem) {
                EntityItem entityItemI = (EntityItem) entity;
                ItemEvents.EntityItemsUpdate(entityItemI);
            }

            if (entity instanceof EntityLivingBase)
            {
                EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

                MCHazard.callMethodHazard(entityLivingBase);

                ItemEvents.onAddNbtTick(entityLivingBase);

            }

        }

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void EntityDeathEvent(EntityDeathEvent event)
    {
        Entity entity = event.getEntity();

        if (!entity.world.isRemote)
        {
            if (entity instanceof EntityItem) {
                EntityItem entityItemI = (EntityItem) entity;
                ItemStack stack = entityItemI.getItem();

                boolean cancelEvent = EventItemHook.cancelEntityItemDamageHook(entityItemI, stack).isEventCancelled();

                if (cancelEvent)
                {
                    event.setCanceled(true);
                }
            }

            if (entity instanceof EntityLivingBase)
            {
                EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

                if (SoftEMDEHook.softEMDEHookGetterObject(entityLivingBase, "editModdedDangerEvents", "isDeathCancelled", Boolean.class)) {
                    event.setCanceled(true);
                }
            }

        }

    }




}
