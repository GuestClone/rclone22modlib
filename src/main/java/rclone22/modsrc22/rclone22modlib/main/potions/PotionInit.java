package rclone22.modsrc22.rclone22modlib.main.potions;

import net.minecraft.potion.Potion;
import rclone22.modsrc22.rclone22modlib.main.Constant;

import java.util.ArrayList;
import java.util.List;

public class PotionInit
{

    public static final List<Potion> POTIONS = new ArrayList<Potion>();

    public static final Potion SECBODYPOT = new SecuredBodyPotionRes(false, 0x98D982,
            Constant.MODID, "secured_body");

    public static final Potion WATERVISION = new WaterVision(false, 0x98D982,
            Constant.MODID, "water_vision");

    static {
        POTIONS.add(SECBODYPOT);
        POTIONS.add(WATERVISION);
    }

}
