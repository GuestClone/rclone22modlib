package rclone22.modsrc22.rclone22modlib.mixins.hbm.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;

@Pseudo
@Mixin(targets = "com/hbm/handler/HazmatRegistry", remap = false)
public abstract class HBMHazmatRegistry
{

    @Inject(method = "getResistance(Lnet/minecraft/entity/EntityLivingBase;)F", at = @At("RETURN"), cancellable = true, remap = false)
    private static void getResistance(EntityLivingBase player, CallbackInfoReturnable<Float> cir) {
        float original = cir.getReturnValueF();

        EditModdedDangerEvents.EditRadiation event = new EditModdedDangerEvents.EditRadiation(player);
        MinecraftForge.EVENT_BUS.post(event);

        float radResistance = event.getRadiationResistance();

        cir.setReturnValue(original + radResistance);
    }

}

