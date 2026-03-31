package rclone22.modsrc22.rclone22modlib.api.item.invunerableitem;

import net.minecraft.init.Items;
import rclone22.modsrc22.rclone22modlib.api.item.NbtItemUtil;
import rclone22.modsrc22.rclone22modlib.main.items.ItemInit;
import rclone22.modsrc22.rclone22modlib.main.oredict.ItemInvulOreDict;

public class ItemInvulRegistry
{

    public static void initAll()
    {
        NbtItemUtil.registerItemToInvulnerable(Items.NETHER_STAR);

        NbtItemUtil.registerItemToInvulnerable(ItemInvulOreDict.itemAvaritia1);
        NbtItemUtil.registerItemToInvulnerable(ItemInvulOreDict.itemAvaritia2);
        NbtItemUtil.registerItemToInvulnerable(ItemInvulOreDict.itemAvaritia3);
        NbtItemUtil.registerItemToInvulnerable(ItemInvulOreDict.itemAvaritia4);

        NbtItemUtil.registerItemToInvulnerable(ItemInit.META_HELMET);
        NbtItemUtil.registerItemToInvulnerable(ItemInit.ITEM_WEARABLE_SBP);
    }

}
