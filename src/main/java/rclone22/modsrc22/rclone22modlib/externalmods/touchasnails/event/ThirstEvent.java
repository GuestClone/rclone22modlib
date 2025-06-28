package rclone22.modsrc22.rclone22modlib.externalmods.touchasnails.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.thirst.ThirstHandler;

public class ThirstEvent
{

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
