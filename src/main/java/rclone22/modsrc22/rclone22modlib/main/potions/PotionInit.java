package rclone22.modsrc22.rclone22modlib.main.potions;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.registryutils.RegistryUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class PotionInit
{

    public static final String SECBODYPOT_NAME = "secured_body";

    public static final List<Potion> POTIONS = new ArrayList<Potion>();


    public static final Potion SECBODYPOT = register(new SecuredBodyPotionRes(false, 0x98D982, SECBODYPOT_NAME));

    private static <T extends Potion> T register(T potion) {
        POTIONS.add(potion);
        return potion;
    }


    public static final PotionType SECBODYPOT_3Min = new PotionType(
            PotionInit.SECBODYPOT_NAME,
            new PotionEffect(PotionInit.SECBODYPOT, 3600)
    ).setRegistryName(Constant.MODID, PotionInit.SECBODYPOT_NAME);



}
