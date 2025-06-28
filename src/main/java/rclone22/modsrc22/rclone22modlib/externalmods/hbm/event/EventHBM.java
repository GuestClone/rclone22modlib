package rclone22.modsrc22.rclone22modlib.externalmods.hbm.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils.NTMData;

public class EventHBM
{

    public static void modDangerHbm(Entity entity) {
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase elb = (EntityLivingBase) entity;
                EditModdedDangerEvents editModDangerEvent = new EditModdedDangerEvents(elb);
                MinecraftForge.EVENT_BUS.post(editModDangerEvent);

                if (editModDangerEvent.isEventCancelled()) {
                    if (ModChecker.isHbmModLoaded()) {
                        NTMData.setAllToZero(elb);
                    }
                }
            }
        }
    }


}
