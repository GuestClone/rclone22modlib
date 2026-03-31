package rclone22.modsrc22.rclone22modlib.externalmods.hbm.blocks;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.main.blocks.CheckBlockUtil;

import java.util.HashSet;
import java.util.Set;

public class HBMRadResistBlocks
{
    private static final Set<Block> RAD_RESISTANT_BLOCKS = new HashSet<>();

    public static void register(Block block) {
        RAD_RESISTANT_BLOCKS.add(block);
    }

    public static boolean isRadResistant(Block block) {
        return RAD_RESISTANT_BLOCKS.contains(block);
    }

    public static void register()
    {
        HBMRadResistBlocks.register(Blocks.BEDROCK);
        HBMRadResistBlocks.register(Blocks.COMMAND_BLOCK);
        HBMRadResistBlocks.register(Blocks.CHAIN_COMMAND_BLOCK);
        HBMRadResistBlocks.register(Blocks.REPEATING_COMMAND_BLOCK);


    }

}
