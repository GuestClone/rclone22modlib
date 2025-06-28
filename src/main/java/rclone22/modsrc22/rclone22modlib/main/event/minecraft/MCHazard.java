package rclone22.modsrc22.rclone22modlib.main.event.minecraft;



import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.potion.RemoveBadEffects;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.items.CheckItemUtil;
import rclone22.modsrc22.rclone22modlib.main.potions.PotionUtil;
import rclone22.modsrc22.rclone22modlib.main.potions.SecuredBodyPotionRes;

public class MCHazard
{


    public static void callMethodHazard(Entity entity) {
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                IAttributeInstance attr = AbsResEntityAttributes.getOrRegisterAbsResAttr(livingEntityBase);

                PotionEffect potionEffectA = PotionUtil.hasPotionEffectAbsoluteResistanceMethodTrue(livingEntityBase);

                if (attr != null) {

                    boolean conditionActive = false;
                    double newValue = 0.0D;

                    if (potionEffectA != null) {
                        newValue = 0.1D + potionEffectA.getAmplifier();
                        conditionActive = true;
                    }

                    if (CheckItemUtil.isWearingItemByModId(livingEntityBase, EntityEquipmentSlot.HEAD, Constant.MODID, "meta_helmet"))
                    {
                            newValue = 0.1D;
                            conditionActive = true;
                    }

                    if (conditionActive) {
                        attr.setBaseValue(newValue + attr.getAttributeValue());
                    } else {
                        attr.setBaseValue(0.0D);
                    }
                }

                if (AbsResEntityAttributes.hasAbsResAttrResistanceEffect(livingEntityBase)) {
                    RemoveBadEffects.doBoth(livingEntityBase);

                    livingEntityBase.setFire(0);
                    livingEntityBase.extinguish();
                    livingEntityBase.setHealth(livingEntityBase.getMaxHealth());
                    livingEntityBase.setAir(300);

                    if (livingEntityBase instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) livingEntityBase;
                        entityPlayer.getFoodStats().addStats(20, 20.0F);
                    }
                }
            }
        }
    }




}
