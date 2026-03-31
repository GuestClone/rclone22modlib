package rclone22.modsrc22.rclone22modlib.main.items;

import api.hbm.energy.IBatteryItem;
import cofh.redstoneflux.api.IEnergyContainerItem;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.util.I18nUtil;
import ic2.api.item.IElectricItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;
import rclone22.modsrc22.rclone22modlib.client.IHasModel;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.Main;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Optional.InterfaceList(
        {@Optional.Interface(iface = "cofh.redstoneflux.api.IEnergyContainerItem", modid = "redstoneflux", striprefs = true),
                @Optional.Interface(iface = "api.hbm.energy.IBatteryItem", modid = ModChecker.HBM, striprefs = true)
        }
)
public class ItemBigBattery extends RegularItemBase implements IEnergyContainerItem, IBatteryItem {

    public static final int CAPACITY = Integer.MAX_VALUE;
    public static final int MAX_TRANSFER = Integer.MAX_VALUE;

    public static final long CAPACITY_LONG = Long.MAX_VALUE;
    public static final long MAX_TRANSFER_LONG = Long.MAX_VALUE;

    
    public ItemBigBattery(String name, CreativeTabs tabs, int maxStackSize) {
        super(name, tabs, maxStackSize);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(@Nullable ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new EnergyCapabilityProvider(stack);
    }


    @Optional.Method(modid = "redstoneflux")
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        long hbmCharge = 0;
        long hbmCapacity = CAPACITY_LONG;

        // Use HBM charge if HBM mod is loaded
        if (ModChecker.isHbmModLoaded()) {
            hbmCharge = getCharge(stack);
            hbmCapacity = getMaxCharge();
        }

        int acceptedFE = (int) Math.min(maxReceive, Math.min(MAX_TRANSFER, hbmCapacity - hbmCharge));

        if (!simulate) {
            if (ModChecker.isHbmModLoaded()) {
                setCharge(stack, hbmCharge + acceptedFE);
            } else {
                // fallback to FE-only storage
                if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
                int storedFE = stack.getTagCompound().getInteger("Energy");
                stack.getTagCompound().setInteger("Energy", storedFE + acceptedFE);
            }
        }

        return acceptedFE;
    }

    @Optional.Method(modid = "redstoneflux")
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        long hbmCharge = 0;

        if (ModChecker.isHbmModLoaded()) {
            hbmCharge = getCharge(stack);
        } else {
            if (stack.hasTagCompound())
                hbmCharge = stack.getTagCompound().getInteger("Energy");
        }

        int extractedFE = (int) Math.min(maxExtract, Math.min(MAX_TRANSFER, hbmCharge));

        if (!simulate) {
            if (ModChecker.isHbmModLoaded()) {
                setCharge(stack, hbmCharge - extractedFE);
            } else {
                stack.getTagCompound().setInteger("Energy", (int) (hbmCharge - extractedFE));
            }
        }

        return extractedFE;
    }

    @Optional.Method(modid = "redstoneflux")
    public int getEnergyStored(ItemStack stack) {
        if (ModChecker.isHbmModLoaded()) {
            return (int) Math.min(getCharge(stack), Integer.MAX_VALUE);
        } else {
            if (!stack.hasTagCompound()) return 0;
            return stack.getTagCompound().getInteger("Energy");
        }
    }

    @Optional.Method(modid = "redstoneflux")
    public int getMaxEnergyStored(ItemStack stack) {
        if (ModChecker.isHbmModLoaded()) {
            return (int) Math.min(getMaxCharge(), Integer.MAX_VALUE);
        }
        return CAPACITY;
    }


    @SideOnly(Side.CLIENT)
    public void addInformation(@Nullable ItemStack stack, @Nullable World worldIn,@Nullable List<String> tooltip, @Nullable ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add("Transfer rate: Instant. ");

        if (ModChecker.isHbmModLoaded()) {
            tooltip.add("Same conversion ratio from RF/FE to HE or vice versa. ");
        }

        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);

            if (storage != null) {
                int energy = storage.getEnergyStored();
                int max = storage.getMaxEnergyStored();

                tooltip.add(TextFormatting.GREEN + "Energy: " +
                        TextFormatting.YELLOW + energy + " / " + max + " FE");
            }
        }

        if (ModChecker.isModPresent("redstoneflux")) {
            if (stack.getItem() instanceof IEnergyContainerItem) {
                IEnergyContainerItem rfItem = (IEnergyContainerItem) stack.getItem();
                int energy = rfItem.getEnergyStored(stack);
                int max = rfItem.getMaxEnergyStored(stack);
                tooltip.add(TextFormatting.AQUA + "Energy (RF): " +
                        TextFormatting.YELLOW + energy + " / " + max + " RF");
            }
        }

        if (ModChecker.isHbmModLoaded())
        {
            long charge = this.editableHbmMaxCharge();
            if(stack.hasTagCompound())
                charge = getCharge(stack);

            if(stack.getItem() == this)

            {
                tooltip.add("§6"+ I18nUtil.resolveKey("desc.energystore")+" " + Library.getShortNumber(charge) + "/" + Library.getShortNumber(this.editableHbmMaxCharge()) + "HE§r");
            } else {
                String charge1 = Library.getShortNumber((charge * 100) / this.editableHbmMaxCharge());
                tooltip.add("§2"+I18nUtil.resolveKey("desc.energychargecur")+" " + charge1 + "%§r");
                tooltip.add("(" + Library.getShortNumber(charge) + "/" + Library.getShortNumber(this.editableHbmMaxCharge()) + "HE)");
            }
            tooltip.add("§a"+I18nUtil.resolveKey("desc.energychargerate")+" " + Library.getShortNumber(this.editableHbmChargeRate() * 20) + "HE/s§r");
            tooltip.add("§c"+I18nUtil.resolveKey("desc.energydchargerate")+" " + Library.getShortNumber(this.editableHbmDischargeRate() * 20) + "HE/s§r");
        }

    }

    @Optional.Method(modid = ModChecker.HBM)
    public void chargeBattery(ItemStack stack, long i) {
        if(stack.getItem() instanceof ItemBigBattery) {
            if(stack.hasTagCompound()) {
                stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") + i);
            } else {
                stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setLong("charge", i);
            }
        }
    }

    @Optional.Method(modid = ModChecker.HBM)
    public void setCharge(ItemStack stack, long i) {
        if(stack.getItem() instanceof ItemBigBattery) {
            if(stack.hasTagCompound()) {
                stack.getTagCompound().setLong("charge", i);
            } else {
                stack.setTagCompound(new NBTTagCompound());;
                stack.getTagCompound().setLong("charge", i);
            }
        }
    }

    @Optional.Method(modid = ModChecker.HBM)
    public void dischargeBattery(ItemStack stack, long i) {
        if(stack.getItem() instanceof ItemBigBattery) {
            if(stack.hasTagCompound()) {
                stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") - i);
            } else {
                stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setLong("charge", this.editableHbmMaxCharge() - i);
            }
        }
    }

    @Optional.Method(modid = ModChecker.HBM)
    public long getCharge(ItemStack stack) {
        if (!(stack.getItem() instanceof ItemBigBattery)) return 0;

        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setLong("charge", 0); // <--- empty battery by default
            return 0;
        }

        return stack.getTagCompound().getLong("charge");
    }

    @Optional.Method(modid = ModChecker.HBM)
    public long getMaxCharge() {
        return this.editableHbmMaxCharge();
    }

    @Optional.Method(modid = ModChecker.HBM)
    public long getChargeRate() {
        return this.editableHbmChargeRate();
    }

    @Optional.Method(modid = ModChecker.HBM)
    public long getDischargeRate() {
        return this.editableHbmDischargeRate();
    }

    public long editableHbmDischargeRate()
    {
        return Long.MAX_VALUE;
    }

    public long editableHbmChargeRate()
    {
        return Long.MAX_VALUE;
    }

    public long editableHbmMaxCharge()
    {
        return Long.MAX_VALUE;
    }


    private static class EnergyCapabilityProvider implements ICapabilityProvider {
        private final ItemStack container;
        private final IEnergyStorage storage;

        EnergyCapabilityProvider(ItemStack container) {
            this.container = container;
            this.storage = new EnergyStorageImpl(container);
        }

        @Override
        public boolean hasCapability(@Nonnull net.minecraftforge.common.capabilities.Capability<?> capability, @Nullable net.minecraft.util.EnumFacing facing) {
            return capability == CapabilityEnergy.ENERGY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable net.minecraft.util.EnumFacing facing) {
            if (capability == CapabilityEnergy.ENERGY) {
                return CapabilityEnergy.ENERGY.cast(storage);
            }
            return null;
        }
    }

    private static class EnergyStorageImpl implements IEnergyStorage {
        private final ItemStack container;

        EnergyStorageImpl(ItemStack container) {
            this.container = container;
        }

        public NBTTagCompound getNBT() {
            if (!container.hasTagCompound()) {
                container.setTagCompound(new NBTTagCompound());
            }
            return container.getTagCompound();
        }

        private long getChargeSafe() {
            if (ModChecker.isHbmModLoaded()) {
                return ((ItemBigBattery) container.getItem()).getCharge(container);
            } else {
                if (!container.hasTagCompound()) container.setTagCompound(new NBTTagCompound());
                return container.getTagCompound().getInteger("Energy");
            }
        }

        private void setChargeSafe(long amount) {
            if (ModChecker.isHbmModLoaded()) {
                ((ItemBigBattery) container.getItem()).setCharge(container, amount);
            } else {
                if (!container.hasTagCompound()) container.setTagCompound(new NBTTagCompound());
                container.getTagCompound().setInteger("Energy", (int) Math.min(amount, Integer.MAX_VALUE));
            }
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            long stored = getChargeSafe();
            long accepted = Math.min(maxReceive, Math.min(MAX_TRANSFER, CAPACITY_LONG - stored));
            if (!simulate) setChargeSafe(stored + accepted);
            return (int) accepted;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            long stored = getChargeSafe();
            long extracted = Math.min(maxExtract, Math.min(MAX_TRANSFER, stored));
            if (!simulate) setChargeSafe(stored - extracted);
            return (int) extracted;
        }

        @Override
        public int getEnergyStored() {
            return (int) Math.min(getChargeSafe(), Integer.MAX_VALUE);
        }

        @Override
        public int getMaxEnergyStored() {
            return (int) Math.min(CAPACITY_LONG, Integer.MAX_VALUE);
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    }

}
