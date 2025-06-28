package rclone22.modsrc22.rclone22modlib.mixins.minecraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rclone22.modsrc22.rclone22modlib.api.item.NbtItemUtil;

@Mixin(value = Entity.class, remap = false)
public abstract class MCEntity
{
    @Unique
    Entity r22hbmmixin$this = (Entity) (Object) this;

    @Shadow
    public boolean isDead;

    @Inject(method = "onKillCommand", at = @At("HEAD"), cancellable = true, remap = false)
    public void onKillCommand(CallbackInfo ci) {
        if (r22hbmmixin$this instanceof EntityItem) {
            EntityItem entityItemI = (EntityItem) r22hbmmixin$this;
            ItemStack stack = entityItemI.getItem();
            if (NbtItemUtil.isItemInvulnerable(stack)) {
                    this.isDead = false;
                    ci.cancel();
            }
        }
    }

    @Inject(method = "setDead", at = @At("HEAD"), cancellable = true, remap = false)
    public void setDead(CallbackInfo ci)
    {
        if (r22hbmmixin$this instanceof EntityItem) {
            EntityItem entityItemI = (EntityItem) r22hbmmixin$this;
            ItemStack stack = entityItemI.getItem();

            if (NbtItemUtil.isItemInvulnerable(stack)) {
                this.isDead = false;
                ci.cancel();
            }
        }
    }

}
