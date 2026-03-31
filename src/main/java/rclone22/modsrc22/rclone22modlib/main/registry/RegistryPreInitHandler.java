package rclone22.modsrc22.rclone22modlib.main.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.main.enchantments.EnchantInit;
import rclone22.modsrc22.rclone22modlib.main.items.ItemInit;
import rclone22.modsrc22.rclone22modlib.main.potions.PotionInit;
import rclone22.modsrc22.rclone22modlib.main.registryutils.ClassRegister;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.registryutils.RegistryUtils;

public class RegistryPreInitHandler
{

    public static void preInitHandler()
    {
        ClassRegister.registEventClassByString(
                new String[]{"minecraft"},
                "rclone22.modsrc22.rclone22modlib.main.registry.RegistryPreInitHandler"

        );

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(
                ItemInit.ITEMS.toArray(new Item[0])
        );
    }

    @SubscribeEvent
    public static void registerPotionType(RegistryEvent.Register<PotionType> event) {
        IForgeRegistry<PotionType> register = event.getRegistry();

        register.registerAll(
                PotionInit.SECBODYPOT_3Min
        );

    }

    @SubscribeEvent
    public static void registerPotion(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> register = event.getRegistry();

        register.registerAll(
                PotionInit.POTIONS.toArray(new Potion[0])
        );

    }

    @SubscribeEvent
    public static void registerEnchant(RegistryEvent.Register<Enchantment> event) {
        IForgeRegistry<Enchantment> register = event.getRegistry();

        register.registerAll(
                EnchantInit.ENCHANTMENTS.toArray(new Enchantment[0])
        );


    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Constant.MODID)) {
            ConfigManager.sync(Constant.MODID, Config.Type.INSTANCE);
        }
    }


}
