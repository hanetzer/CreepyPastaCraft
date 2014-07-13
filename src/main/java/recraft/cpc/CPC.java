package recraft.cpc;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import recraft.cpc.client.gui.achievement.CPCAchievement;
import recraft.cpc.common.CommonProxy;
import recraft.cpc.api.registry.PastaRegistry;
import recraft.cpc.core.CPCBlock;
import recraft.cpc.common.creativetab.CreativeTabCPC;
import recraft.cpc.core.CPCEntity;
import recraft.cpc.common.handlers.CPCEventHandler;
import recraft.cpc.common.handlers.GuiHandler;
import recraft.cpc.core.CPCItem;

@Mod(modid="cpc")
public class CPC {
	public static Minecraft mc = Minecraft.getMinecraft();

	@Instance("cpc")
	public static CPC instance;

	@SidedProxy(
			clientSide="recraft.cpc.client.ClientProxy",
			serverSide="recraft.cpc.common.CommonProxy")
	public static CommonProxy proxy;
	public static CreativeTabs tabCPC;
	public static String configPath;

	public static int pageSpawnRange;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		configPath = event.getModConfigurationDirectory() + "/creepypastacraft/";
		//CPCConfig.init(configPath);
		tabCPC = new CreativeTabCPC(CreativeTabs.getNextID(), "tabCPC");
		MinecraftForge.EVENT_BUS.register(new CPCEventHandler());
		CPCItem.init();
		CPCBlock.init();
		CPCEntity.init();
		PastaRegistry.init();
		CPCAchievement.init();

		//GameRegistry.registerCraftingHandler(new CraftHandler());

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//TickRegistry.registerTickHandler(new CPClientTickHandler(), Side.CLIENT);
	}

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		mc.gameSettings.gammaSetting = -0.7f;
	}

}
