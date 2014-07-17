package recraft.cpc.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import recraft.cpc.common.stats.CPCAchievementList;

public class EntitySquidward extends EntityAnimal {

    public EntitySquidward(World world) {
        super(world);
        setSize(0.6F, 1.8F);
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(5, new EntityAIWander(this, 0.6D));
        tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        tasks.addTask(7, new EntityAILookIdle(this));
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected void entityInit() {
        super.entityInit();
        super.dataWatcher.addObject(16, (byte) 0);
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }

    public boolean interact(EntityPlayer player) {
        if (super.interact(player)) {
            return true;
        } else if (!super.worldObj.isRemote) {
            this.setDead();
            EntitySquidwardSuicide entity = new EntitySquidwardSuicide(super.worldObj);
            entity.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
            entity.setHealth(this.getMaxHealth());
            entity.renderYawOffset = super.renderYawOffset;
            super.worldObj.spawnEntityInWorld(entity);
            player.addStat(CPCAchievementList.suicide, 1);
            return true;
        } else {
            return false;
        }
    }

    public static boolean spawnCreature(World world, int par1, double x, double y, double z) {
        EntitySquidwardSuicide entity = new EntitySquidwardSuicide(world);
        entity.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
        world.spawnEntityInWorld(entity);
        entity.playLivingSound();
        return true;
    }

    public EntityAgeable createChild(EntityAgeable entity) {
        return entity;
    }
}
