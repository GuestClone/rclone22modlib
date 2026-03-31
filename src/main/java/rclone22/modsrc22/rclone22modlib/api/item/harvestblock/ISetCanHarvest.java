package rclone22.modsrc22.rclone22modlib.api.item.harvestblock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

///  Harvest any block regardless or no matter what tool or block
public interface ISetCanHarvest
{

    interface ICustomHarvestCheck {
        boolean canAlwaysHarvest(ItemStack stack);
    }

   interface ICustomHarvestCheckEnchant {
        boolean canAlwaysHarvest(ItemStack stack);
    }

}
