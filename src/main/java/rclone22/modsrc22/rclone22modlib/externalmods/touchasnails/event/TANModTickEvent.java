package rclone22.modsrc22.rclone22modlib.externalmods.touchasnails.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import rclone22.modsrc22.rclone22modlib.main.registryutils.RegistryUtils;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.temperature.TemperatureScale;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.temperature.TemperatureHandler;
import toughasnails.thirst.ThirstHandler;


public class TANModTickEvent
{

    @SubscribeEvent
    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        if (ModChecker.isTANModLoaded()) {
            Entity entity = event.getEntity();

            if (!entity.world.isRemote) {
                if (entity instanceof EntityLivingBase) {
                    EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

                    TANModTickEvent.callMethodTemp(livingEntityBase);
                    TANModTickEvent.callMethodThirst(livingEntityBase);

                }
            }
        }
    }

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

    public static void callMethodThirst(Entity entity) {
        if (ModChecker.isTANModLoaded()) {
            if (!entity.world.isRemote) {
                if (entity instanceof EntityLivingBase) {
                    EntityLivingBase livingEntityBase = (EntityLivingBase) entity;
                    if (livingEntityBase instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) livingEntityBase;
                        ThirstHandler handler = (ThirstHandler) ThirstHelper.getThirstData(entityPlayer);

                        EditModdedDangerEvents.EditTANThirst editTANThirst = new EditModdedDangerEvents.EditTANThirst(entityPlayer);
                        MinecraftForge.EVENT_BUS.post(editTANThirst);

                        if (editTANThirst.isShouldThirst()) {
                            handler.addStats(20, 20F);
                            handler.setThirst(20);
                            handler.setHydration(20);
                            handler.setChangeTime(0);
                            handler.setExhaustion(0F);
                        }

                    }
                }
            }
        }
    }


}
