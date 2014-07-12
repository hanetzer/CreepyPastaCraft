package recraft.cpc.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import recraft.cpc.client.gui.achievement.CPCAchievement;
import recraft.cpc.init.CPCItems;

public class CPCItemPickupHandler {
	@SubscribeEvent
	public void onItemPickup(PlayerEvent.ItemPickupEvent event) {
		if(event.pickedUp.getEntityItem().getItem() == CPCItems.jeffKnife) {
			event.player.addStat(CPCAchievement.noSleep, 1);
		}

		if(event.pickedUp.getEntityItem().getItem() == CPCItems.killKnife) {
			event.player.addStat(CPCAchievement.mothSwat, 1);
		}
	}
}
