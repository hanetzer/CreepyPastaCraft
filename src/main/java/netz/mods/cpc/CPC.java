package netz.mods.cpc;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import netz.mods.cpc.block.CPCBlock;
import netz.mods.cpc.client.ClientProxy;
import netz.mods.cpc.client.gui.achievement.CPCAchievement;
import netz.mods.cpc.config.CPCConfig;
import netz.mods.cpc.creativetab.CreativeTabCPC;
import netz.mods.cpc.entity.CPCEntity;
import netz.mods.cpc.handlers.CPClientTickHandler;
import netz.mods.cpc.handlers.GuiHandler;
import netz.mods.cpc.handlers.SoundHandler;
import netz.mods.cpc.item.CPArchive;
import netz.mods.cpc.item.CPCItem;
import netz.mods.cpc.item.record.Record;
import netz.mods.cpc.world.biome.CPCBiomes;
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
@NetworkMod(
		clientSideRequired=true,
		serverSideRequired=false
		)
public class CPC
{

    Minecraft mc = Minecraft.getMinecraft();
    @Instance("cpc")
	public static CPC instance;

	@SidedProxy(
			clientSide="netz.mods.cpc.client.ClientProxy",
			serverSide="netz.mods.cpc.CommonProxy")
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
