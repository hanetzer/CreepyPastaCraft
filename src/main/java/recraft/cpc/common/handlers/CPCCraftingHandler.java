package recraft.cpc.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.Item;
import recraft.cpc.client.gui.achievement.CPCAchievement;
import recraft.cpc.init.CPCBlocks;
import recraft.cpc.init.CPCItems;

public class CPCCraftingHandler {
	@SubscribeEvent
	public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
		if(event.crafting.getItem() == Item.getItemFromBlock(CPCBlocks.laptop)) {
			event.player.addStat(CPCAchievement.laptop, 1);
		}

		if(event.crafting.getItem() == CPCItems.pasta) {
			event.player.addStat(CPCAchievement.creepyPasta, 1);
		}

		if(event.crafting.getItem() == CPCItems.diaper) {
			event.player.addStat(CPCAchievement.diaper, 1);
		}
	}
}

