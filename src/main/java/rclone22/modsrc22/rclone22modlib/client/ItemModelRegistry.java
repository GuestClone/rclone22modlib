package rclone22.modsrc22.rclone22modlib.client;

import net.minecraft.item.Item;
import rclone22.modsrc22.rclone22modlib.main.items.ItemInit;

public class ItemModelRegistry
{

    public static void itemsToBeRegis()
    {
        for (Item item : ItemInit.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }

    }


}
