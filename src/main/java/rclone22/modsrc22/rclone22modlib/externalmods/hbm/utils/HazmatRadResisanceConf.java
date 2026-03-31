package rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils;

import net.minecraftforge.common.config.Config;
import rclone22.modsrc22.rclone22modlib.main.Constant;

import java.util.HashMap;
import java.util.Map;

@Config(modid = Constant.MODID, name = "HBM Rad Resistance Config", type = Config.Type.INSTANCE)
public class HazmatRadResisanceConf {

    public static Map<String, Double> getMultipliersMap() {
        Map<String, Double> map = new HashMap<>();
        for (String entry : HazmatRadResisanceConf.multipliers) {
            String[] parts = entry.split("=");
            if (parts.length != 2) continue;
            try {
                String key = parts[0].trim();
                double value = Double.parseDouble(parts[1].trim());
                map.put(key, value);
            } catch (NumberFormatException ignored) {}
        }
        return map;
    }

    @Config.Name("Hazmat Resistance Multipliers")
    @Config.Comment({
            "Map of multiplier names to values.",
            "You can add more entries here, and reference them in the hazmat items list.",
            "Example: ic2Multiplier=1.0, euphMultiplier=10.0"
    })
    public static String[] multipliers = new String[]{
            "ic2Multiplier=1.0",
            "euphMultiplier=10.0",
            "moddedCustomMultiplier=100.0"
    };

    @Config.Name("Base Armor Piece Resistances")
    @Config.Comment({
            "Base resistance values for each armor piece.",
            "These are multiplied by the chosen multiplier when using calculated pieces."
    })
    public static Map<String, Double> baseArmorValues = new HashMap<String, Double>() {{
        put("helmet", 0.2);
        put("chest", 0.4);
        put("legs", 0.3);
        put("boots", 0.1);
    }};

    @Config.Name("Hazmat Resistance Final")
    @Config.Comment({
            "List of hazmat items with resistance sources.",
            "Format: modid:itemname---valueSource",
            "Value source can be:",
            "- A raw number (e.g., 0.5)",
            "- A multiplier name from the multipliers map (e.g., ic2Multiplier)",
            "- A calculated piece: calculatedhelmet, calculatedchest, calculatedlegs, calculatedboots",
            "    This will use: baseArmorValues[piece] * multiplier",
            "    Example: avaritia:infinity_boots---calculatedboots (will use boots base × matching multiplier)"
    })
    public static String[] hazmatItemsRadsFinal = new String[]{
            "ic2:itemarmorhazmathelmet---calculatedhelmet---ic2Multiplier",
            "ic2:itemarmorhazmatchestplate---calculatedchest---ic2Multiplier",
            "ic2:itemarmorhazmatleggings---calculatedlegs---ic2Multiplier",
            "ic2:itemarmorrubboots---calculatedboots---ic2Multiplier",
            "avaritia:infinity_helmet---calculatedhelmet---euphMultiplier",
            "avaritia:infinity_chestplate---calculatedchest---euphMultiplier",
            "avaritia:infinity_pants---calculatedlegs---euphMultiplier",
            "avaritia:infinity_boots---calculatedboots---euphMultiplier"
    };

}
