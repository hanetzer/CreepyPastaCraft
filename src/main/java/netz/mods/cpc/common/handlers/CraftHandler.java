package netz.mods.cpc.common.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import netz.mods.cpc.client.gui.achievement.CPCAchievement;
import netz.mods.cpc.common.item.CPCItem;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftHandler implements ICraftingHandler
{
	@Override
	public void onCrafting(EntityPlayer entityplayer, ItemStack itemstack, IInventory iinventory)
	{
		boolean flag = false;
		if(itemstack == GameRegistry.findItemStack("CPC", "computer", 1)) {
			entityplayer.addStat(CPCAchievement.computer, 1);
			flag = true;
		}

		if(itemstack.itemID == CPCItem.pasta.itemID) {
			entityplayer.addStat(CPCAchievement.creepyPasta, 1);
			flag = true;
		}

		if(itemstack.itemID == CPCItem.diaper.itemID) {
			entityplayer.addStat(CPCAchievement.diaper, 1);
			flag = true;
		}

	}

	public void onItemPickup(EntityPlayer entityplayer, ItemStack itemstack) {
		if(itemstack.itemID == CPCItem.jeffKnife.itemID) {
			entityplayer.addStat(CPCAchievement.noSleep, 1);
		}

		if(itemstack.itemID == CPCItem.killKnife.itemID) {
			entityplayer.addStat(CPCAchievement.mothSwat, 1);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {}
}

