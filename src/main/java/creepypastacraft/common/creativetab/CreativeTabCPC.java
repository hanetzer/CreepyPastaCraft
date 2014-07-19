package creepypastacraft.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import creepypastacraft.init.CPCItems;

public class CreativeTabCPC extends CreativeTabs {
    public CreativeTabCPC(int position, String tabID) {
        super(position, tabID);
    }

    public Item getTabIconItem() {
        return CPCItems.horror;
    }
}
