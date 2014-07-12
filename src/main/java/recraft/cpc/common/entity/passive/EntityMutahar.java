package recraft.cpc.common.entity.passive;

import recraft.cpc.common.entity.monster.CPEntity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMutahar extends EntityAnimal
{
	public boolean isAttacking;

	public EntityMutahar(World par1World) {
		super(par1World);
		float moveSpeed = 0.2F;
		this.setSize(1.0F,0.5F);
		super.isImmuneToFire = true;
		float var2 = 0.25F;
		this.getNavigator().setBreakDoors(true);
		super.tasks.addTask(0, new EntityAISwimming(this));
		super.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		super.tasks.addTask(2, new EntityAIBreakDoor(this));
		super.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, moveSpeed));
		super.tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
		super.tasks.addTask(6, new EntityAIWander(this, var2));
		super.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		super.tasks.addTask(8, new EntityAILookIdle(this));
		super.tasks.addTask(1, new EntityAIAvoidEntity(this, CPEntity.class, 6.0F, 0.25F, 0.3F));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
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
	
	public void onKillEntity(EntityLiving par1EntityLiving) {}

	public EntityAgeable createChild(EntityAgeable var1) {
		return var1;
	}
}
