package creepypastacraft.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

import java.util.Random;

public class CPCBatHandler
{
	@SubscribeEvent
	public void batSpawnEvent(LivingSpawnEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		int rand = new Random().nextInt(5);
		switch (rand){
			case 0:
				if (entity instanceof EntityBat)
				{
					event.setCanceled(true);
				}
				break;
			default:
				break;
		}
	}
}
