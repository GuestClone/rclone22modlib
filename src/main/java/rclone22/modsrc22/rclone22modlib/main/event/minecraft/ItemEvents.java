package rclone22.modsrc22.rclone22modlib.main.event.minecraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.world.ExplosionEvent;

import rclone22.modsrc22.rclone22modlib.api.item.NbtItemUtil;

import java.util.List;

public class ItemEvents
{

    public static void itemExpireEvent(ItemExpireEvent event)
    {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote && entity instanceof EntityItem) {
            EntityItem entityItemI = (EntityItem) entity;
            ItemStack stack = entityItemI.getItem();

            if (NbtItemUtil.isItemInvulnerable(stack)) {
                    event.setExtraLife(36000);
                    entityItemI.setFire(0);
                    entityItemI.extinguish();
                    entityItemI.isDead = false;
                    entityItemI.isEntityAlive();
                    event.setCanceled(true);
            }
        }
    }

    public static void onItemToss(ItemTossEvent event) {
        Entity entity = event.getEntity();
        if (!entity.world.isRemote) {
            EntityItem entityItemI = (EntityItem) entity;
            ItemStack stack = entityItemI.getItem();

            if (NbtItemUtil.isItemInvulnerable(stack)) {
                    entityItemI.setEntityInvulnerable(true);
                    entityItemI.setFire(0);
                    entityItemI.extinguish();
                    entityItemI.isDead = false;
                    entityItemI.isEntityAlive();
            }
        }
    }

    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        List<Entity> affectedEntities = event.getAffectedEntities();

        affectedEntities.removeIf(entity -> {
            if (entity instanceof EntityItem) {
                ItemStack stack = ((EntityItem) entity).getItem();

                return NbtItemUtil.isItemInvulnerable(stack);
            }

            return false;
        });
    }

}
