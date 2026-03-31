package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.minecraft.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.CanHarvestUtil;


@Mixin(value = net.minecraft.item.ItemStack.class, remap = false)
public abstract class MCItemStack
{

    @Unique
    ItemStack r22hbmmixin$this = (ItemStack) (Object) this;



    @Inject(method = "isItemStackDamageable", at = @At("HEAD"), cancellable = true, remap = false)
    public void isItemStackDamageable(CallbackInfoReturnable<Boolean> cir) {
        if (EventItemHook.cancelItemStackDamageHook(r22hbmmixin$this).isEventCancelledFirst()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "isItemDamaged", at = @At("HEAD"), cancellable = true, remap = false)
    public void isItemDamaged(CallbackInfoReturnable<Boolean> cir)
    {
        if (EventItemHook.cancelItemStackDamageHook(r22hbmmixin$this).isEventCancelledFirst()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canHarvestBlock", at = @At("HEAD"), cancellable = true, remap = false)
    public void canHarvestBlock(IBlockState blockIn, CallbackInfoReturnable<Boolean> cir)
    {
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(r22hbmmixin$this);

        if (isCanHarvest) {
            cir.setReturnValue(true);
        }
    }
}
