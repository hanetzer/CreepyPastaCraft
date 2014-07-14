package recraft.cpc.common.handlers;

import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("unused")
public class CPCEventHandler {

	public static void init() {
		registerItemEventHandler();
		//registerEntityEventHandler();
	}

	private static void registerItemEventHandler() {
		MinecraftForge.EVENT_BUS.register(new CPCCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new CPCItemPickupHandler());
		MinecraftForge.EVENT_BUS.register(new CPCHiltBlackHandler());
	}

	private static void registerEntityEventHandler() {
		MinecraftForge.EVENT_BUS.register(new CPCAIHandler());
	}
}
