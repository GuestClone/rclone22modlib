package rclone22.modsrc22.rclone22modlib.mixins.hbm.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.externalmods.hbm.event.EventHBM;
import rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils.NTMData;

@Pseudo
@Mixin(targets = "com/hbm/main/ModEventHandler", remap = false)
public abstract class HBMModEventHandler {

    @Inject(method = "onLivingUpdate(Lnet/minecraftforge/event/entity/living/LivingEvent$LivingUpdateEvent;)V", at = @At("HEAD"), remap = false)
    private void onLivingUpdate(LivingEvent.LivingUpdateEvent event, CallbackInfo ci) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase elb = (EntityLivingBase) entity;
                EventHBM.modDangerHbm(elb);
            }
        }
    }

}


