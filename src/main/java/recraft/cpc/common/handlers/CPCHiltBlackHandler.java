package recraft.cpc.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import recraft.cpc.CPC;
import recraft.cpc.init.CPCItems;

import java.util.Random;

public class CPCHiltBlackHandler {
	@SubscribeEvent
	public void entityAttacked(LivingAttackEvent event) {
		EntityLivingBase attackedEnt = event.entityLiving;
		Entity entity = event.source.getEntity();
		if(entity instanceof EntityPlayer && ((EntityPlayer) entity).getItemInUse().getItem() == CPCItems.hiltBlack) {
			attackedEnt.attackEntityFrom(DamageSource.generic, 2);
			EntityClientPlayerMP player = CPC.mc.thePlayer;
			if(player.getHealth() < player.getMaxHealth()) {
				player.setHealth(player.getHealth() + 1);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if(player.getHeldItem().getItem() == CPCItems.hiltBlack) {
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 50, 1));
			player.addPotionEffect(new PotionEffect(Potion.wither.id, 50, 1));
			World world = player.worldObj;
			if(world.isRemote) {
				world.spawnParticle("reddust", player.posX, player.posY - 0.4D, player.posZ, 0.0D, 0.0D, 0.0D);
				if ((new Random()).nextInt(100) < 25) {
					world.spawnParticle("smoke", player.posX, player.posY - 0.4D, player.posZ, 0.0D, 0.0D, 0.0D);
				}
			}

		}
		else {
			player.removePotionEffect(Potion.wither.id);
			player.removePotionEffect(Potion.blindness.id);
		}
	}

}
