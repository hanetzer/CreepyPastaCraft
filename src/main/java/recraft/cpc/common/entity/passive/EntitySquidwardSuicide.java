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

public class EntitySquidwardSuicide extends EntityAnimal {

    private int sheepTimer;
    public boolean field_70885_d = false;
    public float field_70886_e = 0.0F;
    public float destPos = 0.0F;
    public float field_70884_g;
    public float field_70888_h;
    public float field_70889_i = 1.0F;
    public int madTime;


    public EntitySquidwardSuicide(World par1World) {
        super(par1World);
        this.setSize(0.9F, 2.0F);
        this.getNavigator().setAvoidsWater(true);
        float var2 = 0.25F;
        this.madTime = super.rand.nextInt(200);
        super.tasks.addTask(0, new EntityAISwimming(this));
        super.tasks.addTask(5, new EntityAIWander(this, var2));
        super.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        super.tasks.addTask(7, new EntityAILookIdle(this));
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.field_70888_h = this.field_70886_e;
        this.field_70884_g = this.destPos;
        this.destPos = (float) ((double) this.destPos + (double) (super.onGround ? -1 : 4) * 0.3D);
        if (this.destPos < 0.0F) {
            this.destPos = 0.0F;
        }

        if (this.destPos > 1.0F) {
            this.destPos = 1.0F;
        }

        if (!super.onGround && this.field_70889_i < 1.0F) {
            this.field_70889_i = 1.0F;
        }

        this.field_70889_i = (float) ((double) this.field_70889_i * 0.9D);
        if (!super.onGround && super.motionY < 0.0D) {
            super.motionY *= 0.6D;
        }

        this.field_70886_e += this.field_70889_i * 2.0F;
        if (!this.isChild() && --this.madTime <= 0) {
            if (!super.worldObj.isRemote) {
                this.setDead();
                EntitySquidwardMad var10 = new EntitySquidwardMad(super.worldObj);
                var10.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
                var10.setHealth(this.getMaxHealth());
                var10.renderYawOffset = super.renderYawOffset;
                super.worldObj.spawnEntityInWorld(var10);
                super.worldObj.playSoundAtEntity(this, "mob.wolf.growl", 1.0F, (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.madTime = super.rand.nextInt(200);
        }

    }

    public boolean isAIEnabled() {
        return true;
    }

    protected void entityInit() {
        super.entityInit();
        super.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
    }

    public boolean interact(EntityPlayer par1EntityPlayer) {
        return super.interact(par1EntityPlayer);
    }

    public EntityAnimal spawnBabyAnimal(EntityAnimal par1EntityAnimal) {
        return new EntitySquidwardSuicide(super.worldObj);
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return var1;
    }
}
