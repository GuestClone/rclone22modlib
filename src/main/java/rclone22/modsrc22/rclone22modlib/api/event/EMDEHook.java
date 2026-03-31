package rclone22.modsrc22.rclone22modlib.api.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;



///  Or
/// "EditModdedDangerEventsHook"
public class EMDEHook
{

    /// TODO: DO NOT EDIT THE NAMES OF THE METHODS SINCE THEY ARE SOFT CALLED TO A SOFT HOOK GETTER

    public static EditModdedDangerEvents editModdedDangerEvents(Entity e)
    {
        EditModdedDangerEvents eventLLL = new EditModdedDangerEvents(e);
        MinecraftForge.EVENT_BUS.post(eventLLL);

        return eventLLL;
    }

    public static EditModdedDangerEvents.EditRadiation radiationHBMModEvents(Entity e)
    {
        EditModdedDangerEvents.EditRadiation event = new EditModdedDangerEvents.EditRadiation(e);
        MinecraftForge.EVENT_BUS.post(event);

        return event;
    }

    public static EditModdedDangerEvents.EditDigamma digammaModEvents(Entity e)
    {
        EditModdedDangerEvents.EditDigamma event = new EditModdedDangerEvents.EditDigamma(e);
        MinecraftForge.EVENT_BUS.post(event);

        return event;
    }

    public static EditModdedDangerEvents.EditGCOxygenEvent oxygenEventModGC(Entity e)
    {

        EditModdedDangerEvents.EditGCOxygenEvent editEvent = new EditModdedDangerEvents.EditGCOxygenEvent(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }

    public static EditModdedDangerEvents.EditHungerEvent hungerMCModEvent(EntityPlayer e)
    {
        EditModdedDangerEvents.EditHungerEvent editEvent = new EditModdedDangerEvents.EditHungerEvent(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }

    public static EditModdedDangerEvents.EditThermalStatusEvent thermalStatGCEvent(Entity e)
    {
        EditModdedDangerEvents.EditThermalStatusEvent editEvent = new EditModdedDangerEvents.EditThermalStatusEvent(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }

    public static EditModdedDangerEvents.EditAsbestos asbestosHBMModEvent(Entity e)
    {
        EditModdedDangerEvents.EditAsbestos editEvent = new EditModdedDangerEvents.EditAsbestos(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }

    public static EditModdedDangerEvents.EditCoal coalHBMModEvent(Entity e)
    {
        EditModdedDangerEvents.EditCoal editEvent = new EditModdedDangerEvents.EditCoal(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }

    public static EditModdedDangerEvents.EditFaraday faradayBMModEvent(Entity e)
    {
        EditModdedDangerEvents.EditFaraday editEvent = new EditModdedDangerEvents.EditFaraday(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }

    public static EditModdedDangerEvents.EditHazmat hazmatBMModEvent(Entity e)
    {
        EditModdedDangerEvents.EditHazmat editEvent = new EditModdedDangerEvents.EditHazmat(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }

    public static EditModdedDangerEvents.EditContagion contagionHBMModEvent(Entity e)
    {
        EditModdedDangerEvents.EditContagion editEvent = new EditModdedDangerEvents.EditContagion(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }

    public static EditModdedDangerEvents.EditTANTemp tempTANModEvent(Entity e)
    {
        EditModdedDangerEvents.EditTANTemp editEvent = new EditModdedDangerEvents.EditTANTemp(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }

    public static EditModdedDangerEvents.EditTANThirst thirstTANModEvent(Entity e)
    {
        EditModdedDangerEvents.EditTANThirst editEvent = new EditModdedDangerEvents.EditTANThirst(e);
        MinecraftForge.EVENT_BUS.post(editEvent);

        return editEvent;
    }




}
