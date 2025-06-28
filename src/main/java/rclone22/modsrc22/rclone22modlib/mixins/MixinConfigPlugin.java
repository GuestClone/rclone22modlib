package rclone22.modsrc22.rclone22modlib.mixins;

import com.mojang.realmsclient.client.Request;
import net.minecraft.launchwrapper.Launch;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import rclone22.modsrc22.rclone22modlib.main.Constant;

import java.util.*;

public class MixinConfigPlugin implements IMixinConfigPlugin {


    @Override
    public void onLoad(String mixinPackage) {
        System.out.println("[RClone22] Loading Mixin plugin for package: " + mixinPackage);
    }

    @Override
    public String getRefMapperConfig() {
        return "mixins."+ Constant.MODID+".refmap.json";
    }

    ///  More safety for mixin classes,
    /// prevents missing classses from crashing the game.
    /// Any mixins in my package should be registered into here
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return GetClassTargets.isGCPlayerHandling(mixinClassName)
                || GetClassTargets.isHBMMachineAmgen(mixinClassName)
                || GetClassTargets.isHBMAbsorber(mixinClassName)
                || GetClassTargets.isHBMDeconRad(mixinClassName)
                || GetClassTargets.isHBMDiDecon(mixinClassName)
                || GetClassTargets.isHBMRadSensor(mixinClassName)
                || GetClassTargets.isHBMArmorUtil(mixinClassName)
                || GetClassTargets.isHBMContaminationUtil(mixinClassName)
                || GetClassTargets.isHBMHazmatRegistry(mixinClassName)
                || GetClassTargets.isHBMEntityEffectHandler(mixinClassName)
                || GetClassTargets.isHBMModEventHandler(mixinClassName)
                || GetClassTargets.isMCEntity(mixinClassName)
                || GetClassTargets.isMCItem(mixinClassName)
                || GetClassTargets.isMCItemStack(mixinClassName)
                || GetClassTargets.isMCEntityRenderer(mixinClassName)

                ;
    }




    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        List<String> mixins = new ArrayList<>();
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
