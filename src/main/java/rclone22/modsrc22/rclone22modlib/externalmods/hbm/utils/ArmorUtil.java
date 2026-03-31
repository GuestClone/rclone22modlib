package rclone22.modsrc22.rclone22modlib.externalmods.hbm.utils;

import com.hbm.handler.HazmatRegistry;
import com.hbm.util.ArmorRegistry;
import net.minecraft.item.Item;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.main.items.CheckItemUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

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

            for (String entry : HelmetHazardsConf.hazardItemsHazards) {
                try {
                    String[] parts = entry.split("---");
                    String[] itemParts = parts[0].split(":");
                    String domain = itemParts[0];
                    String name = itemParts[1];

                    String[] hazardStrings = parts[1].split(",");
                    ArmorRegistry.HazardClass[] hazards = new ArmorRegistry.HazardClass[hazardStrings.length];
                    for (int i = 0; i < hazardStrings.length; i++) {
                        hazards[i] = ArmorRegistry.HazardClass.valueOf(hazardStrings[i].trim().toUpperCase());
                    }

                    registerHazards(domain, name, hazards);

                } catch (Exception e) {
                    System.err.println("Invalid hazard config entry: " + entry);
                    e.printStackTrace();
                }
            }
        }
    }

    ///  Register in the "init" method in either proxy or in main mod class
    public static void registerHazmats(){
        if (ModChecker.isHbmModLoaded()) {

            Map<String, Double> multipliersMap = HazmatRadResisanceConf.getMultipliersMap();

            for (String entry : HazmatRadResisanceConf.hazmatItemsRadsFinal) {
                try {
                    String[] parts = entry.split("---");

                    if (parts.length < 2) {
                        System.err.println("[HazmatConfig] Invalid entry: " + entry);
                        continue;
                    }

                    String fullItemName = parts[0];
                    String valueType = parts[1];
                    String multiplierName = (parts.length >= 3) ? parts[2] : null;

                    String[] itemParts = fullItemName.split(":");
                    if (itemParts.length != 2) {
                        System.err.println("[HazmatConfig] Invalid item format: " + fullItemName);
                        continue;
                    }

                    String modid = itemParts[0];
                    String itemName = itemParts[1];

                    double finalValue;

                    if (valueType.startsWith("calculated")) {
                        String piece = valueType.replace("calculated", "").toLowerCase();
                        double baseValue = HazmatRadResisanceConf.baseArmorValues.getOrDefault(piece, 0.0);

                        double multiplier = 1.0;
                        if (multiplierName != null) {
                            multiplier = multipliersMap.getOrDefault(multiplierName, 1.0);
                        }

                        finalValue = baseValue * multiplier;
                    }
                    else {
                        try {
                            /// Try parsing as raw number
                            finalValue = Double.parseDouble(valueType);
                        } catch (NumberFormatException e) {
                            /// If not a number, assume it's a multiplier name
                            finalValue = multipliersMap.getOrDefault(valueType, 1.0);
                        }
                    }

                    ArmorUtil.registerHazmats(modid, itemName, ArmorUtil.fixRounding(finalValue));

                } catch (Exception e) {
                    System.err.println("[HazmatConfig] Failed to register hazmat entry: " + entry);
                    e.printStackTrace();
                }
            }
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
