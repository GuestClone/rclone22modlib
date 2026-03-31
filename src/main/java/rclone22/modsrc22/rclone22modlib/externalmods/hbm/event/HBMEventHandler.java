package rclone22.modsrc22.rclone22modlib.externalmods.hbm.event;

import com.hbm.handler.RadiationSystemNT;
import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;
import rclone22.modsrc22.rclone22modlib.externalmods.hbm.blocks.HBMRadResistBlocks;
import rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils.NTMData;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.event.minecraft.ItemEvents;
import rclone22.modsrc22.rclone22modlib.main.registryutils.RegistryUtils;


public class HBMEventHandler
{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void doEventHBM(LivingEvent.LivingUpdateEvent event)
    {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase elb = (EntityLivingBase) entity;
                if (ModChecker.isHbmModLoaded()) {
                    NTMData.setAllToZero(elb);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockNeighbor(BlockEvent.NeighborNotifyEvent event) {
        if (ModChecker.isHbmModLoaded()) {


            World world = event.getWorld();
            BlockPos pos = event.getPos();
            Block block = event.getState().getBlock();

            if (HBMRadResistBlocks.isRadResistant(block)) {
                RadiationSystemNT.markChunkForRebuild(world, pos);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (ModChecker.isHbmModLoaded()) {


            World world = event.getWorld();
            BlockPos pos = event.getPos();
            Block block = event.getState().getBlock();

            if (HBMRadResistBlocks.isRadResistant(block)) {
                RadiationSystemNT.markChunkForRebuild(world, pos);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (ModChecker.isHbmModLoaded()) {

            World world = event.getWorld();
            BlockPos pos = event.getPos();
            Block block = event.getPlacedBlock().getBlock();

            if (HBMRadResistBlocks.isRadResistant(block)) {
                RadiationSystemNT.markChunkForRebuild(world, pos);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockPlaced(BlockEvent.PlaceEvent event) {
        if (ModChecker.isHbmModLoaded()) {

            World world = event.getWorld();
            BlockPos pos = event.getPos();
            Block block = event.getPlacedBlock().getBlock();

            if (HBMRadResistBlocks.isRadResistant(block)) {
                RadiationSystemNT.markChunkForRebuild(world, pos);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockPlaceMultiple(BlockEvent.MultiPlaceEvent event) {
        if (ModChecker.isHbmModLoaded()) {

            World world = event.getWorld();
            BlockPos pos = event.getPos();
            Block block = event.getPlacedBlock().getBlock();

            if (HBMRadResistBlocks.isRadResistant(block)) {
                RadiationSystemNT.markChunkForRebuild(world, pos);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockPlaceMultipleEntity(BlockEvent.EntityMultiPlaceEvent event) {
        if (ModChecker.isHbmModLoaded()) {

            World world = event.getWorld();
            BlockPos pos = event.getPos();
            Block block = event.getPlacedBlock().getBlock();

            if (HBMRadResistBlocks.isRadResistant(block)) {
                RadiationSystemNT.markChunkForRebuild(world, pos);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (ModChecker.isHbmModLoaded()) {
            ItemStack stack = event.getItemStack();
            if (stack.isEmpty()) return;

            Item item = stack.getItem();
            if (!(item instanceof ItemBlock)) return;

            Block block = ((ItemBlock) item).getBlock();
            if (HBMRadResistBlocks.isRadResistant(block)) {

                event.getToolTip().add("§2[" + I18nUtil.resolveKey("trait.radshield") + "]");

                float hardness = block.getExplosionResistance(null);
                if (hardness > 50) {
                    event.getToolTip().add("§6" + I18nUtil.resolveKey("trait.blastres", hardness));
                }
            }
        }
    }


}
