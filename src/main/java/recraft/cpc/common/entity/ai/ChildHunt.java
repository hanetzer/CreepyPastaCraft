package recraft.cpc.common.entity.ai;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ChildHunt extends EntityAIBase {
	World worldObj;
	EntityCreature attacker;

	/**
	 * An amount of decrementing ticks that allows the entity to attack once the tick reaches 0.
	 */
	int attackTick;

	/** The speed with which the mob will approach the target */
	double speedTowardsTarget;

	/**
	 * When true, the mob will continue chasing its target, even if it can't find a path to them right now.
	 */
	boolean longMemory;

	/** The PathEntity of our entity. */
	PathEntity entityPathEntity;
	Class<? extends EntityAgeable> classTarget;
	private int field_75445_i;

	private int failedPathFindingPenalty;

	public ChildHunt(EntityCreature par1EntityCreature, Class<? extends EntityAgeable> par2Class, double par3, boolean par5)
	{
		this(par1EntityCreature, par3, par5);
		this.classTarget = par2Class;
	}

	public ChildHunt(EntityCreature par1EntityCreature, double par2, boolean par4)
	{
		this.attacker = par1EntityCreature;
		this.worldObj = par1EntityCreature.worldObj;
		this.speedTowardsTarget = par2;
		this.longMemory = par4;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		EntityAgeable entityAgeable = (EntityAgeable) this.attacker.getAttackTarget();

		if (entityAgeable == null)
		{
			return false;
		}
		else if (!entityAgeable.isEntityAlive())
		{
			return false;
		}
		else if (this.classTarget != null && !this.classTarget.isAssignableFrom(entityAgeable.getClass()))
		{
			return false;
		}
		else if(!entityAgeable.isChild())
		{
			return false;
		}
		else
		{
			if (-- this.field_75445_i <= 0)
			{
				this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(entityAgeable);
				this.field_75445_i = 4 + this.attacker.getRNG().nextInt(7);
				return this.entityPathEntity != null;
			}
			else
			{
				return true;
			}
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		if (this.attacker.getAttackTarget() != null) {
			if (this.attacker.getAttackTarget().isEntityAlive()) {
				if (!this.longMemory) {
					if (!this.attacker.getNavigator().noPath()) {
						return true;
					}
				} else {
					if (this.attacker.isWithinHomeDistance(MathHelper.floor_double(this.attacker.getAttackTarget().posX), MathHelper.floor_double(this.attacker.getAttackTarget().posY), MathHelper.floor_double(this.attacker.getAttackTarget().posZ))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.attacker.getNavigator().setPath(this.entityPathEntity, this.speedTowardsTarget);
		this.field_75445_i = 0;
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		this.attacker.getNavigator().clearPathEntity();
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
		this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);

		if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && --this.field_75445_i <= 0)
		{
			this.field_75445_i = failedPathFindingPenalty + 4 + this.attacker.getRNG().nextInt(7);
			this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget);
			if (this.attacker.getNavigator().getPath() != null)
			{
				PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
				if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.xCoord, finalPathPoint.yCoord, finalPathPoint.zCoord) < 1)
				{
					failedPathFindingPenalty = 0;
				}
				else
				{
					failedPathFindingPenalty += 10;
				}
			}
			else
			{
				failedPathFindingPenalty += 10;
			}
		}

		this.attackTick = Math.max(this.attackTick - 1, 0);
		double d0 = (double)(this.attacker.width * 2.0F * this.attacker.width * 2.0F + entitylivingbase.width);

		if (this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) <= d0)
		{
			if (this.attackTick <= 0)
			{
				this.attackTick = 20;

				if (this.attacker.getHeldItem() != null)
				{
					this.attacker.swingItem();
				}

				this.attacker.attackEntityAsMob(entitylivingbase);
			}
		}
	}
}
