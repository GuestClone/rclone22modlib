package rclone22.modsrc22.rclone22modlib.mixins.hbm.blocks;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockAbsorber;
import com.hbm.handler.RadiationSystemNT;
import com.hbm.interfaces.IRadResistantBlock;
import com.hbm.saveddata.RadiationSavedData;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import java.util.List;
import java.util.Random;

@Pseudo
@Mixin(targets = "com/hbm/blocks/generic/BlockAbsorber", remap = false)
@Implements(@Interface(iface = IRadResistantBlock.class, prefix = "isRadResistant$"))
public abstract class HBMAbsorber extends Block
{

    float absorb = 0;

    public HBMAbsorber(Material materialIn, float ab, String s) {
        super(materialIn);
        this.setUnlocalizedName(s);
        this.setRegistryName(s);
        this.setTickRandomly(true);
        absorb = ab;

        ModBlocks.ALL_BLOCKS.add(this);
    }

    public boolean isRadResistant$isRadResistant(World worldIn, BlockPos blockPos){
        return true;
    }

    @Override
    public int tickRate(World worldIn) {
        return 10;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        RadiationSavedData.decrementRad(world, pos, absorb);

        world.scheduleUpdate(pos, this, this.tickRate(world));
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        RadiationSystemNT.markChunkForRebuild(worldIn, pos);
        super.breakBlock(worldIn, pos, state);
    }
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        RadiationSystemNT.markChunkForRebuild(worldIn, pos);
        super.onBlockAdded(worldIn, pos, state);
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add("§2[Radiation Shielding]§r");
        float hardness = this.getExplosionResistance(null);
        if(hardness > 50){
            tooltip.add("§6Blast Resistance: "+hardness+"§r");
        }
    }


}
