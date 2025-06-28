package rclone22.modsrc22.rclone22modlib.externalmods.touchasnails.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;

public class TANModTickEvent
{

    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        if (ModChecker.isTANModLoaded()) {
            Entity entity = event.getEntity();

            if (!entity.world.isRemote) {
                if (entity instanceof EntityLivingBase) {
                    EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                    TempEvent.callMethodTemp(livingEntityBase);
                    ThirstEvent.callMethodThirst(livingEntityBase);

                }
            }
        }
    }

}
