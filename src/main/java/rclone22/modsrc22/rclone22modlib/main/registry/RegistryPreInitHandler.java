package rclone22.modsrc22.rclone22modlib.main.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import rclone22.modsrc22.rclone22modlib.main.ClassRegister;
import rclone22.modsrc22.rclone22modlib.main.enchantments.EnchantInit;
import rclone22.modsrc22.rclone22modlib.main.items.ItemInit;
import rclone22.modsrc22.rclone22modlib.main.potions.PotionInit;

public class RegistryPreInitHandler
{

    public RegistryPreInitHandler()
    {

    }

    @SubscribeEvent
    public void registerPotion(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> register = event.getRegistry();

        for (Potion potion : PotionInit.POTIONS) {
            register.register(potion);
        }
    }

    @SubscribeEvent
    public void registerItem(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> register = event.getRegistry();

        for (Item item : ItemInit.ITEMS) {
            register.register(item);
        }

    }

    @SubscribeEvent
    public void registerEnchant(RegistryEvent.Register<Enchantment> event) {
        IForgeRegistry<Enchantment> register = event.getRegistry();

        for (Enchantment enchantment : EnchantInit.ENCHANTMENTS) {
            register.register(enchantment);
        }

    }

}
