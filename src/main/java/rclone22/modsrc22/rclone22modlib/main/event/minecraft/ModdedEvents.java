package rclone22.modsrc22.rclone22modlib.main.event.minecraft;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.items.CheckItemUtil;
import rclone22.modsrc22.rclone22modlib.main.items.ItemInit;
import rclone22.modsrc22.rclone22modlib.main.potions.PotionInit;
import rclone22.modsrc22.rclone22modlib.main.potions.PotionUtil;
import rclone22.modsrc22.rclone22modlib.main.registryutils.RegistryUtils;


public class ModdedEvents
{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void cancelModdedDanger(EditModdedDangerEvents event) {
        Entity e = event.getEntity();
        if (e != null && e.world != null && !e.world.isRemote)  {
            if (e instanceof EntityLivingBase) {
                boolean shouldCancelEvent = false;
                EntityLivingBase eLB = (EntityLivingBase) e;
                if (AbsResEntityAttributes.hasAbsResAttrResistanceEffect(eLB)) {
                    shouldCancelEvent = true;
                }

                PotionEffect potionEffectA = PotionUtil.hasPotionEffectAbsoluteResistanceMethodTrue(eLB);

                if (potionEffectA != null) {

                    shouldCancelEvent = true;
                }

                if (CheckItemUtil.isWearingItemByModId(eLB, EntityEquipmentSlot.HEAD, Constant.MODID, "meta_helmet"))
                {

                    shouldCancelEvent = true;
                }

                if (CheckItemUtil.isWearingItemByModId(eLB, EntityEquipmentSlot.HEAD, ModChecker.AVARITIA, "infinity_helmet") &&
                        CheckItemUtil.isWearingItemByModId(eLB, EntityEquipmentSlot.CHEST, ModChecker.AVARITIA, "infinity_chestplate") &&
                        CheckItemUtil.isWearingItemByModId(eLB, EntityEquipmentSlot.LEGS, ModChecker.AVARITIA, "infinity_pants") &&
                        CheckItemUtil.isWearingItemByModId(eLB, EntityEquipmentSlot.FEET, ModChecker.AVARITIA, "infinity_boots"))
                {

                    shouldCancelEvent = true;
                }

                if (shouldCancelEvent) {
                    event.setCanceled(true);
                }

                if (eLB instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) eLB;
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void thermalEvent(EditModdedDangerEvents.EditHungerEvent event) {
        Entity e = event.e;
        if (!e.world.isRemote) {
            if (e instanceof EntityLivingBase) {
                EntityLivingBase eLB = (EntityLivingBase) e;
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void radiationResistance(EditModdedDangerEvents.EditRadiation event) {
        Entity e = event.getEntity();
        if (!e.world.isRemote) {
            if (e instanceof EntityLivingBase) {
                EntityLivingBase eLB = (EntityLivingBase) e;
                if (eLB.isPotionActive(MobEffects.RESISTANCE))
                {
                   ///  event.setRadiationResistanceBetterVers(100.0F);
                }
            }
        }
    }

}
