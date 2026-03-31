package rclone22.modsrc22.rclone22modlib.api.event.entityevent;


import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;


///  This one is like the LivingEvent.LivingUpdateEvent but broader as it targets any Entity
///
/// EntityUpdateEvent is fired when an Entity is updated.
///
/// This event is fired whenever an Entity is updated in Entity.onUpdate().
///
/// This event is fired directly to Entity.onUpdate() via -MIXINS-.
///
/// This event is Cancelable.
/// If this event is canceled, the Entity does not update.
///
/// This event does not have a result. Event.HasResult
///
/// This event is fired on the MinecraftForge.EVENT_BUS.
@Cancelable
public class EntityUpdateEvent extends EntityEvent {

    public EntityUpdateEvent(Entity entity) {
        super(entity);
    }

}
