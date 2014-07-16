package recraft.cpc.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import recraft.cpc.init.CPCItems;

public class CreativeTabCPC extends CreativeTabs {
	public CreativeTabCPC(int position, String tabID) {
		super(position, tabID);
	}

	public Item getTabIconItem() {
		return CPCItems.horror;
	}

}
