package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.minecraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;
import rclone22.modsrc22.rclone22modlib.api.event.entityevent.EntityDeathEvent;
import rclone22.modsrc22.rclone22modlib.api.event.entityevent.EntityUpdateEvent;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;


@Mixin(value = net.minecraft.entity.Entity.class, remap = false)
public class MCEntity {

    @Unique
    private Entity r22hbmmixin$this = (Entity) (Object) this;

    @Inject(method = "setDead", at = @At("HEAD"), cancellable = true)
    public void setDead(CallbackInfo ci) {
        if (!r22hbmmixin$this.world.isRemote)
        {
            EntityDeathEvent event = new EntityDeathEvent(r22hbmmixin$this);
            boolean canceled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);

            if (canceled) {
                r22hbmmixin$this.isDead = false;
                ci.cancel();
            }
        }
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true, remap = false)
    public void attackEntityFrom(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        if (r22hbmmixin$this instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) r22hbmmixin$this;


            if (SoftEMDEHook.softEMDEHookGetterObject(entityLivingBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                cir.setReturnValue(false);
            }
        }


    }

    @Inject(method = "onKillCommand", at = @At("HEAD"), cancellable = true, remap = false)
    public void onKillCommand(CallbackInfo ci)
    {
        if (!r22hbmmixin$this.world.isRemote)
        {
            EntityUpdateEvent event = new EntityUpdateEvent(r22hbmmixin$this);
            boolean canceled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);

            if (canceled) {
                ci.cancel();
            }
        }

    }

    @Inject(method = "isEntityAlive", at = @At("HEAD"), cancellable = true, remap = false)
    public void isEntityAlive(CallbackInfoReturnable<Boolean> cir)
    {
        if (!r22hbmmixin$this.world.isRemote)
        {
            EntityDeathEvent event = new EntityDeathEvent(r22hbmmixin$this);
            boolean canceled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);

            if (canceled) {
                cir.setReturnValue(true);
            }
        }

    }

    @Inject(method = "onUpdate", at = @At("HEAD"), remap = false)
    public void onUpdate(CallbackInfo ci)
    {

    }

    @Inject(method = "onEntityUpdate", at = @At("HEAD"), remap = false, cancellable = true)
    public void onEntityUpdate(CallbackInfo ci)
    {
        if (!r22hbmmixin$this.world.isRemote)
        {
            EntityUpdateEvent event = new EntityUpdateEvent(r22hbmmixin$this);
            boolean canceled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);

            if (canceled) {
                ci.cancel();
            }

            EntityUpdateEvent eventDeath = new EntityUpdateEvent(r22hbmmixin$this);
            boolean canceledDeath = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(eventDeath);
            if (canceledDeath) {
                r22hbmmixin$this.isDead = false;
            }

        }
    }

    @Inject(method = "isBurning", at = @At("HEAD"), cancellable = true, remap = false)
    public void isBurning(CallbackInfoReturnable<Boolean> cir)
    {
        if (r22hbmmixin$this instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) r22hbmmixin$this;

            if (SoftEMDEHook.softEMDEHookGetterObject(entityLivingBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                cir.setReturnValue(false);
            }
        }

    }

    @Inject(method = "setOnFireFromLava", at = @At("HEAD"), cancellable = true, remap = false)
    protected void setOnFireFromLava(CallbackInfo ci)
    {
        if (r22hbmmixin$this instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) r22hbmmixin$this;

            if (SoftEMDEHook.softEMDEHookGetterObject(entityLivingBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
               ci.cancel();
            }
        }
    }

    @Inject(method = "getFireImmuneTicks", at = @At("HEAD"), cancellable = true, remap = false)
    protected void getFireImmuneTicks(CallbackInfoReturnable<Integer> cir)
    {
        if (r22hbmmixin$this instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) r22hbmmixin$this;

            if (SoftEMDEHook.softEMDEHookGetterObject(entityLivingBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                cir.setReturnValue(2147483647);
            }
        }
    }

    @Inject(method = "isEntityInvulnerable", at = @At("HEAD"), cancellable = true, remap = false)
    public void isEntityInvulnerable(DamageSource source, CallbackInfoReturnable<Boolean> cir)
    {
        if (r22hbmmixin$this instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) r22hbmmixin$this;


            if (SoftEMDEHook.softEMDEHookGetterObject(entityLivingBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                cir.setReturnValue(true);
            } else if (!SoftEMDEHook.softEMDEHookGetterObject(entityLivingBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class)) {
                cir.setReturnValue(false);
            }
        }

        if (r22hbmmixin$this instanceof EntityItem) {
            EntityItem rclone22modlib$entityItemI = (EntityItem) r22hbmmixin$this;
            ItemStack rclone22modlib$stack = rclone22modlib$entityItemI.getItem();
            if (EventItemHook.cancelEntityItemDamageHook(rclone22modlib$entityItemI, rclone22modlib$stack).isEventCancelled()) {
                cir.setReturnValue(true);
            } else if (!EventItemHook.cancelEntityItemDamageHook(rclone22modlib$entityItemI, rclone22modlib$stack).isEventCancelled()) {
                cir.setReturnValue(false);
            }

        }

    }


}
