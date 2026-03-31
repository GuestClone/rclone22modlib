package rclone22.modsrc22.rclone22modlib.externalmods.trinketsandbaubles;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.externalmods.baubles.NBTBaubleUtils;
import xzeroair.trinkets.api.TrinketHelper;
import xzeroair.trinkets.capabilities.InventoryContainerCapability.ITrinketContainerHandler;
import xzeroair.trinkets.util.interfaces.IAccessoryInterface;

public class TrinketsBaubleEvent
{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void cancelModdedDanger(EditModdedDangerEvents event) {
        if (ModChecker.isTrinketsAndBaublesModLoaded()) {
            Entity e = event.getEntity();
            if (e != null && e.world != null && !e.world.isRemote) {
                if (e instanceof EntityLivingBase) {
                    EntityLivingBase eLB = (EntityLivingBase) e;
                    if (eLB instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) eLB;

                        ITrinketContainerHandler trinkets = TrinketHelper.getTrinketHandler(entityPlayer);
                        if (trinkets != null) {
                            for (int i = 0; i < trinkets.getSlots(); i++) {
                                ItemStack stack = trinkets.getStackInSlot(i);
                                if (!stack.isEmpty() && NBTBaubleUtils.isBaubleHasNbt(stack)) {
                                    if (NBTBaubleUtils.isBaubleHasNbt(stack)) {
                                        event.setCanceled(true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
