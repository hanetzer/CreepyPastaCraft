package recraft.cpc.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import recraft.cpc.common.stats.CPCAchievementList;

public class EntitySquidward extends EntityAnimal {

	public EntitySquidward(World par1World) {
		super(par1World);
		this.setSize(0.9F, 2.0F);
		this.getNavigator().setAvoidsWater(true);
		float var2 = 0.25F;
		super.tasks.addTask(0, new EntityAISwimming(this));
		super.tasks.addTask(5, new EntityAIWander(this, var2));
		super.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		super.tasks.addTask(7, new EntityAILookIdle(this));
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
		if(super.interact(par1EntityPlayer)) {
			return true;
		} else if(!super.worldObj.isRemote) {
			this.setDead();
			EntitySquidwardSuicide var10 = new EntitySquidwardSuicide(super.worldObj);
			var10.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
			var10.setHealth(this.getMaxHealth());
			var10.renderYawOffset = super.renderYawOffset;
			super.worldObj.spawnEntityInWorld(var10);
			par1EntityPlayer.addStat(CPCAchievementList.suicide, 1);
			return true;
		} else {
			return false;
		}
	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal par1EntityAnimal) {
		return new EntitySquidward(super.worldObj);
	}

	public static boolean spawnCreature(World par0World, int par1, double par2, double par4, double par6) {
		EntitySquidwardSuicide var8 = new EntitySquidwardSuicide(par0World);
		var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
		par0World.spawnEntityInWorld(var8);
		var8.playLivingSound();
		return var8 != null;
	}

	public EntityAgeable createChild(EntityAgeable var1) {
		return var1;
	}
}
