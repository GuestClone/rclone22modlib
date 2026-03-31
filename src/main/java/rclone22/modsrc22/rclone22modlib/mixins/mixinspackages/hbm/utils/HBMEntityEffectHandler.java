package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.hbm.utils;

import com.hbm.capability.HbmLivingProps;
import com.hbm.handler.ArmorUtil;
import com.hbm.handler.EntityEffectHandler;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;

import java.util.List;

@Mixin(value = EntityEffectHandler.class, remap = false)
public class HBMEntityEffectHandler {

    @Inject(method = "handleContamination", at = @At("HEAD"), cancellable = true)
    private static void handleContamination(EntityLivingBase entity, CallbackInfo ci) {
        if (SoftEMDEHook.softEMDEHookGetterObject(entity, "editModdedDangerEvents", "isModdedEventCancelled", Boolean.class)) {

            List<HbmLivingProps.ContaminationEffect> contamination = HbmLivingProps.getCont(entity);
            for (HbmLivingProps.ContaminationEffect con : contamination) {
                con.time--;
            }
            contamination.removeIf(con -> con.time <= 0);
            ci.cancel();
        }
    }

}
