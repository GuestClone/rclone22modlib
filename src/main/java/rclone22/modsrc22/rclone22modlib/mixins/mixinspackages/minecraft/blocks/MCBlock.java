package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.minecraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.CanHarvestUtil;


@Mixin(value = Block.class, remap = false)
public abstract class MCBlock {



    @Unique
    private Block rclone22modlib$block = (Block) (Object) this;

    @Unique
    private boolean rclone22modlib$isCanHarvest;

    @Unique
    public boolean rclone22modlib$isCanHarvest() {
        return rclone22modlib$isCanHarvest;
    }


    @Inject(method = "canHarvestBlock", at = @At("HEAD"), remap = false, cancellable = true)
    public void canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player, CallbackInfoReturnable<Boolean> cir)
    {
        ItemStack stack = player.getHeldItemMainhand();
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(stack);
        if (isCanHarvest) {
            rclone22modlib$isCanHarvest = true;
            cir.setReturnValue(true);
        }

    }

    @Inject(method = "getPlayerRelativeBlockHardness", at = @At("HEAD"), remap = false, cancellable = true)
    public void getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos, CallbackInfoReturnable<Float> cir)
    {
        ItemStack stack = player.getHeldItemMainhand();
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(stack);
        float blockHardnessP = state.getBlockHardness(worldIn, pos);

        float speed;

        if (isCanHarvest) {
            speed = ((float)(1D/100D));
        }
        else {
           speed = net.minecraftforge.common.ForgeHooks.blockStrength(state, player, worldIn, pos);
        }

        cir.setReturnValue(speed);
    }





}
