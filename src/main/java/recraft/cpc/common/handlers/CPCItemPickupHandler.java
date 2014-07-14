package recraft.cpc.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import recraft.cpc.common.stats.CPCAchievementList;
import recraft.cpc.init.CPCItems;

@SuppressWarnings("unused")
public class CPCItemPickupHandler {
	@SubscribeEvent
	public void onItemPickup(PlayerEvent.ItemPickupEvent event) {
		if(event.pickedUp.getEntityItem().getItem() == CPCItems.jeffKnife) {
			event.player.addStat(CPCAchievementList.noSleep, 1);
		}

		if(event.pickedUp.getEntityItem().getItem() == CPCItems.killKnife) {
			event.player.addStat(CPCAchievementList.mothSwat, 1);
		}
	}
}
