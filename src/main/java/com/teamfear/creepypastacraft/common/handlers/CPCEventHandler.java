package com.teamfear.creepypastacraft.common.handlers;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("unused")
public class CPCEventHandler {

    public static void init() {
        registerItemEventHandler();
        //registerEntityEventHandler();
    }

    private static void registerItemEventHandler() {
        FMLCommonHandler.instance().bus().register(new CPCCraftingHandler());
        FMLCommonHandler.instance().bus().register(new CPCItemPickupHandler());
        MinecraftForge.EVENT_BUS.register(new CPCHiltBlackHandler());
		MinecraftForge.EVENT_BUS.register(new CPCSmileyChatHandler());
    }

    private static void registerEntityEventHandler() {
        MinecraftForge.EVENT_BUS.register(new CPCAIHandler());
    }
}
