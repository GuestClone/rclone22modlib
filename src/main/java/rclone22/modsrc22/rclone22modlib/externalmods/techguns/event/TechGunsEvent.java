package rclone22.modsrc22.rclone22modlib.externalmods.techguns.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;
import rclone22.modsrc22.rclone22modlib.main.event.minecraft.ItemEvents;
import techguns.capabilities.TGExtendedPlayer;
import techguns.gui.player.TGPlayerInventory;

public class TechGunsEvent
{

    ///  Overkilled coding here btw, looks like cluster of nonsense
    @SubscribeEvent
    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        if (ModChecker.isTechgunsModLoaded()) {
            Entity entity = event.getEntity();
            if (entity != null && entity.world != null && !entity.world.isRemote) {
                if (entity instanceof EntityLivingBase) {
                    EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                    if (livingEntityBase instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) livingEntityBase;

                        TGExtendedPlayer props = TGExtendedPlayer.get(entityPlayer);

                        if (props != null && props.tg_inventory != null) {

                            NonNullList<ItemStack> inv = props.tg_inventory.inventory;

                            int[] gearSlots = {
                                    TGPlayerInventory.SLOT_FACE,
                                    TGPlayerInventory.SLOT_BACK,
                                    TGPlayerInventory.SLOT_HAND,
                                    TGPlayerInventory.SLOTS_AUTOFOOD_START,
                                    TGPlayerInventory.SLOTS_AUTOFOOD_END,
                                    TGPlayerInventory.SLOT_AUTOHEAL,
                                    TGPlayerInventory.SLOTS_AMMO_START,
                                    TGPlayerInventory.SLOTS_AMMO_END,
                            };

                            for (int slot : gearSlots) {
                                if (slot < inv.size()) {
                                    ItemStack stack = inv.get(slot);
                                    if (!stack.isEmpty() && EventItemHook.cancelItemDamageHook(entityPlayer, stack).isEventCancelled()) {
                                      //  ItemEvents.fixItem(stack, entityPlayer);
                                    }
                                }
                            }

                            for (int i = 0; i < props.tg_inventory.inventory.size(); i++) {
                                ItemStack stack = props.tg_inventory.inventory.get(i);
                                if (!stack.isEmpty()) {
                                    if (EventItemHook.cancelItemDamageHook(entityPlayer, stack).isEventCancelled()) {
                                   //     ItemEvents.fixItem(stack, entityPlayer);

                                    }
                                }
                            }
                        }
                        ItemEvents.playerDoChanges(entityPlayer);
                    }

                }
            }
        }
    }

}
