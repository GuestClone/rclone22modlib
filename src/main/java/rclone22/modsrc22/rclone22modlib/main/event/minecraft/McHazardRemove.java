package rclone22.modsrc22.rclone22modlib.main.event.minecraft;




import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;


public class McHazardRemove
{


    public static void onEntityJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (event.getEntity() instanceof EntityLivingBase) {
                EntityLivingBase entityLB = (EntityLivingBase) event.getEntity();

                resetCustomAttribute(entityLB);
            }
        }
    }

    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                AbsResEntityAttributes.registerAttributes(livingEntityBase);

                MCHazard.callMethodHazard(livingEntityBase);

            }
        }
    }

    public static void entityAttack(LivingAttackEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                // if(PotionUtil.hasPotionAbsoluteResistanceMethodTrue(livingEntityBase)) {event.setCanceled(true);}

                if (AbsResEntityAttributes.hasAbsResAttrResistanceEffect(livingEntityBase)) {
                    event.setCanceled(true);
                }

            }
        }
    }

    public static void entityHurt(LivingHurtEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                //if(PotionUtil.hasPotionAbsoluteResistanceMethodTrue(livingEntityBase)) {event.setCanceled(true);}

                ;

                if (AbsResEntityAttributes.hasAbsResAttrResistanceEffect(livingEntityBase)) {
                    event.setCanceled(true);
                }

            }
        }
    }



    public static void entityDamage(LivingDamageEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

            // if(PotionUtil.hasPotionAbsoluteResistanceMethodTrue(livingEntityBase)) {event.setCanceled(true);}


            if (AbsResEntityAttributes.hasAbsResAttrResistanceEffect(livingEntityBase)) {
                event.setCanceled(true);
            }
            }
        }
    }


    public static void entityDead(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;


                resetCustomAttribute(livingEntityBase);

                if (AbsResEntityAttributes.hasAbsResAttrResistanceEffect(livingEntityBase)) {
                    event.setCanceled(true);
                }

                // if(PotionUtil.hasPotionAbsoluteResistanceMethodTrue(livingEntityBase)) {event.setCanceled(true);}

            }
        }
    }

    public static void resetCustomAttribute(EntityLivingBase entity) {
        IAttributeInstance attr = AbsResEntityAttributes.getOrRegisterAbsResAttr(entity);
        if (attr != null) {
            attr.setBaseValue(0.0D);
        }
    }

}



