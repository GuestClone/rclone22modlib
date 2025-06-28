package rclone22.modsrc22.rclone22modlib.api.potion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BadEffectsList
{

    public static Set<String> mcBadEffects() {
        return new HashSet<>(Arrays.asList(
                //Minecraft
                "minecraft:slowness",
                "minecraft:mining_fatigue",
                "minecraft:instant_damage",
                "minecraft:nausea",
                "minecraft:blindness",
                "minecraft:hunger",
                "minecraft:weakness",
                "minecraft:poison",
                "minecraft:wither",
                "minecraft:levitation",
                "minecraft:unluck"

        ));
    }

    public static Set<String> modBadEffects() {
        return new HashSet<>(Arrays.asList(
                //Put in "modId:effectName" of any mod which you think is bad

                "mod:",
                "mod:"

        ));
    }


}
