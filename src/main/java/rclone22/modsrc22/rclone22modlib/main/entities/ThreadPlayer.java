package rclone22.modsrc22.rclone22modlib.main.entities;

import net.minecraft.entity.player.EntityPlayer;

public class ThreadPlayer
{

    private static ThreadLocal<EntityPlayer> craftingPlayer = new ThreadLocal<EntityPlayer>();

    public static void setCraftingPlayer(EntityPlayer player)
    {
        craftingPlayer.set(player);
    }
    public static EntityPlayer getCraftingPlayer()
    {
        return craftingPlayer.get();
    }

}
