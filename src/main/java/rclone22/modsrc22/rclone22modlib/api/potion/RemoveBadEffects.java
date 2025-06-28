package rclone22.modsrc22.rclone22modlib.api.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveBadEffects
{

    public static void doBoth (EntityLivingBase e)
    {
        removeBadEffects(e);
        removeBadEffectsList(e);
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

    }

    public static void removeBadEffectsList(EntityLivingBase entity) {



            Set<String> badEffectsMC = BadEffectsList.mcBadEffects();
            Set<String> badEffectsMod = BadEffectsList.modBadEffects();

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

}
