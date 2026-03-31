package rclone22.modsrc22.rclone22modlib.main.event.minecraft;



import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;
import rclone22.modsrc22.rclone22modlib.api.potion.RemoveBadEffects;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.items.CheckItemUtil;
import rclone22.modsrc22.rclone22modlib.main.potions.PotionUtil;


public class MCHazard
{


    public static void callMethodHazard(Entity entity) {
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                EditModdedDangerEvents editModDangerEvent = new EditModdedDangerEvents(livingEntityBase);
                MinecraftForge.EVENT_BUS.post(editModDangerEvent);

                IAttributeInstance attr = AbsResEntityAttributes.getOrRegisterAbsResAttr(livingEntityBase);

                PotionEffect potionEffectA = PotionUtil.hasPotionEffectAbsoluteResistanceMethodTrue(livingEntityBase);

                if (attr != null) {

                    boolean conditionActive = false;
                    double newValue = 0.0D;

                    /*
                    if (potionEffectA != null) {
                        newValue += 1.0D + potionEffectA.getAmplifier();
                        conditionActive = true;
                    }

                    if (CheckItemUtil.isWearingItemByModId(livingEntityBase, EntityEquipmentSlot.HEAD, Constant.MODID, "meta_helmet"))
                    {
                            newValue += 1.0D;
                            conditionActive = true;
                    }

                    if (CheckItemUtil.isWearingItemByModId(livingEntityBase, EntityEquipmentSlot.HEAD, ModChecker.AVARITIA, "infinity_helmet") &&
                            CheckItemUtil.isWearingItemByModId(livingEntityBase, EntityEquipmentSlot.CHEST, ModChecker.AVARITIA, "infinity_chestplate") &&
                            CheckItemUtil.isWearingItemByModId(livingEntityBase, EntityEquipmentSlot.LEGS, ModChecker.AVARITIA, "infinity_pants") &&
                            CheckItemUtil.isWearingItemByModId(livingEntityBase, EntityEquipmentSlot.FEET, ModChecker.AVARITIA, "infinity_boots"))
                    {
                        newValue += 1.0D;
                        conditionActive = true;
                    }

                     */


                    if (conditionActive) {
                        AbsResEntityAttributes.setAbsResAttrResistance(livingEntityBase, newValue);
                    } else {
                        AbsResEntityAttributes.clearAbsResAttrResistance(livingEntityBase);
                    }
                }


                if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                    livingEntityBase.setEntityInvulnerable(true);
                } else if (!SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class))
                {
                    livingEntityBase.setEntityInvulnerable(false);
                }

                if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isDeathCancelled", Boolean.class)) {
                   RemoveBadEffects.doAll(livingEntityBase);

                    livingEntityBase.setFire(0);
                    livingEntityBase.extinguish();
                    livingEntityBase.setHealth(livingEntityBase.getMaxHealth());
                    livingEntityBase.isDead = false;
                }

                if (livingEntityBase instanceof EntityPlayer) {

                    EntityPlayer entityPlayer = (EntityPlayer) livingEntityBase;

                    EditModdedDangerEvents.EditHungerEvent editHungerEvent = new EditModdedDangerEvents.EditHungerEvent(entityPlayer);
                    MinecraftForge.EVENT_BUS.post(editHungerEvent);

                    editHungerEvent.ifCancelledDoFullStats();


                }

                AbsResEntityAttributes.registerAttributes(livingEntityBase);
            }
        }
    }




}
