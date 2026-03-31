package rclone22.modsrc22.rclone22modlib.externalmods.galacticraft.event;

import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;
import rclone22.modsrc22.rclone22modlib.main.event.minecraft.ItemEvents;

import java.lang.reflect.Method;


public class GCCoreEvent
{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onAnyEvent(LivingEvent event) {
        if (ModChecker.isMicDo2GCCoreLoaded()) {

            if (event.getClass().getName().equals("micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent$Pre")) {
                try {
                    Method getEntityMethod = event.getClass().getMethod("getEntity");
                    Object entity = getEntityMethod.invoke(event);

                    if (entity instanceof EntityLivingBase && !((Entity) entity).world.isRemote) {
                        EntityLivingBase living = (EntityLivingBase) entity;


                        if (SoftEMDEHook.softEMDEHookGetterObject(living, "oxygenEventModGC", "isGCSuffocationCancelled", Boolean.class)) {
                            Method cancelMethod = event.getClass().getMethod("setCanceled", boolean.class);
                            cancelMethod.invoke(event, true);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        if (ModChecker.isMicDo2GCCoreLoaded()) {
            Entity entity = event.getEntity();

            if (!entity.world.isRemote) {
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entity;

                    GCPlayerStats stats = GCPlayerStats.get(player);

                    GCPlayerHandler gcPlayerHandler = new GCPlayerHandler();

                 ///   GCEventUtil.isItemGCInventoryDamageable(player);

                    if (player instanceof EntityPlayerMP) {
                        EntityPlayerMP playerMP = (EntityPlayerMP) player;

                        GCEventUtil.checkThermalStatus(playerMP, stats, gcPlayerHandler);
                    }
                    ItemEvents.playerDoChanges(player);
                }
            }
        }
    }


}
