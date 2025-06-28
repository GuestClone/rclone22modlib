package rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils;

import com.hbm.handler.HazmatRegistry;
import com.hbm.util.ArmorRegistry;
import net.minecraft.item.Item;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.items.CheckItemUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArmorUtil
{

    /** Values of armor types for registerHazmats
    double helmet = 0.2D;
    double chest = 0.4D;
    double legs = 0.3D;
    double boots = 0.1D;

     Also register "register()" in postInit method in either proxy or in main mod class
    */
    public static void register(){
        if (ModChecker.isHbmModLoaded()) {

            ArmorUtil.registerHazards("ic2", "itemarmorhazmathelmet", ArmorRegistry.HazardClass.PARTICLE_COARSE, ArmorRegistry.HazardClass.PARTICLE_FINE, ArmorRegistry.HazardClass.GAS_CHLORINE, ArmorRegistry.HazardClass.GAS_MONOXIDE, ArmorRegistry.HazardClass.SAND, ArmorRegistry.HazardClass.LIGHT);

            ArmorUtil.registerHazards(Constant.MODID, "meta_helmet", ArmorRegistry.HazardClass.GAS_CHLORINE, ArmorRegistry.HazardClass.GAS_MONOXIDE, ArmorRegistry.HazardClass.GAS_INERT, ArmorRegistry.HazardClass.PARTICLE_COARSE, ArmorRegistry.HazardClass.PARTICLE_FINE, ArmorRegistry.HazardClass.BACTERIA, ArmorRegistry.HazardClass.NERVE_AGENT, ArmorRegistry.HazardClass.GAS_CORROSIVE, ArmorRegistry.HazardClass.SAND, ArmorRegistry.HazardClass.LIGHT, ArmorRegistry.HazardClass.RAD_GAS);
        }
    }

    ///  Register in the "init" method in either proxy or in main mod class
    public static void registerHazmats(){
        if (ModChecker.isHbmModLoaded()) {
            double helmet = 0.2D;
            double chest = 0.4D;
            double legs = 0.3D;
            double boots = 0.1D;

            double ic2 = 1.0D;

            ArmorUtil.registerHazmats(Constant.MODID, "meta_helmet", ArmorUtil.fixRounding(100 * helmet));

            ArmorUtil.registerHazmats("ic2", "itemarmorhazmathelmet", ArmorUtil.fixRounding(ic2 * helmet));
            ArmorUtil.registerHazmats("ic2", "itemarmorhazmatchestplate", ArmorUtil.fixRounding(ic2 * chest));
            ArmorUtil.registerHazmats("ic2", "itemarmorhazmatleggings", ArmorUtil.fixRounding(ic2* legs));
            ArmorUtil.registerHazmats("ic2", "itemarmorrubboots", ArmorUtil.fixRounding(ic2 * boots));

        }
    }

    public static double fixRounding(double value){
        return BigDecimal.valueOf(value).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    private static void registerHazards(String domain, String name, ArmorRegistry.HazardClass... classes) {
        if (ModChecker.isHbmModLoaded()) {
            Item item = CheckItemUtil.tryLoadItem(domain, name);
            if (item != null)
                ArmorRegistry.registerHazard(item, classes);
        }
    }

    private static void registerHazmats(String domain, String name, double resistance) {
        if (ModChecker.isHbmModLoaded()) {
            Item item = CheckItemUtil.tryLoadItem(domain, name);
            if (item != null)
                HazmatRegistry.registerHazmat(item, resistance);
        }
    }



}
