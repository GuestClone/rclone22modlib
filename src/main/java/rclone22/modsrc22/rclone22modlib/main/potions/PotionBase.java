package rclone22.modsrc22.rclone22modlib.main.potions;

import net.minecraft.potion.Potion;
import rclone22.modsrc22.rclone22modlib.main.Constant;


public class PotionBase extends Potion {

    public PotionBase(boolean isBadEffectIn, int liquidColorIn, String name) {
        super(isBadEffectIn, liquidColorIn);
        setRegistryName(Constant.MODID, name);
        setPotionName("potion."+name);
    }


}
