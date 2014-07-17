package recraft.cpc.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import recraft.cpc.common.entity.monster.EntityJeff;
import recraft.cpc.common.entity.monster.EntityRake;
import recraft.cpc.common.entity.monster.EntitySeed;

public class CPCAIHandler {
    @SubscribeEvent
    public void entitySpawnEvent(LivingSpawnEvent event) {
        EntityLivingBase entity = event.entityLiving;
        if (entity instanceof EntityVillager) {
            ((EntityVillager) entity).tasks.addTask(1, new EntityAIAvoidEntity((EntityCreature) entity, EntityJeff.class, 8.0F, 0.6D, 0.6D));
            ((EntityVillager) entity).tasks.addTask(1, new EntityAIAvoidEntity((EntityCreature) entity, EntityRake.class, 8.0F, 0.6D, 0.6D));
            ((EntityVillager) entity).tasks.addTask(1, new EntityAIAvoidEntity((EntityCreature) entity, EntitySeed.class, 8.0F, 0.6D, 0.6D));
        }
        //	else if(entity instanceof EntityBat) {
        //		((EntityBat) entity).tasks.addTask(1, new EntityAIAttackOnCollide((EntityCreature)entity, EntityPlayer.class, 7.0D, false));
        //	}
    }
}
