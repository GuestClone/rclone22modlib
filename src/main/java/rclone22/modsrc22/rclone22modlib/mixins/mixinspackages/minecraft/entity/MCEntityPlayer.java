package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.minecraft.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.CanHarvestUtil;
import rclone22.modsrc22.rclone22modlib.main.entities.ThreadPlayer;

@Mixin(value = EntityPlayer.class, remap = false)
public class MCEntityPlayer
{

    @Unique
    EntityPlayer r22hbmmixin$this = (EntityPlayer) (Object) this;

    @Inject(method = "onUpdate", at = @At("HEAD"), remap = false)
    public void onUpdate(CallbackInfo ci)
    {
        ThreadPlayer.setCraftingPlayer(r22hbmmixin$this);
    }

    @Inject(method = "canHarvestBlock", at = @At("HEAD"), remap = false, cancellable = true)
    public void canHarvestBlock(IBlockState state, CallbackInfoReturnable<Boolean> cir)
    {
        ItemStack stack = r22hbmmixin$this.getHeldItemMainhand();
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(stack);
        if (isCanHarvest)
        {
            cir.setReturnValue(true);
        } else {
        cir.setReturnValue(net.minecraftforge.event.ForgeEventFactory.doPlayerHarvestCheck(r22hbmmixin$this, state, r22hbmmixin$this.inventory.canHarvestBlock(state)));
        }
    }


}
