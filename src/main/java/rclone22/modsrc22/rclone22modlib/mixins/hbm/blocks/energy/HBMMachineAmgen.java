package rclone22.modsrc22.rclone22modlib.mixins.hbm.blocks.energy;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.BlockDeconRad;
import com.hbm.blocks.machine.MachineAmgen;
import com.hbm.handler.RadiationSystemNT;
import com.hbm.interfaces.IRadResistantBlock;
import com.hbm.tileentity.machine.TileEntityMachineAmgen;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import java.util.List;

@Pseudo
@Mixin(targets = "com/hbm/blocks/machine/MachineAmgen", remap = false)
@Implements(@Interface(iface = IRadResistantBlock.class, prefix = "isRadResistant$"))
public abstract class HBMMachineAmgen extends BlockContainer
{

    public HBMMachineAmgen(Material materialIn, String s) {
        super(materialIn);
        this.setUnlocalizedName(s);
        this.setRegistryName(s);

        ModBlocks.ALL_BLOCKS.add(this);
    }

    public boolean isRadResistant$isRadResistant(World worldIn, BlockPos blockPos){
        return true;
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

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        RadiationSystemNT.markChunkForRebuild(worldIn, pos);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        RadiationSystemNT.markChunkForRebuild(worldIn, pos);
        super.onBlockAdded(worldIn, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMachineAmgen();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

}
