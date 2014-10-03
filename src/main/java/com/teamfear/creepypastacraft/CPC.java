package com.teamfear.creepypastacraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import com.teamfear.creepypastacraft.common.tile.CPCTile;
import net.minecraft.creativetab.CreativeTabs;
import com.teamfear.creepypastacraft.api.registry.PastaRegistry;
import com.teamfear.creepypastacraft.common.CommonProxy;
import com.teamfear.creepypastacraft.common.creativetab.CreativeTabCPC;
import com.teamfear.creepypastacraft.common.handlers.CPCEventHandler;
import com.teamfear.creepypastacraft.common.stats.CPCAchievementList;
import com.teamfear.creepypastacraft.common.entity.CPCEntity;
import com.teamfear.creepypastacraft.common.item.CPCItem;

@SuppressWarnings("unused")
@Mod(modid = "creepypastacraft", name = "CreepyPastaCraft", version = "@VERSION@")
public class CPC {
    @Instance("creepypastacraft")
    public static CPC instance;

    @SidedProxy(
            clientSide = "com.teamfear.creepypastacraft.client.ClientProxy",
            serverSide = "com.teamfear.creepypastacraft.common.CommonProxy")
    public static CommonProxy proxy;
    public static CreativeTabs tabCPC;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        tabCPC = new CreativeTabCPC("tabCPC");
        CPCEventHandler.init();
        CPCItem.init();
        CPCTile.init();
        CPCEntity.init();
        PastaRegistry.init();
        CPCAchievementList.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {}
}
