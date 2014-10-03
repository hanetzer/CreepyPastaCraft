package com.teamfear.creepypastacraft.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import com.teamfear.creepypastacraft.common.stats.CPCAchievementList;

public class EntitySquidward extends EntityAnimal {
    private int mood;
    private int madTime;

    public EntitySquidward(World world) {
        super(world);
        setSize(0.6F, 1.8F);
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(5, new EntityAIWander(this, 0.6D));
        tasks.addTask(7, new EntityAILookIdle(this));
        tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        //setMood(0);
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, 0);
    }

    public int getMood() {
        return (this.dataWatcher.getWatchableObjectInt(16));
    }

    private void setMood(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    public void onLivingUpdate() {
        switch (getMood()) {
            case 0:
                super.onLivingUpdate();
                break;
            case 1:
                if (!super.worldObj.isRemote && --this.madTime <= 0) {
                    setMood(2);
                    super.onLivingUpdate();
                }
                break;
            case 2:
                super.onLivingUpdate();
                worldObj.playSoundAtEntity(this, "mob.wolf.growl", 1.0F, (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
                attackEntityFrom(DamageSource.drown, 15);
                break;
        }
    }

    public boolean interact(EntityPlayer player) {
        if(getMood() != 0) {
            return false;
        }
        else if(super.interact(player)) {
            return true;
        }
        else if(!super.worldObj.isRemote) {
            setMood(1);
            madTime = rand.nextInt(200);
            player.addStat(CPCAchievementList.suicide, 1);
            return true;
        }
        else {
            return false;
        }
    }

    public EntityAgeable createChild(EntityAgeable entity) {
        return entity;
    }
}
