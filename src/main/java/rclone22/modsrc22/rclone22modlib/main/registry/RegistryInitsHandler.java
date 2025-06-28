package rclone22.modsrc22.rclone22modlib.main.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.main.event.minecraft.ItemEvents;
import rclone22.modsrc22.rclone22modlib.main.event.minecraft.McHazardRemove;
import rclone22.modsrc22.rclone22modlib.externalmods.touchasnails.event.TANModTickEvent;

public class RegistryInitsHandler
{

    public RegistryInitsHandler()
    {
    }

    @SubscribeEvent
    public void onEntityJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            McHazardRemove.onEntityJoin(event);
        }
    }

    @SubscribeEvent
    public void entityTick(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            McHazardRemove.entityTick(event);

            if (ModChecker.isTANModLoaded()) {
                TANModTickEvent.entityTick(event);
            }

        }
    }

    @SubscribeEvent
    public void entityHurt(LivingHurtEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            McHazardRemove.entityHurt(event);
        }
    }

    @SubscribeEvent
    public void entityAttack(LivingAttackEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            McHazardRemove.entityAttack(event);
        }
    }

    @SubscribeEvent
    public void entityDamage(LivingDamageEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            McHazardRemove.entityDamage(event);
        }
    }

    @SubscribeEvent
    public void entityDead(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            McHazardRemove.entityDead(event);
        }
    }

    @SubscribeEvent
    public void onExplosionDetonate(ExplosionEvent.Detonate event)
    {
        ItemEvents.onExplosionDetonate(event);
    }

    @SubscribeEvent
    public void itemExpireEvent(ItemExpireEvent event)
    {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            ItemEvents.itemExpireEvent(event);
        }
    }


    @SubscribeEvent
    public void onItemToss(ItemTossEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            ItemEvents.onItemToss(event);
        }
    }

    @SubscribeEvent
    public void cancelModdedDanger(EditModdedDangerEvents event) {
        Entity e = event.e;
        if (!e.world.isRemote) {
            if (e instanceof EntityLivingBase) {
                EntityLivingBase eLB = (EntityLivingBase) e;
                if (AbsResEntityAttributes.hasAbsResAttrResistanceEffect(eLB)) {

                }
            }
        }
    }

    @SubscribeEvent
    public void thermalEvent(EditModdedDangerEvents.EditThermalStatusEvent event) {
        Entity e = event.e;
        if (!e.world.isRemote) {
            if (e instanceof EntityLivingBase) {
                EntityLivingBase eLB = (EntityLivingBase) e;
            }
        }
    }

    @SubscribeEvent
    public void radiationResistance(EditModdedDangerEvents.EditRadiation event) {
        Entity e = event.e;
        if (!e.world.isRemote) {
            if (e instanceof EntityLivingBase) {
                EntityLivingBase eLB = (EntityLivingBase) e;
                if (eLB.isPotionActive(MobEffects.RESISTANCE))
                {
                    /// event.setRadiationResistanceBetterVers(100.0F);
                }
            }
        }
    }

}
