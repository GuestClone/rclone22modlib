package rclone22.modsrc22.rclone22modlib.main.event.minecraft;




import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;
import rclone22.modsrc22.rclone22modlib.api.potion.RemoveBadEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class McHazardRemove
{




    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEntityJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (event.getEntity() instanceof EntityLivingBase) {
                EntityLivingBase entityLB = (EntityLivingBase) event.getEntity();

                resetCustomAttribute(entityLB);
            }
        }
    }



    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entityKnockback(LivingKnockBackEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;



                if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                      event.setCanceled(true);
                }

            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entityAttack(LivingAttackEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                // if(PotionUtil.hasPotionAbsoluteResistanceMethodTrue(livingEntityBase)) {event.setCanceled(true);}



                if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                    event.setCanceled(true);
                    livingEntityBase.setHealth(livingEntityBase.getMaxHealth());
                }

            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entityHurt(LivingHurtEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                //if(PotionUtil.hasPotionAbsoluteResistanceMethodTrue(livingEntityBase)) {event.setCanceled(true);}



                if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                   event.setCanceled(true);
                    livingEntityBase.setHealth(livingEntityBase.getMaxHealth());
                }

            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entityDamage(LivingDamageEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

            // if(PotionUtil.hasPotionAbsoluteResistanceMethodTrue(livingEntityBase)) {event.setCanceled(true);}



                if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                    event.setCanceled(true);
                    livingEntityBase.setHealth(livingEntityBase.getMaxHealth());
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entityDead(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        if ((!entity.world.isRemote) || (!(event.isCancelable()) && event.isCanceled())) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                resetCustomAttribute(livingEntityBase);

            }
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void potionApplicable(PotionEvent.PotionApplicableEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                PotionEffect effect = event.getPotionEffect();
                Potion potion = effect.getPotion();

                ResourceLocation registryName = potion.getRegistryName();

                if (registryName != null) {
                    String name = registryName.toString();

                    Set<String> badEffectsMC = RemoveBadEffects.mcBadEffects();
                    Set<String> badEffectsMod = RemoveBadEffects.modBadEffects();

                    if (badEffectsMC.contains(name) || badEffectsMod.contains(name)) {
                        event.setResult(Event.Result.DENY);
                    }
                }

                if (potion.isBadEffect()) {
                    if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                        event.setResult(Event.Result.DENY);
                    }
                }



            }
        }
    }

    public static void resetCustomAttribute(EntityLivingBase entity) {
        IAttributeInstance attr = AbsResEntityAttributes.getOrRegisterAbsResAttr(entity);
        if (attr != null) {
            AbsResEntityAttributes.clearAbsResAttrResistance(entity);
        }
    }



}



