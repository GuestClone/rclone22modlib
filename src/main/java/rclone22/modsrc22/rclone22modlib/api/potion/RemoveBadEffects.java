package rclone22.modsrc22.rclone22modlib.api.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.*;

public class RemoveBadEffects
{

    public static void doAll(EntityLivingBase e)
    {
        newRemoveBadEffects(e);
    }


    public static void newRemoveBadEffects(EntityLivingBase entity)
    {
        Set<String> badEffectsMC = mcBadEffects();
        Set<String> badEffectsMod = modBadEffects();


        List<PotionEffect> activeEffects = new ArrayList<>(entity.getActivePotionEffects());

        for (PotionEffect effect : activeEffects) {
            Potion potion = effect.getPotion();


            boolean isBad = potion.isBadEffect();


            ResourceLocation id = potion.getRegistryName();
            if (id != null) {
                String name = id.toString();
                if (badEffectsMC.contains(name) || badEffectsMod.contains(name)) {
                    isBad = true;
                }
            }

            if (isBad) {
                entity.removePotionEffect(potion);
            }
        }
    }

    public static void removeBadEffects(EntityLivingBase e) {

            List<Potion> potionsToRemove = new ArrayList<Potion>();

            for (PotionEffect effect : e.getActivePotionEffects()) {
                Potion potion = effect.getPotion();
                if (potion.isBadEffect()) {
                    potionsToRemove.add(potion);
                }
            }

            for (Potion potion : potionsToRemove) {
                e.removePotionEffect(potion);
            }

            for (PotionEffect effect : new ArrayList<>(e.getActivePotionEffects())) {
                Potion potion = effect.getPotion();
                if (potion.isBadEffect()) {
                    e.removePotionEffect(potion);
                }
            }

    }

    public static void removeBadEffectsList(EntityLivingBase entity) {



            Set<String> badEffectsMC = mcBadEffects();
            Set<String> badEffectsMod = modBadEffects();

            List<Potion> potionsToRemove = new ArrayList<Potion>();
            for (PotionEffect effect : entity.getActivePotionEffects()) {
                Potion potion = effect.getPotion();
                ResourceLocation registryName = potion.getRegistryName();
                if (registryName != null) {
                    String name = registryName.toString();
                    if (badEffectsMC.contains(name) || badEffectsMod.contains(name)) {
                        potionsToRemove.add(potion);
                    }
                }
            }


            for (Potion potion : potionsToRemove) {
                entity.removePotionEffect(potion);
            }


    }



    public static Set<String> mcBadEffects() {
        return new HashSet<>(Arrays.asList(BadEffectsListConfig.mcBadEffects));
    }

    public static Set<String> modBadEffects() {
        return new HashSet<>(Arrays.asList(BadEffectsListConfig.modBadEffects));
    }

}
