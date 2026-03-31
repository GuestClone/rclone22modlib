package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.hbm.hazards;

import com.hbm.modules.ItemHazardModule;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;

@Mixin(value = ItemHazardModule.class, remap = false)
public abstract class HBMItemHazardModule {


    @Inject(method = "applyEffects", at = @At("HEAD"), cancellable = true, remap = false)
    public void applyEffects(EntityLivingBase entity, float mod, int slot, boolean currentItem, EnumHand hand, CallbackInfo ci) {


        if (SoftEMDEHook.softEMDEHookGetterObject(entity, "editModdedDangerEvents", "isModdedEventCancelled", Boolean.class))
        {
            ci.cancel();
        }

    }


}
