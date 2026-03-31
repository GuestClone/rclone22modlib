package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.hbm.utils;

import com.hbm.util.ContaminationUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;


@Mixin(value = ContaminationUtil.class, remap = false)
public abstract class HBMContaminationUtil
{



   /// Radiation
    @Inject(method = "calculateRadiationMod(Lnet/minecraft/entity/EntityLivingBase;)F", at = @At("HEAD"), cancellable = true, remap = false)
    private static void calculateRadiationMod(EntityLivingBase entity, CallbackInfoReturnable<Float> cir) {
        if (SoftEMDEHook.softEMDEHookGetterObject(entity, "radiationHBMModEvents", "isRadiationImmune", Boolean.class)) {
            cir.setReturnValue(0F);
        }
    }

    @Inject(method = "getRads(Lnet/minecraft/entity/Entity;)F", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getRads(Entity e, CallbackInfoReturnable<Float> cir) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "radiationHBMModEvents", "isRadiationImmune", Boolean.class)) {
                cir.setReturnValue(0F);
            }
        }
    }

    @Inject(method = "isRadImmune(Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"), cancellable = true, remap = false)
    private static void isRadImmune(Entity e, CallbackInfoReturnable<Boolean> cir) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "radiationHBMModEvents", "isRadiationImmune", Boolean.class)) {
                cir.setReturnValue(SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "radiationHBMModEvents", "isRadiationImmune", Boolean.class));
            }
        }
    }

    /// Asbestos
    @Inject(method = "applyAsbestos(Lnet/minecraft/entity/Entity;III)V", at = @At("HEAD"), cancellable = true, remap = false)
    private static void applyAsbestos(Entity e, int i, int dmg, int chance, CallbackInfo ci) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "asbestosHBMModEvent", "isAsbestosImmune", Boolean.class)) {
                ci.cancel();
            }
        }
    }

    /// Coal
    @Inject(method = "applyCoal(Lnet/minecraft/entity/Entity;III)V", at = @At("HEAD"), cancellable = true, remap = false)
    private static void applyCoal(Entity e, int i, int dmg, int chance, CallbackInfo ci) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;

            if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "coalHBMModEvent", "isCoalImmune", Boolean.class)) {
                ci.cancel();
            }
        }
    }

    /// Digamma
    @Inject(method = "applyDigammaData(Lnet/minecraft/entity/Entity;F)V", at = @At("HEAD"), cancellable = true, remap = false)
    private static void applyDigammaData(Entity e, float f, CallbackInfo ci) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;

            if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "digammaModEvents", "isDigammaImmune", Boolean.class)) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "getDigamma(Lnet/minecraft/entity/Entity;)F", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getDigamma(Entity e, CallbackInfoReturnable<Float> cir) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "digammaModEvents", "isDigammaImmune", Boolean.class)) {
                cir.setReturnValue(0F);
            }
        }
    }

    ///  Explosion smth
    @Inject(method = "isExplosionExempt(Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"), cancellable = true, remap = false)
    private static void isExplosionExempt(Entity e, CallbackInfoReturnable<Boolean> cir) {
        if (e instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) e;
            if (SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isModdedEventCancelled", Boolean.class)) {
                cir.setReturnValue(SoftEMDEHook.softEMDEHookGetterObject(livingEntityBase, "editModdedDangerEvents", "isModdedEventCancelled", Boolean.class));
            }
        }
    }

    /// Hazard
    @Inject(method = "contaminate(Lnet/minecraft/entity/EntityLivingBase;Lcom/hbm/util/ContaminationUtil$HazardType;Lcom/hbm/util/ContaminationUtil$ContaminationType;F)Z", at = @At("HEAD"), cancellable = true, remap = false)
    private static void contaminate(EntityLivingBase entity, ContaminationUtil.HazardType hazard, ContaminationUtil.ContaminationType cont, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (SoftEMDEHook.softEMDEHookGetterObject(entity, "editModdedDangerEvents", "isModdedEventCancelled", Boolean.class)) {
            cir.setReturnValue(false);
        }
    }

}
