package rclone22.modsrc22.rclone22modlib.externalmods.galacticraft.event;

import micdoodle8.mods.galacticraft.api.inventory.IInventoryGC;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;
import rclone22.modsrc22.rclone22modlib.main.event.minecraft.ItemEvents;

public class GCEventUtil
{

    public static void checkThermalStatus(EntityPlayerMP player, GCPlayerStats playerStats,  GCPlayerHandler gcPlayerHandler)
    {
        if (ModChecker.isMicDo2GCCoreLoaded()) {
            if (player.world.provider instanceof IGalacticraftWorldProvider && !player.capabilities.isCreativeMode && !CompatibilityManager.isAndroid(player)) {
                EditModdedDangerEvents.EditThermalStatusEvent eventThermal = new EditModdedDangerEvents.EditThermalStatusEvent(player);
                MinecraftForge.EVENT_BUS.post(eventThermal);

                if (eventThermal.isNormalThermalEventCancel()) {
                    playerStats.setThermalLevelNormalising(eventThermal.isNormalThermalEventCancel());
                    gcPlayerHandler.normaliseThermalLevel(player, playerStats, 3);
                }

                if (playerStats.getThermalLevel() >= -22 && playerStats.getThermalLevel() <= 22) {
                    playerStats.setThermalLevelNormalising(true);
                    gcPlayerHandler.normaliseThermalLevel(player, playerStats, eventThermal.getNormalThermalLevel());

                }
            }
        }
    }

    public static void isItemGCInventoryDamageable(EntityPlayer player) {
        if (ModChecker.isMicDo2GCCoreLoaded()) {
            GCPlayerStats stats = GCPlayerStats.get(player);

            IInventoryGC extendedInv = stats.getExtendedInventory();
            if (extendedInv != null) {
                for (int i = 0; i < extendedInv.getSizeInventory(); i++) {
                    ItemStack slotItem = extendedInv.getStackInSlot(i);
                    if (!slotItem.isEmpty()) {
                        if (EventItemHook.cancelItemDamageHook(player, slotItem).isEventCancelled()) {
                            ItemEvents.fixItem(slotItem, player);
                        }
                    }
                }
            }



        }

    }




}
