package rclone22.modsrc22.rclone22modlib.main.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.main.Constant;

public class CheckBlockUtil
{

    public static Block tryLoadBlock(String modid, String registryName) {
        if (modid == null || registryName == null || modid.isEmpty() || registryName.isEmpty()) {
            return null;
        }

        if (!ModChecker.isModPresent(modid)) {
            return null;
        }

        ResourceLocation rl;
        try {
            rl = new ResourceLocation(modid, registryName);
        } catch (Exception e) {
            System.err.println("[ModCompat:" + Constant.MODID + "]: Optional Dependant Block not loaded = " + modid + ":" + registryName);
            e.printStackTrace();
            return null;
        }

        if (!Block.REGISTRY.containsKey(rl)) {
            return null;
        }

        return Block.REGISTRY.getObject(rl);
    }

    public static Block tryLoadBlockMeta(String modid, String registryName, int meta) {
        Block block = tryLoadBlock(modid, registryName);
        if (block == null) return null;

        try {
            block.getStateFromMeta(meta);
        } catch (Exception ignored) {
            System.err.println("Invalid metadata " + meta + " for block " + modid + ":" + registryName);
        }

        return block;
    }

}
