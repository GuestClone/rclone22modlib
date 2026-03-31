package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.hbm.utils;

import com.hbm.handler.HazmatRegistry;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;


@Mixin(value = HazmatRegistry.class, remap = false)
public abstract class HBMHazmatRegistry
{

    @Inject(method = "getResistance(Lnet/minecraft/entity/EntityLivingBase;)F", at = @At("RETURN"), cancellable = true, remap = false)
    private static void getResistance(EntityLivingBase player, CallbackInfoReturnable<Float> cir) {
        float original = cir.getReturnValueF();

        cir.setReturnValue(original + SoftEMDEHook.softEMDEHookGetterObject(player, "radiationHBMModEvents", "resistanceValue", Float.class));
    }


}

