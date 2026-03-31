package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.hbm.utils;

import com.hbm.handler.ArmorUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;


@Mixin(value = ArmorUtil.class, remap = false)
public abstract class HBMArmorUtil
{

    @Inject(method = "checkForHazmat", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForHazmat(EntityLivingBase player, CallbackInfoReturnable<Boolean> cir) {

        if (SoftEMDEHook.softEMDEHookGetterObject(player, "hazmatBMModEvent", "isHazmat", Boolean.class)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForHaz2", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForHaz2(EntityLivingBase player, CallbackInfoReturnable<Boolean> cir) {
        if (SoftEMDEHook.softEMDEHookGetterObject(player, "hazmatBMModEvent", "isHazmat", Boolean.class)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForAsbestos", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForAsbestos(EntityLivingBase player, CallbackInfoReturnable<Boolean> cir) {
        if (SoftEMDEHook.softEMDEHookGetterObject(player, "asbestosHBMModEvent", "isAsbestosImmune", Boolean.class)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForDigamma", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForDigamma(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        if (SoftEMDEHook.softEMDEHookGetterObject(player, "digammaModEvents", "isDigammaImmune", Boolean.class)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForHazmatOnly", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForHazmatOnly(EntityLivingBase player, CallbackInfoReturnable<Boolean> cir) {
        if (SoftEMDEHook.softEMDEHookGetterObject(player, "hazmatBMModEvent", "isHazmat", Boolean.class)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForFaraday", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForFaraday(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        if (SoftEMDEHook.softEMDEHookGetterObject(player, "faradayBMModEvent", "isFaradayImmune", Boolean.class)) {
            cir.setReturnValue(true);
        }
    }

}
