package recraft.cpc.common.entity.passive;

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

   public int damageDelay;
   public EntitySquidwardMad(World world) {
	  super(world);
	  setSize(0.9F, 2.0F);
	  getNavigator().setAvoidsWater(true);
	  damageDelay = super.rand.nextInt(100);
	  tasks.addTask(0, new EntityAISwimming(this));
	  tasks.addTask(5, new EntityAIWander(this, 0.6D));
	  tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
	  tasks.addTask(7, new EntityAILookIdle(this));
   }

   public void onLivingUpdate() {
	  this.attackEntityFrom(DamageSource.drown, 15);
   }

   public boolean isAIEnabled() {
	  return true;
   }

   protected void entityInit() {
	  super.entityInit();
	  super.dataWatcher.addObject(16, (byte) 0);
   }

   public void writeEntityToNBT(NBTTagCompound compound) {
	  super.writeEntityToNBT(compound);
   }

   public void readEntityFromNBT(NBTTagCompound compound) {
	  super.readEntityFromNBT(compound);
   }

   public boolean interact(EntityPlayer par1EntityPlayer) {
	  return super.interact(par1EntityPlayer);
   }

   public EntityAgeable createChild(EntityAgeable entity) {
	  return entity;
   }
}
