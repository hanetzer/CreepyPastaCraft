package netz.mods.cpc.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySquidwardMad extends EntityAnimal {

   public int DamageDelay;


   public EntitySquidwardMad(World par1World) {
	  super(par1World);
	  this.setSize(0.9F, 2.0F);
	  this.getNavigator().setAvoidsWater(true);
	  float var2 = 0.25F;
	  this.DamageDelay = super.rand.nextInt(100);
	  super.tasks.addTask(0, new EntityAISwimming(this));
	  super.tasks.addTask(5, new EntityAIWander(this, var2));
	  super.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
	  super.tasks.addTask(7, new EntityAILookIdle(this));
   }

   public void onLivingUpdate() {
	  this.attackEntityFrom(DamageSource.drown, 15);
   }

   public boolean isAIEnabled() {
	  return true;
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

   public boolean interact(EntityPlayer par1EntityPlayer) {
	  return super.interact(par1EntityPlayer);
   }

   public EntityAnimal spawnBabyAnimal(EntityAnimal par1EntityAnimal) {
	  return new EntitySquidwardMad(super.worldObj);
   }

   public EntityAgeable createChild(EntityAgeable var1) {
	  return var1;
   }

}
