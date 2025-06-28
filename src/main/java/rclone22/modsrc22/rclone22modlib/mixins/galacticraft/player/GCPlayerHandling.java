package rclone22.modsrc22.rclone22modlib.mixins.galacticraft.player;

import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rclone22.modsrc22.rclone22modlib.api.event.EditModdedDangerEvents;

@Pseudo
@Mixin(targets = "micdoodle8/mods/galacticraft/core/entities/player/GCPlayerHandler", remap = false)
public abstract class GCPlayerHandling
{

    /**
     *  If error about playerStats.getExtendedInventory().getStackInSlot(int);
     *  then it should be replaced to adding (IInventory)
     * ((IInventory) playerStats.getExtendedInventory()).getStackInSlot(init);
    **/

    @Unique
    GCPlayerHandler r22hbmmixin$gcPlayerHandling = (GCPlayerHandler) (Object) this;

    @Inject(method = "checkThermalStatus", at = @At("HEAD"), remap = false)
    protected void checkThermalStatus(EntityPlayerMP player, GCPlayerStats playerStats, CallbackInfo ci)
    {
        EditModdedDangerEvents.EditThermalStatusEvent eventThermal = new EditModdedDangerEvents.EditThermalStatusEvent(player);
        MinecraftForge.EVENT_BUS.post(eventThermal);

        if (eventThermal.isNormalThermalEventCancel()) {
            playerStats.setThermalLevelNormalising(true);
            r22hbmmixin$gcPlayerHandling.normaliseThermalLevel(player, playerStats, 3);
        }

        if (playerStats.getThermalLevel() >= -22 && playerStats.getThermalLevel() <= 22) {
            playerStats.setThermalLevelNormalising(true);
            r22hbmmixin$gcPlayerHandling.normaliseThermalLevel(player, playerStats, eventThermal.getNormalThermalLevel());
        }
    }

    @Inject(method = "checkShield", at = @At("HEAD"), cancellable = true, remap = false)
    public void checkShield(EntityPlayerMP playerMP, GCPlayerStats playerStats, CallbackInfo ci)
    {
        EditModdedDangerEvents.EditCheckShieldEvent event = new EditModdedDangerEvents.EditCheckShieldEvent(playerMP);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isEventCancelled()) {
            ci.cancel();
        }
    }


}
