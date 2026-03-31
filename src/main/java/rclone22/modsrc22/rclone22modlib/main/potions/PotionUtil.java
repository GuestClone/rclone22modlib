package rclone22.modsrc22.rclone22modlib.main.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import rclone22.modsrc22.rclone22modlib.api.potion.IPotionMethodsRes;

public class PotionUtil
{

    /**
     *  Yes all is a stupidly long method name (On purpose)
     * **/
    public static boolean hasPotionAbsoluteResistanceMethodTrue(EntityLivingBase entity) {
        for (PotionEffect effect : entity.getActivePotionEffects()) {
            Potion potion = effect.getPotion();
            if (potion instanceof IPotionMethodsRes) {
                if (((IPotionMethodsRes) potion).isPotionAbsoluteResistance()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * If you want to check for effect duration or effect amplifier
     * E.g:
     * PotionEffect effect = PotionUtil.hasPotionEffectAbsoluteResistanceMethodTrue(player);
     * if (effect != null) {
     *     int amplifier = effect.getAmplifier();
     *     int duration = effect.getDuration();
     *    whatever you gonna place here
     * }
     *
     **/
    public static PotionEffect hasPotionEffectAbsoluteResistanceMethodTrue(EntityLivingBase entity) {
        for (PotionEffect effect : entity.getActivePotionEffects()) {
            Potion potion = effect.getPotion();
            if (potion instanceof IPotionMethodsRes && ((IPotionMethodsRes) potion).isPotionAbsoluteResistance()) {
                return effect;
            }
        }
        return null;
    }

}
