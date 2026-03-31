package rclone22.modsrc22.rclone22modlib.client;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.items.ItemInit;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Constant.MODID, value = Side.CLIENT)
public class ClientEventsAndRegistry
{

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void modelRegistry(ModelRegistryEvent event)
    {
        ClientEventsAndRegistry.itemsToBeRegis();
    }

    public static void itemsToBeRegis()
    {
        for (Item item : ItemInit.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }
    }


}

