package rclone22.modsrc22.rclone22modlib.mixins.hbm.utils;

import com.hbm.util.ContaminationUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;

@Pseudo
@Mixin(targets = "com/hbm/util/ContaminationUtil", remap = false)
public abstract class HBMContaminationUtil
{



   /// Radiation
    @Inject(method = "calculateRadiationMod(Lnet/minecraft/entity/EntityLivingBase;)F", at = @At("HEAD"), cancellable = true, remap = false)
    private static void calculateRadiationMod(EntityLivingBase entity, CallbackInfoReturnable<Float> cir) {
        EditModdedDangerEvents.EditRadiation editRadiation = new EditModdedDangerEvents.EditRadiation(entity);
        MinecraftForge.EVENT_BUS.post(editRadiation);
        if (editRadiation.isRadiationImmune()) {
            cir.setReturnValue(0F);
        }
    }

    @Inject(method = "getRads(Lnet/minecraft/entity/Entity;)F", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getRads(Entity e, CallbackInfoReturnable<Float> cir) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            EditModdedDangerEvents.EditRadiation editRadiation = new EditModdedDangerEvents.EditRadiation(livingEntityBase);
            MinecraftForge.EVENT_BUS.post(editRadiation);
            if (editRadiation.isRadiationImmune()) {
                cir.setReturnValue(0F);
            }
        }
    }

    @Inject(method = "isRadImmune(Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"), cancellable = true, remap = false)
    private static void isRadImmune(Entity e, CallbackInfoReturnable<Boolean> cir) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            EditModdedDangerEvents.EditRadiation editRadiation = new EditModdedDangerEvents.EditRadiation(livingEntityBase);
            MinecraftForge.EVENT_BUS.post(editRadiation);
            if (editRadiation.isRadiationImmune()) {
                cir.setReturnValue(editRadiation.isRadiationImmune());
            }
        }
    }

    /// Asbestos
    @Inject(method = "applyAsbestos(Lnet/minecraft/entity/Entity;III)V", at = @At("HEAD"), cancellable = true, remap = false)
    private static void applyAsbestos(Entity e, int i, int dmg, int chance, CallbackInfo ci) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            EditModdedDangerEvents.EditAsbestos editAsbestos = new EditModdedDangerEvents.EditAsbestos(livingEntityBase);
            MinecraftForge.EVENT_BUS.post(editAsbestos);
            if (editAsbestos.isAsbestosImmune()) {
                ci.cancel();
            }
        }
    }

    /// Coal
    @Inject(method = "applyCoal(Lnet/minecraft/entity/Entity;III)V", at = @At("HEAD"), cancellable = true, remap = false)
    private static void applyCoal(Entity e, int i, int dmg, int chance, CallbackInfo ci) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            EditModdedDangerEvents.EditCoal editCoal = new EditModdedDangerEvents.EditCoal(livingEntityBase);
            MinecraftForge.EVENT_BUS.post(editCoal);
            if (editCoal.isCoalImmune()) {
                ci.cancel();
            }
        }
    }

    /// Digamma
    @Inject(method = "applyDigammaData(Lnet/minecraft/entity/Entity;F)V", at = @At("HEAD"), cancellable = true, remap = false)
    private static void applyDigammaData(Entity e, float f, CallbackInfo ci) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            EditModdedDangerEvents.EditDigamma editDigamma = new EditModdedDangerEvents.EditDigamma(livingEntityBase);
            MinecraftForge.EVENT_BUS.post(editDigamma);
            if (editDigamma.isDigammaImmune()) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "getDigamma(Lnet/minecraft/entity/Entity;)F", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getDigamma(Entity e, CallbackInfoReturnable<Float> cir) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            EditModdedDangerEvents.EditDigamma editDigamma = new EditModdedDangerEvents.EditDigamma(livingEntityBase);
            MinecraftForge.EVENT_BUS.post(editDigamma);
            if (editDigamma.isDigammaImmune()) {
                cir.setReturnValue(0F);
            }
        }
    }

    ///  Explosion smth
    @Inject(method = "isExplosionExempt(Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"), cancellable = true, remap = false)
    private static void isExplosionExempt(Entity e, CallbackInfoReturnable<Boolean> cir) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            EditModdedDangerEvents editModDangerEvent = new EditModdedDangerEvents(livingEntityBase);
            MinecraftForge.EVENT_BUS.post(editModDangerEvent);
            if (editModDangerEvent.isEventCancelled()) {
                cir.setReturnValue(editModDangerEvent.isEventCancelled());
            }
        }
    }

    /// Hazard
    @Inject(method = "contaminate(Lnet/minecraft/entity/EntityLivingBase;Lcom/hbm/util/ContaminationUtil$HazardType;Lcom/hbm/util/ContaminationUtil$ContaminationType;F)Z", at = @At("HEAD"), cancellable = true, remap = false)
    private static void contaminate(EntityLivingBase entity, ContaminationUtil.HazardType hazard, ContaminationUtil.ContaminationType cont, float amount, CallbackInfoReturnable<Boolean> cir) {
        EditModdedDangerEvents editModDangerEvent = new EditModdedDangerEvents(entity);
        MinecraftForge.EVENT_BUS.post(editModDangerEvent);
        if (editModDangerEvent.isEventCancelled()) {
            cir.setReturnValue(false);
        }
    }

}
