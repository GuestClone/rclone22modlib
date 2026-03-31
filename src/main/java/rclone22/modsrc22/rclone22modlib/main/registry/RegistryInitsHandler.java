package rclone22.modsrc22.rclone22modlib.main.registry;

import net.minecraftforge.fml.common.Mod;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;


import rclone22.modsrc22.rclone22modlib.api.item.invunerableitem.ItemInvulRegistry;
import rclone22.modsrc22.rclone22modlib.externalmods.hbm.blocks.HBMRadResistBlocks;
import rclone22.modsrc22.rclone22modlib.main.Constant;
import rclone22.modsrc22.rclone22modlib.main.oredict.OreDictInit;
import rclone22.modsrc22.rclone22modlib.main.registryutils.ClassRegister;
import rclone22.modsrc22.rclone22modlib.main.registryutils.RegistryUtils;


///  All events in this class must not be static else must be called to the otherClassedEvent() method
///
public class RegistryInitsHandler
{


    public static void otherClassedEvent()
    {

        ClassRegister.registEventClassByString(
                new String[]{"minecraft"},
                "rclone22.modsrc22.rclone22modlib.main.registry.RegistryInitsHandler",
                "rclone22.modsrc22.rclone22modlib.main.event.minecraft.BlockEvents",
                "rclone22.modsrc22.rclone22modlib.main.event.minecraft.ItemEvents",
                "rclone22.modsrc22.rclone22modlib.main.event.minecraft.McHazardRemove",
                "rclone22.modsrc22.rclone22modlib.main.event.minecraft.ModdedEvents",
                "rclone22.modsrc22.rclone22modlib.main.event.minecraft.EntityEvent"

        );


            ClassRegister.registEventClassByString(
                    new String[]{ModChecker.GCCORE, ModChecker.MICDOODLECORE},
                    "rclone22.modsrc22.rclone22modlib.externalmods.galacticraft.event.GCCoreEvent");


            ClassRegister.registEventClassByString(
                    new String[]{ModChecker.TANMod},
                    "rclone22.modsrc22.rclone22modlib.externalmods.touchasnails.event.TANModTickEvent");


            ClassRegister.registEventClassByString(
                    new String[]{ModChecker.HBM},
                    "rclone22.modsrc22.rclone22modlib.externalmods.hbm.event.HBMEventHandler");


            ClassRegister.registEventClassByString(
                    new String[]{ModChecker.BAUBLES},
                    "rclone22.modsrc22.rclone22modlib.externalmods.baubles.BaublesEvent");


            ClassRegister.registEventClassByString(
                new String[]{ModChecker.TRINKETSANDBAUBLES},
                "rclone22.modsrc22.rclone22modlib.externalmods.trinketsandbaubles.TrinketsBaubleEvent");


            ClassRegister.registEventClassByString(
                    new String[]{ModChecker.TECHGUNS},
                    "rclone22.modsrc22.rclone22modlib.externalmods.techguns.event.TechGunsEvent");


        HBMRadResistBlocks.register();
        ItemInvulRegistry.initAll();
        OreDictInit.OreDictsInit();
    }

   public void doNothingIg()
   {

   }


}
