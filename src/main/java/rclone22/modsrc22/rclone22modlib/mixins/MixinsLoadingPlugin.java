package rclone22.modsrc22.rclone22modlib.mixins;


import com.google.common.eventbus.EventBus;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.relauncher.FMLInjectionData;
import net.minecraftforge.fml.relauncher.IFMLCallHook;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;
import rclone22.modsrc22.rclone22modlib.main.Constant;



import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;

@IFMLLoadingPlugin.Name("RClone22ModLib-Core-Mixins")
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE)
public class MixinsLoadingPlugin implements IFMLLoadingPlugin, IFMLCallHook {

    public MixinsLoadingPlugin()
    {
        mixinInit();
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return "rclone22.modsrc22.rclone22modlib.mixins.MixinsLoadingPlugin$MixinsLoadingContainer";
    }

    @Override
    public String getSetupClass() {
        return "rclone22.modsrc22.rclone22modlib.mixins.MixinsLoadingPlugin";
    }

    @Override
    public void injectData(Map<String, Object> data) {

        mixinInit();
    }

    private static void mixinInit()
    {
        MixinBootstrap.init();

        Mixins.addConfigurations(
                "mixins.rclone22modlib.json",
                "mixins.rclone22modlib.minecraft.json"
        );
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public Void call() throws Exception {
        return null;
    }


    public static class MixinsLoadingContainer extends DummyModContainer
    {

        /**
         E
         **/
        public MixinsLoadingContainer() {
            super(new ModMetadata());
            ModMetadata meta = this.getMetadata();
            meta.modId = "rclone22modlib_core";
            meta.version = "1.0-1.12.2";
            meta.name = "RClone22's ModLib - Core & Mixins";
            meta.description = "CoreMod of RClone22's ModLib, contains mixins and is a CoreMod";
            meta.authorList = Arrays.asList("RCloneBm22/R2234bmgofan22_clone/RClone22");
        }

        @Override
        public boolean registerBus(EventBus bus, LoadController controller) {
            bus.register(this);
            return true;
        }

    }

}

