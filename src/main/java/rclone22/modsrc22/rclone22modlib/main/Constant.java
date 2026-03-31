package rclone22.modsrc22.rclone22modlib.main;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=Constant.MODID)
public class Constant {


    public static final String MODID = "rclone22modlib";

    public static final String MOD_VERSION = "1.0.0-1.12.2";

    public static final String API_VERSION = MOD_VERSION+".01";

    public static final String MOD_NAME = "RClone22's ModLib";

    public static final String MC_VERSION = ForgeVersion.mcVersion;

    public static final String FORGE_VERSION = "14.23.5.2860";

    public static final String MOD_OWNER = "rclone22";

    ///  rclone22modlibAPI
    public static final String API_TEXT = MODID+"API";

    public static final String CLIENT_PROXY = "rclone22.modsrc22.rclone22modlib.main.proxy.ClientProxy";

    public static final String SERVER_PROXY = "rclone22.modsrc22.rclone22modlib.main.proxy.ServerProxy";

}
