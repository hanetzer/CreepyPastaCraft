package creepypastacraft.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import creepypastacraft.common.entity.ai.PewDieRun;
import creepypastacraft.init.CPCItems;

public class EntityPewds extends EntityAnimal {
    public EntityPewds(World world) {
        super(world);
        setSize(0.6F, 1.8F);
        getNavigator().setBreakDoors(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new PewDieRun(this, EntityMob.class, 8.0F, 0.6D, 0.6D));
        tasks.addTask(1, new EntityAIPanic(this, 0.6D));
        tasks.addTask(2, new EntityAIOpenDoor(this, true));
        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5D));
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.5D, false));
        tasks.addTask(6, new EntityAIWander(this, 0.6D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
    }

    public int getTalkInterval() {
        return 240;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    protected float getSoundVolume() {
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

    public void onKillEntity(EntityLiving entity) {
    }

    protected Item getDropItemId() {
        return CPCItems.stephano;
    }

    public EntityAgeable createChild(EntityAgeable entity) {
        return entity;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (worldObj.villageCollectionObj.getVillageList().iterator().hasNext()) {
            if (worldObj.villageCollectionObj.findNearestVillage((int) posX, (int) posY, (int) posZ, 10) == null) {
                return false;
            }
        }
        return true;
    }

    protected boolean canDespawn() {
        return true;
    }
}
