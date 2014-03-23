package netz.mods.cpc.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import netz.mods.cpc.client.gui.achievement.CPCAchievement;

public class EntityStrider extends EntityAnimal {

   private final EntityAIControlledByPlayer aiControlledByPlayer;


   public EntityStrider(World par1World) {
	  super(par1World);
	  this.setSize(2.0F, 2.5F);
	  this.getNavigator().setAvoidsWater(true);
	  float var2 = 0.2F;
	  float moveSpeed = 0.2F;
	  super.tasks.addTask(0, new EntityAISwimming(this));
	  super.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
	  super.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.34F));
	  super.tasks.addTask(2, new EntityAIMate(this, var2));
	  super.tasks.addTask(4, new EntityAIFollowParent(this, 0.28F));
	  super.tasks.addTask(5, new EntityAIWander(this, var2));
	  super.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
	  super.tasks.addTask(7, new EntityAILookIdle(this));
   }

   public boolean isAIEnabled() {
	  return true;
   }

   protected void updateAITasks() {
	  super.updateAITasks();
   }

   public boolean canBeSteered() {
	  ItemStack var1 = ((EntityPlayer)super.riddenByEntity).getHeldItem();
	  return var1 != null || var1 == null;
   }

   public EntityAIControlledByPlayer getAIControlledByPlayer() {
	  return this.aiControlledByPlayer;
   }

   protected void entityInit() {
	  super.entityInit();
	  super.dataWatcher.addObject(16, Byte.valueOf((byte)0));
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
	  super.writeEntityToNBT(par1NBTTagCompound);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
	  super.readEntityFromNBT(par1NBTTagCompound);
   }

   public double getMountedYOffset() {
	  return 2.7D;
   }

   public boolean interact(EntityPlayer par1EntityPlayer) {
	  if(super.interact(par1EntityPlayer)) {
		 return true;
	  } else if(!super.worldObj.isRemote && super.riddenByEntity == null) {
		 par1EntityPlayer.mountEntity(this);
		 par1EntityPlayer.addStat(CPCAchievement.rideStrider, 1);
		 return true;
	  } else {
		 ItemStack var1 = par1EntityPlayer.getCurrentEquippedItem();
		 if(!super.worldObj.isRemote && super.riddenByEntity == par1EntityPlayer && var1 == null) {
			par1EntityPlayer.mountEntity(this);
			return true;
		 } else {
			return false;
		 }
	  }
   }

   protected void fall(float par1) {
	  super.fall(par1);
   }

   public EntityAnimal spawnBabyAnimal(EntityAnimal par1EntityAnimal) {
	  return new EntityStrider(super.worldObj);
   }

   public EntityAgeable createChild(EntityAgeable var1) {
	  return var1;
   }

}
