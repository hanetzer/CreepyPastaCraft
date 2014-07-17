package recraft.cpc.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.ItemStack;
import recraft.cpc.common.stats.CPCAchievementList;
import recraft.cpc.init.CPCBlocks;
import recraft.cpc.init.CPCItems;

public class CPCCraftingHandler {
    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.isItemEqual(new ItemStack(CPCBlocks.laptop))) {
            event.player.addStat(CPCAchievementList.laptop, 1);
        }

        if (event.crafting.getItem() == CPCItems.pasta) {
            event.player.addStat(CPCAchievementList.creepyPasta, 1);
        }

        if (event.crafting.getItem() == CPCItems.diaper) {
            event.player.addStat(CPCAchievementList.diaper, 1);
        }
    }
}
