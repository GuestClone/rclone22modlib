package rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils;

import net.minecraftforge.common.config.Config;
import rclone22.modsrc22.rclone22modlib.main.Constant;

@Config(modid = Constant.MODID, name = "HBM Helmet Hazards Config", type = Config.Type.INSTANCE)
public class HelmetHazardsConf {

    @Config.Name("Armor Hazard - Hazards")
    @Config.Comment({
            "List of items to register hazards for.",
            "Format: modid:itemname---HAZARD1,HAZARD2,...",
            "Hazard names must match ArmorRegistry.HazardClass enum names.",
            "All hazards:",
            "GAS_CHLORINE, GAS_MONOXIDE, GAS_INERT, PARTICLE_COARSE, PARTICLE_FINE,BACTERIA, NERVE_AGENT, GAS_CORROSIVE, SAND,LIGHT, RAD_GAS"
    })
    public static String[] hazardItemsHazards = new String[] {
            "ic2:itemarmorhazmathelmet---PARTICLE_COARSE,PARTICLE_FINE,GAS_CHLORINE,GAS_MONOXIDE,SAND,LIGHT",
            "rclone22modlib:meta_helmet---GAS_CHLORINE,GAS_MONOXIDE,GAS_INERT,PARTICLE_COARSE,PARTICLE_FINE,BACTERIA,NERVE_AGENT,GAS_CORROSIVE,SAND,LIGHT,RAD_GAS",
            "avaritia:infinity_helmet---GAS_CHLORINE,GAS_MONOXIDE,GAS_INERT,PARTICLE_COARSE,PARTICLE_FINE,BACTERIA,NERVE_AGENT,GAS_CORROSIVE,SAND,LIGHT,RAD_GAS",
    };

}
