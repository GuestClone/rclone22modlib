package rclone22.modsrc22.rclone22modlib.client;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rclone22.modsrc22.rclone22modlib.main.Constant;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Constant.MODID, value = Side.CLIENT)
public class ClientRegistry
{

    public ClientRegistry(){}

    @SubscribeEvent
    public void modelRegistry(ModelRegistryEvent event)
    {
        ItemModelRegistry.itemsToBeRegis();
    }

}
