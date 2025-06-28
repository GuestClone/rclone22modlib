package rclone22.modsrc22.rclone22modlib.mixins.minecraft.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.item.NbtItemUtil;

@Mixin(value = ItemStack.class, remap = false)
public abstract class MCItemStack
{
    @Unique
    ItemStack r22hbmmixin$this = (ItemStack) (Object) this;

    @Shadow
    int itemDamage;

    @Shadow
    public abstract Item getItem();

    @Inject(method = "isItemStackDamageable", at = @At("HEAD"), cancellable = true, remap = false)
    public void isItemStackDamageable(CallbackInfoReturnable<Boolean> cir) {
        if (NbtItemUtil.isItemInvulnerable(r22hbmmixin$this)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "damageItem", at = @At("HEAD"), cancellable = true, remap = false)
    public void damageItem(int amount, EntityLivingBase entityIn, CallbackInfo ci) {
        if (NbtItemUtil.isItemInvulnerable(r22hbmmixin$this)) {
            this.itemDamage=0;
            ci.cancel();
        }
    }

    @Inject(method = "setItemDamage", at = @At("HEAD"), remap = false)
    public void setItemDamage(int meta, CallbackInfo ci) {
        if (NbtItemUtil.isItemInvulnerable(r22hbmmixin$this)) {
            getItem().setDamage(r22hbmmixin$this, 0);
        }
    }

}
