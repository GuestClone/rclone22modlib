package rclone22.modsrc22.rclone22modlib.main.potions;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WaterVision extends Potion
{

    public WaterVision(boolean isBadEffectIn, int liquidColorIn, String modid, String name) {
        super(isBadEffectIn, liquidColorIn);
        setRegistryName(modid, name);
        setPotionName("potion."+name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        return super.getStatusIconIndex();
    }

}
