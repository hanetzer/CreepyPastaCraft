package recraft.cpc.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import recraft.cpc.common.entity.monster.EntityJeff;

/**
 * Created by netz on 7/11/14.
 */
public class CPCAIHandler {
	@SubscribeEvent
	public void villagerSpawnEvent(LivingSpawnEvent event)
	{
		EntityLivingBase villager = event.entityLiving;
		if(villager instanceof EntityVillager)
		{
			((EntityVillager) villager).tasks.addTask(1, new EntityAIAvoidEntity((EntityCreature) villager, EntityJeff.class, 8.0F, 0.6D, 0.6D));
		}
	}

}
