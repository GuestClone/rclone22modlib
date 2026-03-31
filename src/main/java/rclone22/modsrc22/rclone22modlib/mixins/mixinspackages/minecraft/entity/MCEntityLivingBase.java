package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.minecraft.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;

@Mixin(value = net.minecraft.entity.EntityLivingBase.class, remap = false)
public class MCEntityLivingBase {


    @Unique
    EntityLivingBase r22hbmmixin$this = (EntityLivingBase) (Object) this;


}
