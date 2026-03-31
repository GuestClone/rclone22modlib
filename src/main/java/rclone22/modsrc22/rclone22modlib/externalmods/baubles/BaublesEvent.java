package rclone22.modsrc22.rclone22modlib.externalmods.baubles;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;
import rclone22.modsrc22.rclone22modlib.main.event.minecraft.ItemEvents;


public class BaublesEvent
{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void cancelModdedDanger(EditModdedDangerEvents event) {
        if (ModChecker.isBaublesModLoaded()) {
            Entity e = event.getEntity();
            if (e != null && e.world != null && !e.world.isRemote)  {
                if (e instanceof EntityLivingBase) {
                    EntityLivingBase eLB = (EntityLivingBase) e;
                    if (eLB instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) eLB;

                        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(entityPlayer);
                        if (baubles != null) {
                            for (int i = 0; i < baubles.getSlots(); i++) {
                                ItemStack stack = baubles.getStackInSlot(i);
                                if (NBTBaubleUtils.isBaubleHasNbt(stack)) {
                                    event.setCanceled(true);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    /*
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        if (ModChecker.isBaublesModLoaded()) {
            Entity entity = event.getEntity();
            if (entity != null && entity.world != null && !entity.world.isRemote) {
                if (entity instanceof EntityLivingBase) {
                    EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                    if (livingEntityBase instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) livingEntityBase;

                        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(entityPlayer);
                        if (baubles != null) {
                            for (int i = 0; i < baubles.getSlots(); i++) {
                                ItemStack stack = baubles.getStackInSlot(i);
                                if (EventItemHook.cancelItemDamageHook(entityPlayer, stack).isEventCancelled()) {
                                    ItemEvents.fixItem(stack, entityPlayer);

                                }


                            }
                        }
                        ItemEvents.playerDoChanges(entityPlayer);
                    }

                }
            }
        }
    }
    */

}
