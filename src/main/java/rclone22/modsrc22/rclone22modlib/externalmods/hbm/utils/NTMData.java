package rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils;

import com.hbm.capability.HbmLivingProps;
import com.hbm.potion.HbmPotion;
import com.hbm.util.ContaminationUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.main.event.minecraft.ItemEvents;


public class NTMData
{

    public static void neutranActivateInventoryChanges(EntityPlayer player) {
        if (ModChecker.isHbmModLoaded()) {
            ContaminationUtil.neutronActivateInventory(player, 0.0F, 0.0001F);
            ItemEvents.playerDoChanges(player);
        }
    }

    public static void setAllToZero(Entity e)
    {
        if (ModChecker.isHbmModLoaded()) {
            if (e instanceof EntityLivingBase) {



                EntityLivingBase elb = (EntityLivingBase) e;
                EditModdedDangerEvents.EditAsbestos editAsbestos = new EditModdedDangerEvents.EditAsbestos(elb);
                MinecraftForge.EVENT_BUS.post(editAsbestos);

                EditModdedDangerEvents.EditCoal editCoal = new EditModdedDangerEvents.EditCoal(elb);
                MinecraftForge.EVENT_BUS.post(editCoal);

                EditModdedDangerEvents.EditContagion editContagion = new EditModdedDangerEvents.EditContagion(elb);
                MinecraftForge.EVENT_BUS.post(editContagion);

                EditModdedDangerEvents.EditDigamma editDigamma = new EditModdedDangerEvents.EditDigamma(elb);
                MinecraftForge.EVENT_BUS.post(editDigamma);

                EditModdedDangerEvents.EditRadiation editRadiation = new EditModdedDangerEvents.EditRadiation(elb);
                MinecraftForge.EVENT_BUS.post(editRadiation);

                if (editAsbestos.isAsbestosImmune()) {
                    HbmLivingProps.setAsbestos(elb, 0);
                }

                if (editCoal.isCoalImmune()) {
                    HbmLivingProps.setBlackLung(elb, 0);
                }

                if (editContagion.isContagionImmune()) {
                    HbmLivingProps.setContagion(elb, 0);
                }

                if (editDigamma.isDigammaImmune()) {
                    PotionEffect current = elb.getActivePotionEffect(HbmPotion.stability);
                    if ((!ModChecker.isRclone22HbmMixinsModLoaded())) {

                        /*
                        if (current == null) {
                            elb.addPotionEffect(new PotionEffect(HbmPotion.stability, 20, 255, false, false));
                        } else if (current.getDuration() < Integer.MAX_VALUE) {
                            elb.addPotionEffect(new PotionEffect(HbmPotion.stability, 20, 255, false, false));
                        }

                         */



                    }
                    HbmLivingProps.setDigamma(elb, 0F);

                }

                if (editRadiation.isRadiationImmune()) {

                    PotionEffect current = elb.getActivePotionEffect(HbmPotion.mutation);

                    if ((!ModChecker.isRclone22HbmMixinsModLoaded())) {

                        /*
                        if (current == null) {
                            elb.addPotionEffect(new PotionEffect(HbmPotion.mutation, 20, 255, false, false));
                        } else if (current.getDuration() < Integer.MAX_VALUE) {
                            elb.addPotionEffect(new PotionEffect(HbmPotion.mutation, 20, 255, false, false));
                        }

                         */

                         
                    }

                    HbmLivingProps.setRadiation(elb, 0F);
                    HbmLivingProps.setNeutron(elb, 0F);
                   HbmLivingProps.setRadEnv(elb, 0F);
                   HbmLivingProps.setRadBuf(elb, 0F);

                    for (ItemStack stack : elb.getEquipmentAndArmor()) {

                        if (editRadiation.isRadiationImmune()) {
                            NTMData.checkAndRemoveTag(stack);
                        }

                    }

                    if (elb instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) elb;

                        for (ItemStack stack : ItemEvents.collectAllPlayerItems(entityPlayer)) {
                            if (stack.isEmpty()) continue;

                            if (editRadiation.isRadiationImmune()) {
                                NTMData.checkAndRemoveTag(stack);
                            }

                        }

                        if (editRadiation.isRadiationImmune()) {
                            NTMData.neutranActivateInventoryChanges(entityPlayer);
                        }

                        ItemEvents.playerDoChanges(entityPlayer);

                    }
                }

            }
        }
    }


    public static void checkAndRemoveTag(ItemStack stack) {
        if (!stack.isEmpty() && stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();


            if (tag != null) {
                if (tag.hasKey("NTM_NEUTRON_NBT_KEY")) {
                    tag.removeTag("NTM_NEUTRON_NBT_KEY");

                    if (tag.hasNoTags()) {
                        stack.setTagCompound(null);
                    }
                }
            }
        }


    }

}
