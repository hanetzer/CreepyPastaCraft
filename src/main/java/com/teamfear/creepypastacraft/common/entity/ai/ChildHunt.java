package com.teamfear.creepypastacraft.common.entity.ai;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
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

    /**
     * The speed with which the mob will approach the target
     */
    double speedTowardsTarget;

    /**
     * When true, the mob will continue chasing its target, even if it can't find a path to them right now.
     */
    boolean longMemory;

    /**
     * The PathEntity of our entity.
     */
    PathEntity entityPathEntity;
    Class<? extends EntityAgeable> classTarget;
    private int field_75445_i;

    private int failedPathFindingPenalty;

    public ChildHunt(EntityCreature creature, Class<? extends EntityAgeable> target,
                     double speed, boolean memory) {
        this(creature, speed, memory);
        classTarget = target;
    }

    public ChildHunt(EntityCreature creature, double speed, boolean memory) {
        attacker = creature;
        worldObj = creature.worldObj;
        speedTowardsTarget = speed;
        longMemory = memory;
        setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        EntityLivingBase entity = attacker.getAttackTarget();

        if (entity == null) {
            return false;
        } else if (!(entity instanceof EntityVillager)) {
            return false;
        } else if (!entity.isEntityAlive()) {
            return false;
        } else if (classTarget != null && !classTarget.isAssignableFrom(entity.getClass())) {
            return false;
        } else if (!entity.isChild()) {
            return false;
        } else {
            if (--field_75445_i <= 0) {
                entityPathEntity = attacker.getNavigator().getPathToEntityLiving(entity);
                field_75445_i = 4 + attacker.getRNG().nextInt(7);
                return entityPathEntity != null;
            } else {
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        if (attacker.getAttackTarget() != null) {
            if (attacker.getAttackTarget().isEntityAlive()) {
                if (!longMemory) {
                    if (!attacker.getNavigator().noPath()) {
                        return true;
                    }
                } else {
                    if (attacker.isWithinHomeDistance(
                            MathHelper.floor_double(attacker.getAttackTarget().posX),
                            MathHelper.floor_double(attacker.getAttackTarget().posY),
                            MathHelper.floor_double(attacker.getAttackTarget().posZ))) {
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
        attacker.getNavigator().setPath(entityPathEntity, speedTowardsTarget);
        field_75445_i = 0;
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        attacker.getNavigator().clearPathEntity();
    }

    /**
     * Updates the task
     */
    public void updateTask() {
        EntityLivingBase entity = attacker.getAttackTarget();
        attacker.getLookHelper().setLookPositionWithEntity(entity, 30.0F, 30.0F);

        if ((longMemory || attacker.getEntitySenses().canSee(entity)) && --field_75445_i <= 0) {
            field_75445_i = failedPathFindingPenalty + 4 + attacker.getRNG().nextInt(7);
            attacker.getNavigator().tryMoveToEntityLiving(entity, speedTowardsTarget);
            if (attacker.getNavigator().getPath() != null) {
                PathPoint finalPathPoint = attacker.getNavigator().getPath().getFinalPathPoint();
                if (finalPathPoint != null && entity.getDistanceSq(
                        finalPathPoint.xCoord,
                        finalPathPoint.yCoord,
                        finalPathPoint.zCoord) < 1) {
                    failedPathFindingPenalty = 0;
                } else {
                    failedPathFindingPenalty += 10;
                }
            } else {
                failedPathFindingPenalty += 10;
            }
        }

        attackTick = Math.max(attackTick - 1, 0);
        double d0 = (double) (attacker.width * 2.0F * attacker.width * 2.0F + entity.width);

        if (attacker.getDistanceSq(entity.posX, entity.boundingBox.minY, entity.posZ) <= d0) {
            if (this.attackTick <= 0) {
                this.attackTick = 20;

                if (attacker.getHeldItem() != null) {
                    attacker.swingItem();
                }
                attacker.attackEntityAsMob(entity);
            }
        }
    }
}
