package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.minecraft.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.CanHarvestUtil;
import rclone22.modsrc22.rclone22modlib.api.item.harvestlevel.NBTHarvestUtil;


@Mixin(value = net.minecraft.item.Item.class, remap = false)
public abstract class MCItem
{
    @Unique
    Item r22hbmmixin$this = (Item) (Object) this;

    @Unique
    ItemStack rclone22modlib$stack;

    @Shadow
    private java.util.Map<String, Integer> toolClasses = new java.util.HashMap<String, Integer>();

    @Inject(method = "getHarvestLevel", at = @At("HEAD"), cancellable = true, remap = false)
    public void getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState, CallbackInfoReturnable<Integer> cir)
    {
        int customLevel = NBTHarvestUtil.doGetCustomHarvestLevel(stack, toolClass);

        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(stack);

        if (customLevel >= 0) {
            cir.setReturnValue(customLevel);
        }

        if (isCanHarvest) {
            cir.setReturnValue(2147483647);
        }


    }

    @Inject(method = "canHarvestBlock(Lnet/minecraft/block/state/IBlockState;)Z", at = @At("HEAD"), cancellable = true, remap = false)
    public void canHarvestBlock(IBlockState blockIn, CallbackInfoReturnable<Boolean> cir)
    {
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(rclone22modlib$stack);
        if (isCanHarvest) {
            cir.setReturnValue(true);
        }

    }

    @Inject(method = "canHarvestBlock(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true, remap = false)
    public void canHarvestBlock(IBlockState blockIn, ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(stack);
        if (isCanHarvest) {
            cir.setReturnValue(true);
        }

    }

    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true, remap = false)
    public void isEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(true);
    }

    @ModifyVariable(method = "setDamage", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    public int setDamage(int damage, ItemStack stack)
    {
        if (EventItemHook.cancelItemStackDamageHook(stack).isEventCancelledFirst()) {
            return 0;
        }
        return damage;
    }

    @Inject(method = "setHarvestLevel", at = @At("HEAD"), remap = false, cancellable = true)
    public void setHarvestLevel(String toolClass, int level, CallbackInfo ci)
    {
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(rclone22modlib$stack);
        if (isCanHarvest) {
            toolClasses.put(toolClass, 2147483647);
            toolClasses.put("universaltool", 2147483647);
            ci.cancel();
        }
    }

}
