package recraft.cpc.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import recraft.cpc.client.gui.achievement.CPCAchievement;
import recraft.cpc.core.CPCItem;
import recraft.cpc.init.CPCBlocks;
import recraft.cpc.init.CPCItems;

public class CPCCraftingHandler {
	@SubscribeEvent
	public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
		if(event.crafting.getItem() == Item.getItemFromBlock(CPCBlocks.laptop)) {
			event.player.addStat(CPCAchievement.computer, 1);
		}

		if(event.crafting.getItem() == CPCItems.pasta) {
			event.player.addStat(CPCAchievement.creepyPasta, 1);
		}

		if(event.crafting.getItem() == CPCItems.diaper) {
			event.player.addStat(CPCAchievement.diaper, 1);
		}
	}
}

