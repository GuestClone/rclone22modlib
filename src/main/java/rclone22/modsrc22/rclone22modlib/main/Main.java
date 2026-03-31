package rclone22.modsrc22.rclone22modlib.main;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.item.invunerableitem.ItemInvulRegistry;
import rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils.ArmorUtil;
import rclone22.modsrc22.rclone22modlib.main.commands.CommandCheckAttribute;
import rclone22.modsrc22.rclone22modlib.main.oredict.OreDictInit;
import rclone22.modsrc22.rclone22modlib.main.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;
import rclone22.modsrc22.rclone22modlib.main.registry.RegistryInitsHandler;
import rclone22.modsrc22.rclone22modlib.main.registry.RegistryPreInitHandler;
import rclone22.modsrc22.rclone22modlib.main.registryutils.ClassRegister;
import rclone22.modsrc22.rclone22modlib.mixins.MixinsLoadingPlugin;


@Mod
        (
        modid = Constant.MODID,
        name = Constant.MOD_NAME,
        version = Constant.MOD_VERSION,
                dependencies = "required-after:mixinbooter;"
)
@Mod.EventBusSubscriber(modid=Constant.MODID)
public class Main
{


    @Mod.Instance(Constant.MODID)
    public static Main instance;

    @SidedProxy(modId = Constant.MODID, clientSide = Constant.CLIENT_PROXY, serverSide = Constant.SERVER_PROXY )
    public static CommonProxy proxy;

    private static Logger logger;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ClassRegister.registerObjectsis(this, Main.class, event);

        ConfigManager.sync(Constant.MODID, Config.Type.INSTANCE);

        RegistryInitsHandler.otherClassedEvent();

        RegistryPreInitHandler.preInitHandler();



        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        ClassRegister.registerObjectsis(this, Main.class, event);

        ConfigManager.sync(Constant.MODID, Config.Type.INSTANCE);

        RegistryInitsHandler.otherClassedEvent();


        if (ModChecker.isHbmModLoaded()) {
            ArmorUtil.registerHazmats();
        }





        proxy.init(event);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        ClassRegister.registerObjectsis(this, Main.class, event);

        ConfigManager.sync(Constant.MODID, Config.Type.INSTANCE);

        RegistryInitsHandler.otherClassedEvent();


        if (ModChecker.isHbmModLoaded()) {
            ArmorUtil.register();
        }



        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {

        event.registerServerCommand(new CommandCheckAttribute());

    }



}
