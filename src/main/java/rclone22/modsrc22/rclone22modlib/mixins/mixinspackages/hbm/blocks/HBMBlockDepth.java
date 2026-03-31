package rclone22.modsrc22.rclone22modlib.mixins.mixinspackages.hbm.blocks;

import api.hbm.item.IDepthRockTool;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockDepth;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.CanHarvestUtil;

@Mixin(value = BlockDepth.class, remap = false)
public class HBMBlockDepth extends Block {

    public HBMBlockDepth(String s){
        super(Material.ROCK);
        this.setUnlocalizedName(s);
        this.setRegistryName(s);
        this.setHarvestLevel("pickaxe", 3);
        this.setBlockUnbreakable();
        this.setResistance(10.0F);

        ModBlocks.ALL_BLOCKS.add(this);
    }


    @SuppressWarnings("deprecation")
    @Inject(method = "func_180647_a", at = @At("HEAD"), cancellable = true, remap = false)
    public void getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos, CallbackInfoReturnable<Float> cir){
        ItemStack stack = player.getHeldItemMainhand();
        boolean isCanHarvest = CanHarvestUtil.canForceHarvest(stack);
        float blockHardnessP = state.getBlockHardness(worldIn, pos);

        float speed;

        if (isCanHarvest) {

            speed = ((float)(1D/100D));
        } else {
            speed = super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
        }

        cir.setReturnValue(speed);
    }


}
