package rclone22.modsrc22.rclone22modlib.api.event.entityevent;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

/// This one is like the LivingEvent.LivingUpdateEvent but broader as it targets any Entity
///
/// EntityDeathEvent is fired when an (specific )Entity dies.
///
/// This event will update the death mechanic in the Entity.java class itself -- Via by Mixins --
///
/// source contains the DamageSource that caused the entity to die.
///
/// This event is Cancelable.
/// If this event is canceled, the Entity does not die.
///
/// This event does not have a result. net.minecraftforge.fml.common.eventhandler.Event.HasResult
///
/// This event is fired on the MinecraftForge.EVENT_BUS
@Cancelable
public class EntityDeathEvent extends EntityEvent {


    public EntityDeathEvent(Entity entity) {
        super(entity);
    }

}
