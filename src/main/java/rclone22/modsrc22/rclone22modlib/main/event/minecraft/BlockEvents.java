package rclone22.modsrc22.rclone22modlib.main.event.minecraft;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.CanHarvestUtil;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.registryutils.RegistryUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockEvents
{

    //  Blocks.BEDROCK.setHardness(0); // makes bedrock be mineable



    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack held = player.getHeldItemMainhand();

        if (CanHarvestUtil.canForceHarvest(held)) {
            event.setCanHarvest(true);
            event.setResult(Event.Result.ALLOW);
        }

    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public void onPlayerLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        ItemStack held = event.getEntityPlayer().getHeldItem(event.getHand());

        if (CanHarvestUtil.canForceHarvest(held)) {
            float originalHardness = block.getBlockHardness(state, world, pos);
            if (originalHardness < 0) {
                originalHardness = 50.0f;

            }

            float newHardness = originalHardness;
            block.setHardness(newHardness);
        }
    }


    /*
    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
        Block block = event.getState().getBlock();

        if (CanHarvestUtil.canForceHarvest(stack)) {
            float hardness = block.getBlockHardness(event.getState(), event.getEntityPlayer().world, event.getPos());
            if (hardness < 0) hardness = 50.0f;
            event.setNewSpeed(event.getOriginalSpeed() / (1.0f + hardness));
        } else {

            event.setNewSpeed(0);
        }
    }
    */



}
