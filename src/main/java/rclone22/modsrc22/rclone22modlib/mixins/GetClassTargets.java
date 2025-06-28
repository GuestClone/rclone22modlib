package rclone22.modsrc22.rclone22modlib.mixins;

import net.minecraft.launchwrapper.Launch;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;

public class GetClassTargets
{

    private static boolean classExists(String path) {
        return Launch.classLoader.getResource(path) != null;
    }

    /// Galacticraft
    public static boolean isGCPlayerHandling(String mixinClassName) {
        return mixinClassName.endsWith("GCPlayerHandling") &&
                classExists("micdoodle8/mods/galacticraft/core/entities/player/GCPlayerHandler.class");
    }

    /// HBM
    public static boolean isHBMMachineAmgen(String mixinClassName) {
        return mixinClassName.endsWith("HBMMachineAmgen") &&
                classExists("com/hbm/blocks/machine/MachineAmgen.class");
    }

    public static boolean isHBMAbsorber(String mixinClassName) {
        return mixinClassName.endsWith("HBMAbsorber") &&
                classExists("com/hbm/blocks/generic/BlockAbsorber.class");
    }

    public static boolean isHBMDeconRad(String mixinClassName) {
        return mixinClassName.endsWith("HBMDeconRad") &&
                classExists("com/hbm/blocks/machine/BlockDeconRad.class");
    }

    public static boolean isHBMDiDecon(String mixinClassName) {
        return mixinClassName.endsWith("HBMDiDecon") &&
                classExists("com/hbm/blocks/machine/BlockDeconDi.class");
    }

    public static boolean isHBMRadSensor(String mixinClassName) {
        return mixinClassName.endsWith("HBMRadSensor") &&
                classExists("com/hbm/blocks/machine/RadSensor.class");
    }

    public static boolean isHBMArmorUtil(String mixinClassName) {
        return mixinClassName.endsWith("HBMArmorUtil") &&
                classExists("com/hbm/handler/ArmorUtil.class");
    }

    public static boolean isHBMContaminationUtil(String mixinClassName) {
        return mixinClassName.endsWith("HBMContaminationUtil") &&
                classExists("com/hbm/util/ContaminationUtil.class");
    }

    public static boolean isHBMHazmatRegistry(String mixinClassName) {
        return mixinClassName.endsWith("HBMHazmatRegistry") &&
                classExists("com/hbm/handler/HazmatRegistry.class");
    }

    public static boolean isHBMEntityEffectHandler(String mixinClassName) {
        return mixinClassName.endsWith("HBMEntityEffectHandler") &&
                classExists("com/hbm/handler/EntityEffectHandler.class");
    }

    public static boolean isHBMModEventHandler(String mixinClassName) {
        return mixinClassName.endsWith("HBMModEventHandler") &&
                classExists("com/hbm/main/ModEventHandler.class");
    }

    /// Minecraft core classes
    /// Yes because it needs to be registered for it to run the mixins
    /// Even if it targets Minecraft classes
    public static boolean isMCEntity(String mixinClassName) {
        return mixinClassName.endsWith("MCEntity") &&
                classExists("net/minecraft/entity/Entity.class");
    }

    public static boolean isMCItem(String mixinClassName) {
        return mixinClassName.endsWith("MCItem") &&
                classExists("net/minecraft/item/Item.class");
    }

    public static boolean isMCItemStack(String mixinClassName) {
        return mixinClassName.endsWith("MCItemStack") &&
                classExists("net/minecraft/item/ItemStack.class");
    }

    public static boolean isMCEntityRenderer(String mixinClassName) {
        return mixinClassName.endsWith("MCEntityRenderer") &&
                classExists("net/minecraft/client/renderer/EntityRenderer.class");
    }

}
