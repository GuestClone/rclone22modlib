package rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils;

import com.hbm.capability.HbmLivingProps;
import com.hbm.util.ContaminationUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;

public class NTMData
{

    public static void neutranActivateInventoryChanges(EntityPlayer player, float rad, float decay) {
        if (ModChecker.isHbmModLoaded()) {
            ContaminationUtil.neutronActivateInventory(player, rad, decay);
            player.inventoryContainer.detectAndSendChanges();
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
                    HbmLivingProps.setDigamma(elb, 0F);
                }

                if (editRadiation.isRadiationImmune()) {
                    HbmLivingProps.setRadiation(elb, 0F);
                    HbmLivingProps.setNeutron(elb, 0F);
                    HbmLivingProps.setRadEnv(elb, 0F);

                    if (elb instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) elb;
                        NTMData.neutranActivateInventoryChanges(entityPlayer, 0.0F, 0.0001F);
                    }
                }
            }
        }
    }

}
