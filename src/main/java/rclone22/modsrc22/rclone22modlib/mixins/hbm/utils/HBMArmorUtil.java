package rclone22.modsrc22.rclone22modlib.mixins.hbm.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;

@Pseudo
@Mixin(targets = "com/hbm/handler/ArmorUtil", remap = false)
public abstract class HBMArmorUtil
{

    @Inject(method = "checkForHazmat", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForHazmat(EntityLivingBase player, CallbackInfoReturnable<Boolean> cir) {
        EditModdedDangerEvents.EditHazmat eventHazmat = new EditModdedDangerEvents.EditHazmat(player);
        MinecraftForge.EVENT_BUS.post(eventHazmat);
        if (eventHazmat.isHazmat()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForHaz2", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForHaz2(EntityLivingBase player, CallbackInfoReturnable<Boolean> cir) {
        EditModdedDangerEvents.EditHazmat eventHazmat = new EditModdedDangerEvents.EditHazmat(player);
        MinecraftForge.EVENT_BUS.post(eventHazmat);
        if (eventHazmat.isHazmat()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForAsbestos", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForAsbestos(EntityLivingBase player, CallbackInfoReturnable<Boolean> cir) {
        EditModdedDangerEvents.EditAsbestos eventAsbestos = new EditModdedDangerEvents.EditAsbestos(player);
        MinecraftForge.EVENT_BUS.post(eventAsbestos);
        if (eventAsbestos.isAsbestosImmune()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForDigamma", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForDigamma(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        EditModdedDangerEvents.EditDigamma eventDigamma = new EditModdedDangerEvents.EditDigamma(player);
        MinecraftForge.EVENT_BUS.post(eventDigamma);
        if (eventDigamma.isDigammaImmune()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForHazmatOnly", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForHazmatOnly(EntityLivingBase player, CallbackInfoReturnable<Boolean> cir) {
        EditModdedDangerEvents.EditHazmat eventHazmat = new EditModdedDangerEvents.EditHazmat(player);
        MinecraftForge.EVENT_BUS.post(eventHazmat);
        if (eventHazmat.isHazmat()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "checkForFaraday", at = @At("HEAD"), cancellable = true, remap = false)
    private static void checkForFaraday(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        EditModdedDangerEvents.EditFaraday eventFaraday = new EditModdedDangerEvents.EditFaraday(player);
        MinecraftForge.EVENT_BUS.post(eventFaraday);
        if (eventFaraday.isFaradayImmune()) {
            cir.setReturnValue(true);
        }
    }

}
