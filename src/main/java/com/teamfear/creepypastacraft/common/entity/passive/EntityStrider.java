package com.teamfear.creepypastacraft.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityStrider extends EntityAnimal {
    private final EntityAIControlledByPlayer ai;

    public EntityStrider(World world) {
        super(world);
        setSize(2.0F, 2.5F);
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.0D));
        tasks.addTask(2, this.ai = new EntityAIControlledByPlayer(this, 0.4F));
        tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        tasks.addTask(5, new EntityAIWander(this, 1.0D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(7, new EntityAILookIdle(this));
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected void updateAITasks() {
        super.updateAITasks();
    }

    public boolean canBeSteered() {
        //ItemStack heldItem = ((EntityPlayer) super.riddenByEntity).getHeldItem();
        //return heldItem != null;
        return true;
    }

    public EntityAIControlledByPlayer getAIControlledByPlayer() {
        return this.ai;
    }

    public double getMountedYOffset() {
        return 2.7D;
    }

    public boolean interact(EntityPlayer player) {
        if (super.interact(player)) {
            return true;
        } else if (!worldObj.isRemote && (riddenByEntity == null || riddenByEntity == player)) {
            player.mountEntity(this);
            return true;
        } else {
            return false;
        }
    }

    protected void fall(float par1) {
        super.fall(par1);
    }

    public EntityAgeable createChild(EntityAgeable entity) {
        return entity;
    }
}
