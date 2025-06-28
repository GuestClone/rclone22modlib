package rclone22.modsrc22.rclone22modlib.main;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;
import rclone22.modsrc22.rclone22modlib.externalmods.galacticraft.event.GCCoreEvent;
import rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils.ArmorUtil;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.main.commands.CommandCheckAttribute;
import rclone22.modsrc22.rclone22modlib.main.oredict.ItemOreDict;
import rclone22.modsrc22.rclone22modlib.main.proxy.CommonProxy;

import rclone22.modsrc22.rclone22modlib.main.registry.RegistryInitsHandler;
import rclone22.modsrc22.rclone22modlib.main.registry.RegistryPreInitHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;


@Mod
        (
        modid = Constant.MODID,
        name = Constant.MOD_NAME,
        version = Constant.MOD_VERSION
)
@Mod.EventBusSubscriber(modid=Constant.MODID)
public class Main
{


    @Mod.Instance(Constant.MODID)
    public static Main instance;

    @SidedProxy(modId = Constant.MODID, clientSide = Constant.CLIENT_PROXY, serverSide = Constant.SERVER_PROXY )
    public static CommonProxy proxy;

    private static Logger logger;

    public static final RegistryPreInitHandler REGISTRY_PRE_INIT_HANDLER = new RegistryPreInitHandler();

    public static final RegistryInitsHandler REGISTRY_INITS_HANDLER = new RegistryInitsHandler();

    public static final GCCoreEvent GCCoreEvents = new GCCoreEvent();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ClassRegister.registerHandlers(this, event, REGISTRY_PRE_INIT_HANDLER);

        if (ModChecker.isMicDo2GCCoreLoaded()) {
            ClassRegister.registerHandlers(GCCoreEvents);
        }

        MixinBootstrap.init();
        Mixins.addConfiguration("mixins."+Constant.MODID+".json");

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        ClassRegister.registerHandlers(this, event, REGISTRY_INITS_HANDLER);

        if (ModChecker.isMicDo2GCCoreLoaded()) {
            ClassRegister.registerHandlers(GCCoreEvents);
        }

        if (ModChecker.isHbmModLoaded())
        {
            ArmorUtil.registerHazmats();
        }

        proxy.init(event);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        ClassRegister.registerHandlers(this, event);

        if (ModChecker.isHbmModLoaded())
        {
            ArmorUtil.register();
        }

        ItemOreDict.registerOres();

        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {

        event.registerServerCommand(new CommandCheckAttribute());

    }

}
