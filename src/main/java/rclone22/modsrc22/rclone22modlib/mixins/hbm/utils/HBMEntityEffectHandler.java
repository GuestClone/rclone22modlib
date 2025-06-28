package rclone22.modsrc22.rclone22modlib.mixins.hbm.utils;

import com.hbm.capability.HbmLivingCapability;
import com.hbm.capability.HbmLivingProps;
import com.hbm.packet.ExtPropPacket;
import com.hbm.packet.PacketDispatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
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
@Mixin(targets = "com/hbm/handler/EntityEffectHandler", remap = false)
public abstract class HBMEntityEffectHandler
{

    @Inject(method = "onUpdate(Lnet/minecraft/entity/EntityLivingBase;)V", at = @At("HEAD"), remap = false)
    private static void onUpdate(EntityLivingBase entity, CallbackInfo ci) {
        if (!entity.world.isRemote) {
            EventHBM.modDangerHbm(entity);
        }
    }

}
