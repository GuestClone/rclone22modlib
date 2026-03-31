package rclone22.modsrc22.rclone22modlib.main.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import rclone22.modsrc22.rclone22modlib.main.registryutils.ClassRegister;

public class ClientProxy extends CommonProxy
{

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ResourceLocation registryName = item.getRegistryName();
        if (registryName != null) {
            ModelLoader.setCustomModelResourceLocation(
                    item,
                    meta,
                    new ModelResourceLocation(registryName, id)
            );
        }
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {

        clientEvents();
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {

        clientEvents();
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        clientEvents();
        super.postInit(event);
    }

    public static void clientEvents()
    {
        ClassRegister.registEventClassByString(
                new String[]{"minecraft"},"rclone22.modsrc22.rclone22modlib.client.ClientEventsAndRegistry"
        );
    }

}
