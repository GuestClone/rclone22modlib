package rclone22.modsrc22.rclone22modlib.mixins;


import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;
import rclone22.modsrc22.rclone22modlib.main.Constant;

import javax.annotation.Nullable;
import java.util.Map;


@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class MixinsLoadingPlugin implements IFMLLoadingPlugin {
    public MixinsLoadingPlugin() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins."+Constant.MODID+".json");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return "rclone22.modsrc22.rclone22modlib.transformers.MixinsLoadingContainer";
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}

