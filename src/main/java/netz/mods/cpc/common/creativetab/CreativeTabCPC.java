package netz.mods.cpc.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import netz.mods.cpc.common.item.CPCItem;

public class CreativeTabCPC extends CreativeTabs
{
	public CreativeTabCPC(int position, String tabID) {
		super(position, tabID);
	}

	public ItemStack getIconItemStack() {
		return new ItemStack(CPCItem.horror, 1, 7);
	}
}
