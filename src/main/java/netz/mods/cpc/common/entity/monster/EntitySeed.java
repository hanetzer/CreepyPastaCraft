package netz.mods.cpc.common.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import netz.mods.cpc.common.entity.ai.ChildHunt;

public class EntitySeed extends EntityMob
{
	public EntitySeed(World par1World)
	{
		super(par1World);
		isImmuneToFire = true;
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		tasks.addTask(2, new EntityAIBreakDoor(this));
		tasks.addTask(3, new ChildHunt(this, EntityVillager.class, 0.6D, false));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.6D, false));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.6D));
		tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.6D, false));
		tasks.addTask(6, new EntityAIWander(this, 0.6D));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
	}
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(32.0D);
	}

	protected boolean isAIEnabled()
	{
		return true;
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	protected int getDropItemId()
	{
		return Item.seeds.itemID;
	}
}
