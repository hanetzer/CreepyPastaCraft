package recraft.cpc;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import recraft.cpc.common.item.ItemHiltBlack;

public class EventHooks
{
	Minecraft mc = Minecraft.getMinecraft();
	EntityPlayer ep = mc.thePlayer;

	public EventHooks()
	{
		super();
		this.ep = this.mc.thePlayer;
	}

	@ForgeSubscribe
	public void entityAttacked(LivingAttackEvent event)
	{
		EntityLivingBase attackedEnt = event.entityLiving;
		DamageSource attackSource = event.source;
		if(ep != null && attackSource.getEntity() instanceof EntityPlayer && ep.getHeldItem() != null && attackedEnt != null && attackSource != null && ItemSword.itemsList[ep.getHeldItem().itemID] instanceof ItemHiltBlack)
		{
			attackedEnt.attackEntityFrom(DamageSource.generic, 2);
			if(ep.getHealth() < ep.getMaxHealth())
			{
				ep.setHealth(ep.getHealth() + 1);
			}
		}
	}
	
	/*@SubscribeEvent
	public void villagerSpawnEvent(LivingSpawnEvent event)
	{
		EntityLivingBase villager = event.entityLiving;
		if(villager instanceof EntityVillager)
		{
			((EntityVillager) villager).tasks.addTask(1, new EntityAIAvoidEntity((EntityCreature) villager, EntityJeff.class, 8.0F, 0.6D, 0.6D));
		}
	}*/
	
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
