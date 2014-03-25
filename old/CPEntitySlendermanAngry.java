package recraft.cpc.common.entity.monster;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class CPEntitySlendermanAngry extends CPEntity {

   public boolean isAttacking;
   public static int healthH;


   public CPEntitySlendermanAngry(World par1World) {
	  super(par1World);
	  super.texture = "/CreepyPastaCraft/textures/mobs/SlendingSlendy.png";
	  super.moveSpeed = 0.5F;
	  this.attackStrength = 6;
	  super.isImmuneToFire = true;
	  healthH = 90;
	  this.setSize(1.0F, 3.0F);
	  this.getNavigator().setBreakDoors(true);
	  super.tasks.addTask(0, new EntityAISwimming(this));
	  super.tasks.addTask(2, new EntityAIBreakDoor(this));
	  super.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, super.moveSpeed, false));
	  super.tasks.addTask(6, new EntityAIMoveTwardsRestriction(this, super.moveSpeed));
	  super.tasks.addTask(7, new EntityAIMoveThroughVillage(this, super.moveSpeed, false));
	  super.tasks.addTask(8, new EntityAIWander(this, super.moveSpeed));
	  super.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	  super.tasks.addTask(9, new EntityAILookIdle(this));
	  super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	  super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
   }

   public int getMaxHealth() {
	  return healthH;
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
	  par1NBTTagCompound.setInteger("healthH", healthH);
	  super.writeEntityToNBT(par1NBTTagCompound);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
	  healthH = par1NBTTagCompound.getInteger("healthH");
	  super.readEntityFromNBT(par1NBTTagCompound);
   }

   protected boolean isAIEnabled() {
	  return true;
   }

   public void onLivingUpdate() {
	  super.onLivingUpdate();
	  Random rand = new Random();
	  int i = rand.nextInt(500);
	  if(i < 3 && !super.worldObj.isRemote) {
		 this.setDead();
		 CPEntitySlendermanArms var10 = new CPEntitySlendermanArms(super.worldObj);
		 var10.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
		 var10.setEntityHealth(this.getHealth());
		 var10.renderYawOffset = super.renderYawOffset;
		 super.worldObj.spawnEntityInWorld(var10);
	  }

   }

   public void onKillEntity(EntityLiving par1EntityLiving) {}

   public void onDeath(DamageSource par1DamageSource) {
	  super.onDeath(par1DamageSource);
	  if(!super.worldObj.isRemote) {
		 ModLoader.getMinecraftInstance().thePlayer.addChatMessage("\u00a7lSlendy: \u00a7rYou just won the game...");
	  }

   }

   protected int getDropItemId() {
	  return 0;
   }
}
