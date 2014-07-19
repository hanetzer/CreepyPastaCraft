package creepypastacraft.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import creepypastacraft.common.stats.CPCAchievementList;
import creepypastacraft.init.CPCItems;

@SuppressWarnings("unused")
public class CPCItemPickupHandler {
    @SubscribeEvent
    public void onItemPickup(PlayerEvent.ItemPickupEvent event) {
        if (event.pickedUp.getEntityItem().getItem() == CPCItems.jeffKnife) {
            event.player.addStat(CPCAchievementList.noSleep, 1);
        }
    }
}
