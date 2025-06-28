package rclone22.modsrc22.rclone22modlib.externalmods.galacticraft.event;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;

public class GCCoreEvent
{

    @SubscribeEvent
    public void preOxygen(GCCoreOxygenSuffocationEvent.Pre event) {
        if (ModChecker.isMicDo2GCCoreLoaded()) {
            Entity entity = event.getEntity();
            if (!entity.world.isRemote) {
                if (entity instanceof EntityLivingBase) {
                    EntityLivingBase livingEntityBase = (EntityLivingBase) entity;
                    EditModdedDangerEvents.EditGCOxygenEvent editModDangerEvent = new EditModdedDangerEvents.EditGCOxygenEvent(livingEntityBase);
                    MinecraftForge.EVENT_BUS.post(editModDangerEvent);
                    if (editModDangerEvent.isGCSuffocationCancelled()) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

}
