package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.minecraft.minecraftforge;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.CanHarvestUtil;

@Mixin(value = ForgeHooks.class, remap = false)
public class MCFORGEForgeHooks {

    @Inject(method = "canHarvestBlock", at = @At("HEAD"), remap = false, cancellable = true)
    private static void canHarvestBlock(Block block, EntityPlayer player, IBlockAccess world, BlockPos pos, CallbackInfoReturnable<Boolean> cir)
    {
        ItemStack stack = player.getHeldItemMainhand();
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(stack);
        if (isCanHarvest) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "canToolHarvestBlock", at = @At("HEAD"), remap = false, cancellable = true)
    private static void canToolHarvestBlock(IBlockAccess world, BlockPos pos, ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(stack);
        if (isCanHarvest) {
            cir.setReturnValue(true);
        }
    }
}
