package rclone22.modsrc22.rclone22modlib.bootstrap.mixin;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.extensibility.IMixinProcessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.transformer.IMixinTransformer;
import org.spongepowered.asm.mixin.transformer.Proxy;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.service.mojang.MixinServiceLaunchWrapper;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Mixin(value = net.minecraftforge.fml.common.LoadController.class, remap = false)
public class LoadControllerMixin {

    @Shadow
    private Loader loader;

    @Inject(
            method = "distributeStateMessage(Lnet/minecraftforge/fml/common/LoaderState;[Ljava/lang/Object;)V",
            at = @At("HEAD")
    )
    private void onConstructing(LoaderState state, Object[] eventData, CallbackInfo ci) throws Throwable {

        if (state != LoaderState.CONSTRUCTING)
            return;

        ModClassLoader modClassLoader = (ModClassLoader) eventData[0];

        ASMDataTable asmDataTable = (ASMDataTable) eventData[1];

        for (ModContainer container : this.loader.getActiveModList()) {
            modClassLoader.addFile(container.getSource());
        }

        Set<String> loadedMods = new HashSet<String>();

        for (ModContainer container : this.loader.getActiveModList()) {
            loadedMods.add(container.getModId());
        }

        String[][] mixins = new String[][]{
                { "mixins.rclone22modlib.hbm.json", "hbm" },
        };

        for (String[] mixin : mixins) {
            String mixinConfig = mixin[0];
            String requiredMod = mixin[1];

            if (requiredMod == null || loadedMods.contains(requiredMod)) {
                Mixins.addConfigurations(mixinConfig);
                System.out.println("[LateMixin] Added: " + mixinConfig);
            }
        }


            rclone22modlib$rebuildMixinTransformer();
        }

        @Unique
        private void rclone22modlib$rebuildMixinTransformer () throws Exception {
            Field delegatedTransformers =
                    MixinServiceLaunchWrapper.class.getDeclaredField("delegatedTransformers");

            delegatedTransformers.setAccessible(true);
            delegatedTransformers.set(MixinService.getService(), null);

            IMixinProcessor processor =
                    ((IMixinTransformer) Proxy.transformer).getProcessor();

            Method select =
                    processor.getClass().getDeclaredMethod("select", MixinEnvironment.class);

            select.setAccessible(true);
            select.invoke(processor, MixinEnvironment.getCurrentEnvironment());
        }

}
