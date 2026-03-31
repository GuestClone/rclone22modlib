package rclone22.modsrc22.rclone22modlib.api;

import rclone22.modsrc22.rclone22modlib.main.Constant;

public class ModChecker
{

    public static final String MICDOODLECORE = "micdoodlecore";

    public static final String GCCORE = "galacticraftcore";

    public static final String HBM = "hbm";

    public static final String TANMod = "toughasnails";

    public static final String BAUBLES = "baubles";

    public static final String TECHGUNS = "techguns";

    public static final String AVARITIA = "avaritia";

    public static final String RCLONE22HBMMIXINS = "rclone22hbmmixins";

    public static final String TRINKETSANDBAUBLES = "xat";

    public static boolean isRclone22HbmMixinsModLoaded() {
        return isModPresent(RCLONE22HBMMIXINS);
    }

    public static boolean isTrinketsAndBaublesModLoaded(){
        return isModPresent(TRINKETSANDBAUBLES);
    }

    public static boolean isAvaritiaModLoaded() {
        return isModPresent(AVARITIA);
    }

    public static boolean isTechgunsModLoaded() {
        return isModPresent(TECHGUNS);
    }

    public static boolean isBaublesModLoaded() {
        return isModPresent(BAUBLES);
    }

    public static boolean isHbmModLoaded() {
        return isModPresent(HBM);
    }

    public static boolean isGCCOREModLoaded() {
        return isModPresent(GCCORE);
    }

    public static boolean isMicdoodleCoreModLoaded() {
        return isModPresent(MICDOODLECORE);
    }

    public static boolean isMicDo2GCCoreLoaded() {
        return isMicdoodleCoreModLoaded() && isGCCOREModLoaded();
    }

    public static boolean isTANModLoaded() {
        return isModPresent(TANMod);
    }

    public static boolean isModPresent(String modid) {
        if (isNullOrBlank(modid)) {
            return false;
        }

        try {
            return net.minecraftforge.fml.common.Loader.isModLoaded(modid);
        } catch (Throwable t) {
            System.err.println("[ModCompat:"+Constant.MODID+"]: Modid is not present: " + modid);
            System.err.println("[ModCompat"+Constant.MODID+"]: "+modid+" Means this mod is optional dependency to "+ Constant.MODID );
            t.printStackTrace();
            return false;
        }
    }

    public static boolean isNullOrBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

}
