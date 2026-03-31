package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.minecraft.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;

@Mixin(value = net.minecraft.entity.item.EntityItem.class, remap = false)
public abstract class MCEntityItem{



    @Inject(method = "onUpdate", at = @At("HEAD"), remap = false)
    public void onUpdate(CallbackInfo ci)
    {

    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true, remap = false)
    public void attackEntityFrom(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        EntityItem entityItemI = (EntityItem) (Object) this;
        ItemStack stack = entityItemI.getItem();
        if (EventItemHook.cancelEntityItemDamageHook(entityItemI, stack).isEventCancelled()) {
           cir.setReturnValue(false);
        }
    }



}
