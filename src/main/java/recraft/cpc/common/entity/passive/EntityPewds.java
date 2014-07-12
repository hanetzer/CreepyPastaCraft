package recraft.cpc.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import recraft.cpc.common.entity.ai.PewDieRun;
import recraft.cpc.core.CPCItem;
import recraft.cpc.init.CPCItems;

public class EntityPewds extends EntityAnimal
{
	public EntityPewds(World par1World)
	{
		super(par1World);
		setSize(1.0F,0.5F);
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new PewDieRun(this, EntityMob.class , 8.0F, 0.6D, 0.6D));
		tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		tasks.addTask(2, new EntityAIOpenDoor(this, true));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5D));
		tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.5D, false));
		tasks.addTask(6, new EntityAIWander(this, 0.5D));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
	}

	public int getTalkInterval() {
		return 160;
	}

	protected boolean isAIEnabled() {
		return true;
	}
	protected float getSoundVolume()
	{
		return 0.4F;
	}
	protected String getLivingSound() {
		return "cpc:mob.pewds.say";
	}

	protected String getHurtSound() {
		return "cpc:mob.pewds.hurt";
	}

	protected String getDeathSound() {
		return "cpc:mob.pewds.death";
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public void onKillEntity(EntityLiving par1EntityLiving) {}

	protected Item getDropItemId() {
		return CPCItems.stephano;
	}

	public EntityAgeable createChild(EntityAgeable var1) {
		return var1;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if(worldObj.villageCollectionObj.getVillageList().iterator().hasNext() && worldObj.villageCollectionObj.findNearestVillage((int)this.posX, (int)this.posY, (int)this.posZ, 10) == null)
		{
			return false;
		}
		return true;
	}

	protected boolean canDespawn()
	{
		return true;
	}
}
