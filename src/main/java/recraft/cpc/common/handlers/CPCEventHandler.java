package recraft.cpc.common.handlers;

import cpw.mods.fml.common.FMLCommonHandler;
import recraft.cpc.common.handlers.CPCAIHandler;
import recraft.cpc.common.handlers.CPCCraftingHandler;
import recraft.cpc.common.handlers.CPCHiltBlackHandler;
import recraft.cpc.common.handlers.CPCItemPickupHandler;

public class CPCEventHandler {

	public static void init() {
		registerItemEventHandler();
	}

	private static void registerItemEventHandler() {
		FMLCommonHandler.instance().bus().register(new CPCCraftingHandler());
		FMLCommonHandler.instance().bus().register(new CPCItemPickupHandler());
		FMLCommonHandler.instance().bus().register(new CPCHiltBlackHandler());
	}

	private static void registerEntityEventHanlder() {
		FMLCommonHandler.instance().bus().register(new CPCAIHandler());
	}

//	@ForgeSubscribe
//	public void batSpawnEvent(LivingSpawnEvent event)
//	{
//		EntityLivingBase bat = event.entityLiving;
//		if(bat instanceof EntityBat)
//		{
//			((EntityBat) bat).tasks.addTask(1, new EntityAIAttackOnCollide((EntityCreature) bat, EntityPlayer.class, 0.7D, false));
//		}
//	}

}
