package rclone22.modsrc22.rclone22modlib.main.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.hbm.interfaces.IRadResistantBlock;
import micdoodle8.mods.galacticraft.api.item.GCRarity;
import micdoodle8.mods.galacticraft.core.items.IClickableItem;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.api.item.IIsInvulnerableUtil;
import rclone22.modsrc22.rclone22modlib.client.IHasModel;
import rclone22.modsrc22.rclone22modlib.externalmods.baubles.IIsBaubleCancelsDanger;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.Main;

import java.lang.annotation.Annotation;

@Optional.InterfaceList({@Optional.Interface(iface = "baubles.api.IBauble", modid = ModChecker.BAUBLES, striprefs = true)})
public class ItemWearingSBP extends RegularItemBase implements IBauble, IIsBaubleCancelsDanger.IItemBaubleCancelDanger
{

    public ItemWearingSBP(String name, CreativeTabs tabs, int maxStackSize) {
        super(name, tabs, maxStackSize);
    }


    @Optional.Method(modid = "baubles")
    public BaubleType getBaubleType(ItemStack itemStack) {
        if (ModChecker.isBaublesModLoaded()) {
            return BaubleType.TRINKET;
        }
        return null;
    }

    @Optional.Method(modid = "baubles")
    public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
        if (ModChecker.isBaublesModLoaded()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldCancelDanger(ItemStack stack) {
        return true;
    }


}
