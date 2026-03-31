package rclone22.modsrc22.rclone22modlib.api.potion;

import net.minecraftforge.common.config.Config;
import rclone22.modsrc22.rclone22modlib.main.Constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Config(modid = Constant.MODID, name = "Bad Effects List", type = Config.Type.INSTANCE)
public class BadEffectsListConfig
{

    @Config.Name("Minecraft Vanilla Bad Effects")
    @Config.Comment({
            "List of bad effects from Minecraft.",
            "Format: modid:effectname (e.g., minecraft:poison)"
    })
    public static String[] mcBadEffects = {
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
    };

    @Config.Name("Modded Bad Effects")
    @Config.Comment({
            "List of bad effects from mods.",
            "Format: modid:effectname (e.g., modname:corrosion)"
    })
    public static String[] modBadEffects = {
            "modid:bad_potion",
            "anothermod:toxic",
            "examplemod:idkbadeffect"
    };


}
