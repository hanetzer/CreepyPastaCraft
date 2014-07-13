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
		DamageSource attackSource = event.source;
		if(attackSource.getEntity() instanceof EntityPlayer && ((EntityPlayer) attackSource.getEntity()).getItemInUse().getItem() == CPCItems.hiltBlack && ((EntityPlayer) attackSource.getEntity()).getItemInUse() != null) {
			attackedEnt.attackEntityFrom(DamageSource.generic, 2);
			if(CPC.mc.thePlayer.getHealth() < CPC.mc.thePlayer.getMaxHealth()) {
				CPC.mc.thePlayer.setHealth(CPC.mc.thePlayer.getHealth() + 1);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(event.player.getHeldItem().getItem() == CPCItems.hiltBlack) {
			event.player.addPotionEffect(new PotionEffect(Potion.blindness.id, 50, 1));
			event.player.addPotionEffect(new PotionEffect(Potion.wither.id, 50, 1));
			if(event.player.worldObj.isRemote) {
				event.player.worldObj.spawnParticle("reddust", event.player.posX, event.player.posY - 0.4D, event.player.posZ, 0.0D, 0.0D, 0.0D);
				if ((new Random()).nextInt(100) < 25) {
					event.player.worldObj.spawnParticle("smoke", event.player.posX, event.player.posY - 0.4D, event.player.posZ, 0.0D, 0.0D, 0.0D);
				}
			}

		}
		else {
			event.player.removePotionEffect(Potion.wither.id);
			event.player.removePotionEffect(Potion.blindness.id);
		}
	}

}
