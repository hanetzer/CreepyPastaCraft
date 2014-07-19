package creepypastacraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import creepypastacraft.api.registry.PastaRegistry;
import creepypastacraft.common.CommonProxy;
import creepypastacraft.common.creativetab.CreativeTabCPC;
import creepypastacraft.common.handlers.CPCEventHandler;
import creepypastacraft.common.stats.CPCAchievementList;
import creepypastacraft.core.CPCBlock;
import creepypastacraft.core.CPCEntity;
import creepypastacraft.core.CPCItem;

@SuppressWarnings("unused")
@Mod(modid = "cpc")
public class CPC {
    @Instance("cpc")
    public static CPC instance;

    @SidedProxy(
            clientSide = "creepypastacraft.client.ClientProxy",
            serverSide = "creepypastacraft.common.CommonProxy")
    public static CommonProxy proxy;
    public static CreativeTabs tabCPC;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        tabCPC = new CreativeTabCPC(CreativeTabs.getNextID(), "tabCPC");
        CPCEventHandler.init();
        CPCItem.init();
        CPCBlock.init();
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
