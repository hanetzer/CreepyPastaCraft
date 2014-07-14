package recraft.cpc.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import recraft.cpc.CPC;
import recraft.cpc.init.CPCItems;

import java.util.Random;

@SuppressWarnings("unused")
public class CPCHiltBlackHandler {

	@SubscribeEvent
	public void entityAttacked(LivingAttackEvent event) {
		EntityLivingBase attackedEnt = event.entityLiving;
		Entity entity = event.source.getEntity();
		if(entity instanceof EntityPlayer && ((EntityPlayer) entity).getItemInUse().getItem() == CPCItems.hiltBlack) {
			attackedEnt.attackEntityFrom(DamageSource.generic, 2);
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			if(player.getHealth() < player.getMaxHealth()) {
				player.setHealth(player.getHealth() + 1);
			}
		}
	}

	@SubscribeEvent
	public void entityUpdate(LivingEvent.LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer && event.entity != null) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.getHeldItem() != null && player.getHeldItem().getItem() == CPCItems.hiltBlack) {
				player.addPotionEffect(new PotionEffect(Potion.blindness.id, 50, 1));
				player.addPotionEffect(new PotionEffect(Potion.wither.id, 50, 1));
				World world = player.worldObj;
				if (world.isRemote) {
					world.spawnParticle("reddust", player.posX, player.posY - 0.4D, player.posZ, 0.0D, 0.0D, 0.0D);
					if ((new Random()).nextInt(100) < 25) {
						world.spawnParticle("smoke", player.posX, player.posY - 0.4D, player.posZ, 0.0D, 0.0D, 0.0D);
					}
				}
			} else {
				player.removePotionEffect(Potion.wither.id);
				player.removePotionEffect(Potion.blindness.id);
			}
		}
	}
}
