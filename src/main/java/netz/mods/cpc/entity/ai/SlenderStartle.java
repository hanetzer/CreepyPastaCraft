package netz.mods.cpc.entity.ai;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

public class SlenderStartle extends EntityAIBase
{
	private EntityCreature theEntity;
	private double farSpeed;
	private double nearSpeed;
	private Entity closestLivingEntity;
	private double distanceFromEntity;
	private PathEntity entityPathEntity;
	private PathNavigate entityPathNavigate;
	private Class<?extends EntityMob> targetEntityClass;

	public SlenderStartle(EntityCreature par1EntityCreature, Class<?extends EntityMob> par2Class, double par3, double par4, double par5)
	{
		super();
		this.theEntity = par1EntityCreature;
		this.targetEntityClass = par2Class;
		this.distanceFromEntity = par3;
		this.farSpeed = par4;
		this.nearSpeed = par5;
		this.entityPathNavigate = par1EntityCreature.getNavigator();
		this.setMutexBits(1);
	}

	@SuppressWarnings("rawtypes")
	public boolean shouldExecute() {
		if(this.targetEntityClass != EntityMob.class) {
			if(this.theEntity instanceof EntityTameable && ((EntityTameable)this.theEntity).isTamed()) {
				return false;
			}

			this.closestLivingEntity = this.theEntity.worldObj.getClosestPlayerToEntity(this.theEntity, (double)this.distanceFromEntity);
			if(this.closestLivingEntity == null) {
				return false;
			}
		} else {
			List var2 = this.theEntity.worldObj.getEntitiesWithinAABB(this.targetEntityClass, this.theEntity.boundingBox.expand((double)this.distanceFromEntity, 3.0D, (double)this.distanceFromEntity));
			if(var2.isEmpty()) {
				return false;
			}

			this.closestLivingEntity = (Entity)var2.get(0);
		}

		if(!this.theEntity.getEntitySenses().canSee(this.closestLivingEntity)) {
			return false;
		} else {
			Vec3 var21 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, this.theEntity.worldObj.getWorldVec3Pool().getVecFromPool(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
			if(var21 == null) {
				return false;
			} else if(this.closestLivingEntity.getDistanceSq(var21.xCoord, var21.yCoord, var21.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity)) {
				return false;
			} else {
				this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(var21.xCoord, var21.yCoord, var21.zCoord);
				return this.entityPathEntity == null?false:this.entityPathEntity.isDestinationSame(var21);
			}
		}
	}

	public boolean continueExecuting() {
		return !this.entityPathNavigate.noPath();
	}

	public void startExecuting() {
		this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
		this.theEntity.worldObj.playSoundAtEntity(this.theEntity, "cpc:mob.pewds.scare", 1.0F, 1.0F);
	}

	public void resetTask() {
		this.closestLivingEntity = null;
	}

	public void updateTask() {
		if(this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D) {
			new Random();
			this.theEntity.getNavigator().setSpeed(this.nearSpeed);
		} else {
			this.theEntity.getNavigator().setSpeed(this.farSpeed);
		}

	}
}
