package rclone22.modsrc22.rclone22modlib.externalmods.touchasnails.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.temperature.TemperatureScale;
import toughasnails.temperature.TemperatureHandler;

public class TempEvent
{

    public static void callMethodTemp(Entity entity) {
        if (ModChecker.isTANModLoaded()) {
            if (!entity.world.isRemote) {
                if (entity instanceof EntityLivingBase) {
                    EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                    if (livingEntityBase instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) livingEntityBase;
                        TemperatureHandler handler = (TemperatureHandler) TemperatureHelper.getTemperatureData(entityPlayer);

                        EditModdedDangerEvents.EditTANTemp editTANTemp = new EditModdedDangerEvents.EditTANTemp(entityPlayer);
                        MinecraftForge.EVENT_BUS.post(editTANTemp);

                        if (editTANTemp.isShouldTemp()) {
                            int normalTemp = TemperatureScale.getScaleTotal() / 2;
                            handler.setTemperature(new Temperature(normalTemp));
                            handler.setChangeTime(0);
                        }

                    }
                }
            }
        }
    }

}
