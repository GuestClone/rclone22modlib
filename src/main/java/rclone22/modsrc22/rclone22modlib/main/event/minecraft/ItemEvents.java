package rclone22.modsrc22.rclone22modlib.main.event.minecraft;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.ExplosionEvent;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.SoftEMDEHook;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EvenCancelsItemStackDamage;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventCancelsEntityItemDamage;
import rclone22.modsrc22.rclone22modlib.api.event.itemevents.EventItemHook;
import rclone22.modsrc22.rclone22modlib.api.item.IIsInvulnerableUtil;
import rclone22.modsrc22.rclone22modlib.api.item.NbtItemUtil;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.CanHarvestUtil;
import rclone22.modsrc22.rclone22modlib.api.item.harvestblock.ISetCanHarvest;
import rclone22.modsrc22.rclone22modlib.api.item.harvestlevel.IGetSetHarvestLevel;
import rclone22.modsrc22.rclone22modlib.api.item.harvestlevel.NBTHarvestUtil;
import rclone22.modsrc22.rclone22modlib.externalmods.baubles.IIsBaubleCancelsDanger;
import rclone22.modsrc22.rclone22modlib.externalmods.baubles.NBTBaubleUtils;
import techguns.capabilities.TGExtendedPlayer;
import techguns.gui.player.TGPlayerInventory;
import xzeroair.trinkets.api.TrinketHelper;
import xzeroair.trinkets.capabilities.InventoryContainerCapability.ITrinketContainerHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class ItemEvents
{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void itemEntityCancel(EventCancelsEntityItemDamage event){
        if (isItemStackNoDam(event.getItemStack())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void itemStackCancel(EvenCancelsItemStackDamage event){
        ItemStack stack = event.getStack();

        boolean isEventCanceled = false;

        if (isItemStackNoDam(stack)) {
           isEventCanceled = true;
        }

       if (NbtItemUtil.getBooleanNbtTagString(stack, "Unbreakable"))
       {
           isEventCanceled = true;
       }

        if (isEventCanceled)
        {
            event.setCanceled(true);
        }

    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        List<Entity> affectedEntities = event.getAffectedEntities();




        affectedEntities.removeIf(entity -> {
            if (entity instanceof EntityItem) {
                EntityItem entityItemI = (EntityItem) entity;
                ItemStack stack = entityItemI.getItem();

                return EventItemHook.cancelEntityItemDamageHook(entityItemI, stack).isEventCancelled();
            }

            if (entity instanceof EntityLivingBase) {
               EntityLivingBase entityLivingBase = (EntityLivingBase) entity;



                return SoftEMDEHook.softEMDEHookGetterObject(entityLivingBase, "editModdedDangerEvents", "isDamageCancelled", Boolean.class);
            }

            return false;
        });



    }



    public static void onInventoryTick(LivingEvent.LivingUpdateEvent event)
    {

        /*
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
           if (entity instanceof EntityLivingBase)
           {
               EntityLivingBase entityLivingBase = (EntityLivingBase) entity;



               for (ItemStack stack : entityLivingBase.getEquipmentAndArmor()) {

                   if (EventItemHook.cancelItemDamageHook(entityLivingBase, stack).isEventCancelled()) {
                       fixItem(stack, entityLivingBase);
                   }

               }

               if (entity instanceof EntityPlayer) {
                   EntityPlayer player = (EntityPlayer) entityLivingBase;

                   for (ItemStack stack : ItemEvents.collectAllPlayerItems(player)) {
                       if (stack.isEmpty()) continue;

                       if (EventItemHook.cancelItemDamageHook(entityLivingBase, stack).isEventCancelled()) {
                           fixItem(stack, entityLivingBase);
                       }

                   }

               }

           }


        }

         */


    }

    public static void EntityItemsUpdate(EntityItem entity)
    {
        ItemStack stack = entity.getItem();

        if (EventItemHook.cancelEntityItemDamageHook(entity, stack).isEventCancelled()) {
            entity.setNoDespawn();
            entity.setFire(0);
            entity.extinguish();
            entity.lifespan = Integer.MAX_VALUE;
        }

        if (EventItemHook.cancelEntityItemDamageHook(entity, stack).isEventCancelled()) {
            entity.setEntityInvulnerable(true);
        } else if (!EventItemHook.cancelEntityItemDamageHook(entity, stack).isEventCancelled())
        {
            entity.setEntityInvulnerable(false);
        }

    }

    private static boolean isItemStackNoDam(ItemStack stack)
    {
            return NbtItemUtil.isItemInvulnerable(stack);

    }

    public static void fixItem(ItemStack stack, Entity entity) {
        /*
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
                if (!stack.isEmpty()) {
                    stack.setItemDamage(0);
                    stack.damageItem(0, entityLivingBase);
                }
                if (entity instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) entityLivingBase;
                    playerDoChanges(entityPlayer);
                }
        }

         */
    }

    public static void onAddNbtTick(EntityLivingBase entity) {
        if (!entity.world.isRemote) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) entity;

                boolean modified = false;

                for (ItemStack stack : collectAllPlayerItems(entityPlayer)) {
                    if (stack.isEmpty()) continue;

                    if (updateInvulnerabilityNBT(stack)) {
                        modified = true;
                    }

                    if (updateHarvestLevelNBT(stack)) {
                        modified = true;
                    }

                    if (updateCanHarvestUtilNBT(stack)) {
                        modified = true;
                    }

                    if (updateBaubleNbt(stack)) {
                        modified = true;
                    }

                }

                if (modified) {
                    playerDoChanges(entityPlayer);
                }

                playerDoChanges(entityPlayer);

            }
        }
    }

    ///  Affects the whole inventory
    public static List<ItemStack> collectAllPlayerItems(EntityPlayer player) {
        List<ItemStack> items = new ArrayList<>(Arrays.asList(player.getHeldItemMainhand(), player.getHeldItemOffhand()));
        items.addAll(player.inventory.armorInventory);
        items.addAll(player.inventory.mainInventory);
        items.addAll(player.inventory.offHandInventory);

        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            items.add(player.inventory.getStackInSlot(i));
        }

        InventoryEnderChest enderChest = player.getInventoryEnderChest();


        for (int i = 0; i < enderChest.getSizeInventory(); i++) {
            items.add(enderChest.getStackInSlot(i));
        }


        if (ModChecker.isTrinketsAndBaublesModLoaded())
        {
            ITrinketContainerHandler trinkets = TrinketHelper.getTrinketHandler(player);
            if (trinkets != null) {
                for (int i = 0; i < trinkets.getSlots(); i++) {
                    ItemStack stack = trinkets.getStackInSlot(i);
                    items.add(stack);
                }
            }
        }

        if (ModChecker.isBaublesModLoaded())
        {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            if (baubles != null) {
                for (int i = 0; i < baubles.getSlots(); i++) {
                    ItemStack stack = baubles.getStackInSlot(i);
                    items.add(stack);
                }
            }
        }

        if (ModChecker.isMicDo2GCCoreLoaded()) {
            GCPlayerStats stats = GCPlayerStats.get(player);
            IInventory extendedInv = stats.getExtendedInventory();
            if (extendedInv != null) {
                for (int i = 0; i < extendedInv.getSizeInventory(); i++) {
                    ItemStack slotItem = extendedInv.getStackInSlot(i);
                    if (!slotItem.isEmpty()) {
                        items.add(slotItem);
                    }
                }
            }
        }

        if (ModChecker.isTechgunsModLoaded())
        {
            TGExtendedPlayer props = TGExtendedPlayer.get(player);
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
                        items.add(stack);
                    }
                }
                for (int i = 0; i < props.tg_inventory.inventory.size(); i++) {
                    ItemStack stack = props.tg_inventory.inventory.get(i);
                    if (!stack.isEmpty()) {
                        items.add(stack);
                    }
                }
            }
        }

        return items;
    }

    private static boolean updateBaubleNbt(ItemStack stack) {
        boolean modified = false;
        boolean foundBaubleEnchant= false;

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        if (!enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment enchant = entry.getKey();

                if (enchant instanceof IIsBaubleCancelsDanger.IEnchBaubleCancelDanger) {
                    IIsBaubleCancelsDanger.IEnchBaubleCancelDanger invul = (IIsBaubleCancelsDanger.IEnchBaubleCancelDanger) enchant;
                    if (invul.shouldCancelDangerEnch(stack)) {
                        foundBaubleEnchant = true;
                    }
                }
            }
        }

        if (foundBaubleEnchant)
        {
            ensureNBT(stack).setBoolean(NBTBaubleUtils.IsEnchBaubleSBP, true);
            modified = true;
        } else {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag != null && tag.hasKey(NBTBaubleUtils.IsEnchBaubleSBP)) {
                tag.removeTag(NBTBaubleUtils.IsEnchBaubleSBP);
                modified = true;
            }
        }

        return modified;
    }

    private static boolean updateInvulnerabilityNBT(ItemStack stack) {
        boolean modified = false;
        boolean foundInvulEnchant = false;

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        if (!enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment enchant = entry.getKey();

                if (enchant instanceof IIsInvulnerableUtil.IIsEnchantItemInvul) {
                    IIsInvulnerableUtil.IIsEnchantItemInvul invul = (IIsInvulnerableUtil.IIsEnchantItemInvul) enchant;
                    if (invul.isEnchInvulnerable(stack)) {
                        foundInvulEnchant = true;
                    }
                }
            }
        }

        if (foundInvulEnchant)
        {
            ensureNBT(stack).setBoolean(NbtItemUtil.isEnchantItemInvul, true);
            modified = true;
        } else {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag != null && tag.hasKey(NbtItemUtil.isEnchantItemInvul)) {
                tag.removeTag(NbtItemUtil.isEnchantItemInvul);
                modified = true;
            }
        }

        return modified;
    }

    private static boolean updateCanHarvestUtilNBT(ItemStack stack) {
        boolean modified = false;
        boolean foundHarvestEnchant = false;

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        if (!enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment enchant = entry.getKey();

                if (enchant instanceof ISetCanHarvest.ICustomHarvestCheckEnchant) {
                    ISetCanHarvest.ICustomHarvestCheckEnchant canHarvest = (ISetCanHarvest.ICustomHarvestCheckEnchant) enchant;
                    if (canHarvest.canAlwaysHarvest(stack)) {
                        foundHarvestEnchant = true;
                    }
                }
            }
        }

        if (foundHarvestEnchant)
        {
            ensureNBT(stack).setBoolean(CanHarvestUtil.canEnchantHarvestBlockTool, true);
            modified = true;
        } else {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag != null && tag.hasKey(CanHarvestUtil.canEnchantHarvestBlockTool)) {
                tag.removeTag(CanHarvestUtil.canEnchantHarvestBlockTool);
                modified = true;
            }
        }

        return modified;
    }

    private static boolean updateHarvestLevelNBT(ItemStack stack) {
        boolean modified = false;
        int highestHarvestLevel = -1;

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        if (!enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment enchant = entry.getKey();


                if (enchant instanceof IGetSetHarvestLevel.IEnchantHarvestLevel) {
                    IGetSetHarvestLevel.IEnchantHarvestLevel harvest = (IGetSetHarvestLevel.IEnchantHarvestLevel) enchant;
                    int level = harvest.getHarvestLevelFromEnchant(stack, entry.getValue());
                    if (level > highestHarvestLevel) {
                        highestHarvestLevel = level;
                    }
                }
            }
        }


        if (highestHarvestLevel >= 0) {
            ensureNBT(stack).setInteger(NBTHarvestUtil.modifyEnchantHarvestLevel, highestHarvestLevel);
            modified = true;
        } else {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag != null && tag.hasKey(NBTHarvestUtil.modifyEnchantHarvestLevel)) {
                tag.removeTag(NBTHarvestUtil.modifyEnchantHarvestLevel);
                modified = true;
            }
        }

        return modified;
    }

    private static NBTTagCompound ensureNBT(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }
        return tag;
    }

    public static void playerDoChanges(EntityPlayer player)
    {
        player.inventoryContainer.detectAndSendChanges();
        player.inventory.markDirty();
    }



}
