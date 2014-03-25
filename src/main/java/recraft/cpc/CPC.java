package recraft.cpc;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import recraft.cpc.common.CommonProxy;
import recraft.cpc.common.block.CPCBlock;
import recraft.cpc.client.ClientProxy;
import recraft.cpc.client.gui.achievement.CPCAchievement;
import recraft.cpc.config.CPCConfig;
import recraft.cpc.common.creativetab.CreativeTabCPC;
import recraft.cpc.common.entity.CPCEntity;
import recraft.cpc.common.handlers.CPClientTickHandler;
import recraft.cpc.common.handlers.GuiHandler;
import recraft.cpc.common.handlers.SoundHandler;
import recraft.cpc.common.item.CPArchive;
import recraft.cpc.common.item.CPCItem;
import recraft.cpc.common.item.record.Record;
import recraft.cpc.common.biome.CPCBiomes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid="cpc")
public class CPC
{

	Minecraft mc = Minecraft.getMinecraft();
	@Instance("cpc")
	public static CPC instance;

	@SidedProxy(
			clientSide="netz.mods.cpc.client.ClientProxy",
			serverSide="netz.mods.cpc.common.CommonProxy")
	public static CommonProxy proxy;
	public static CreativeTabs tabCPC;
	public static String configPath;

	public CPC()
	{
		super();
	}

	public static int pageSpawnRange;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		configPath = event.getModConfigurationDirectory() + "/creepypastacraft/";
		CPCConfig.init(configPath);
		tabCPC = new CreativeTabCPC(CreativeTabs.getNextID(), "tabCPC");
		MinecraftForge.EVENT_BUS.register(new EventHooks());
		CPCItem.init();
		CPCBlock.init();
		CPArchive.init();
		Record.init();
		CPCBiomes.init();
		CPCEntity.init();
		CPCAchievement.init();

		//GameRegistry.registerCraftingHandler(new CraftHandler());
		if(proxy instanceof ClientProxy) {
			MinecraftForge.EVENT_BUS.register(new SoundHandler());
		}

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		TickRegistry.registerTickHandler(new CPClientTickHandler(), Side.CLIENT);
	}

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		mc.gameSettings.gammaSetting = -0.7f;
	}

}
