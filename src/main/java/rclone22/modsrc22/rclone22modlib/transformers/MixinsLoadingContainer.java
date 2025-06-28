package rclone22.modsrc22.rclone22modlib.transformers;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.Arrays;

public class MixinsLoadingContainer extends DummyModContainer
{

    /**
    E
     **/
        public MixinsLoadingContainer() {
            super(new ModMetadata());
            ModMetadata meta = this.getMetadata();
            meta.modId = "rclone22modlib_core";
            meta.version = "1.0.0";
            meta.name = "RClone22's ModLib - Core";
            meta.description = "CoreMod of RClone22's ModLib, contains mixins";
            meta.authorList = Arrays.asList("RCloneBm22/R2234bmgofan22_clone/RClone22");
        }

        public boolean registerBus(EventBus bus, LoadController controller) {
            bus.register(this);
            return true;
        }

}
