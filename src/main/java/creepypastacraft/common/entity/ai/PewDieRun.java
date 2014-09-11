package creepypastacraft.common.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

import java.util.List;
import java.util.Random;

public class PewDieRun extends EntityAIAvoidEntity {
    private EntityCreature theEntity;
    private double farSpeed;
    private double nearSpeed;
    private Entity closestLivingEntity;
    private double distanceFromEntity;
    private PathEntity entityPathEntity;
    private PathNavigate entityPathNavigate;
    private Class<? extends EntityMob> targetEntityClass;

    public PewDieRun(EntityCreature creature, Class<? extends EntityMob> klazz,
                     double par3, double par4, double par5) {
        super(creature, klazz, 1, par5, par5);
        theEntity = creature;
        targetEntityClass = klazz;
        distanceFromEntity = par3;
        farSpeed = par4;
        nearSpeed = par5;
        entityPathNavigate = creature.getNavigator();
        setMutexBits(1);
    }

    @SuppressWarnings("rawtypes")
    public boolean shouldExecute() {
        if (targetEntityClass != EntityMob.class) {
            if (theEntity instanceof EntityTameable && ((EntityTameable) theEntity).isTamed()) {
                return false;
            }

            closestLivingEntity = theEntity.worldObj.getClosestPlayerToEntity(theEntity, distanceFromEntity);
            if (closestLivingEntity == null) {
                return false;
            }
        } else {
            List list = theEntity.worldObj.getEntitiesWithinAABB(targetEntityClass,
                    theEntity.boundingBox.expand(distanceFromEntity, 3.0D, distanceFromEntity));
            if (list.isEmpty()) {
                return false;
            }

            closestLivingEntity = (Entity) list.get(0);
        }

        if (!theEntity.getEntitySenses().canSee(closestLivingEntity)) {
            return false;
        } else {
            Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(theEntity,
                    16, 7, Vec3.createVectorHelper(
                            closestLivingEntity.posX,
                            closestLivingEntity.posY,
                            closestLivingEntity.posZ)
            );
            if (vec3 == null) {
                return false;
            } else if (closestLivingEntity.getDistanceSq(vec3.xCoord, vec3.yCoord, vec3.zCoord) < closestLivingEntity.getDistanceSqToEntity(theEntity)) {
                return false;
            } else {
                entityPathEntity = entityPathNavigate.getPathToXYZ(
                        vec3.xCoord, vec3.yCoord, vec3.zCoord);
                return entityPathEntity != null && entityPathEntity.isDestinationSame(vec3);
            }
        }
    }

    public boolean continueExecuting() {
        return !entityPathNavigate.noPath();
    }

    public void startExecuting() {
        entityPathNavigate.setPath(entityPathEntity, farSpeed);
        theEntity.worldObj.playSoundAtEntity(theEntity, "creepypastacraft:mob.pewds.scare", 1.0F, 1.0F);
    }

    public void resetTask() {
        closestLivingEntity = null;
    }

    public void updateTask() {
        if (theEntity.getDistanceSqToEntity(closestLivingEntity) < 49.0D) {
            new Random();
            theEntity.getNavigator().setSpeed(nearSpeed);
        } else {
            theEntity.getNavigator().setSpeed(farSpeed);
        }
    }
}
