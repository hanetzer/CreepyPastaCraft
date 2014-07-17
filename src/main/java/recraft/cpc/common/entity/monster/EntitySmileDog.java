package recraft.cpc.common.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySmileDog extends CPEntity {

    public static ResourceLocation resourceLocation;
    private int stareTime;
    public boolean isAttacking;
    private boolean isAgressive;
    public ItemStack itemstack;


    public EntitySmileDog(World world) {
        super(world);
        setSize(0.6F, 0.8F);
        stepHeight = 1.0F;
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(7.0F);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public void setAttackTarget(EntityLivingBase entity) {
        super.setAttackTarget(entity);

        if (entity == null) {
            setAngry(false);
        } else {
            setAngry(true);
        }
    }

    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(16, (byte) 0);
    }

    /**
     * Determines whether this wolf is angry or not.
     */
    public boolean isAngry() {
        return (dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    /**
     * Sets whether this wolf is angry or not.
     */
    public void setAngry(boolean bool) {
        byte b0 = dataWatcher.getWatchableObjectByte(16);

        if (bool) {
            dataWatcher.updateObject(16, (byte) (b0 | 2));
        } else {
            dataWatcher.updateObject(16, (byte) (b0 & -3));
        }
    }


    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Angry", isAngry());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        setAngry(compound.getBoolean("Angry"));
    }

    protected boolean canDespawn() {
        return false;
    }

    protected String getLivingSound() {
        return "mob.wolf.bark";
    }

    protected String getHurtSound() {
        return "mob.wolf.hurt";
    }

    protected String getDeathSound() {
        return "mob.wolf.death";
    }

    protected void fall(float par1) {
        super.fall(par1);
    }

    protected Entity findPlayerToAttack() {
        EntityPlayer player = super.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);
        if (player != null) {
            if (this.shouldAttackPlayer(player)) {
                isAgressive = true;
                if (stareTime++ == 5) {
                    stareTime = 0;
                    setAngry(true);
                    return player;
                }

                player.addChatMessage(new ChatComponentText("Spread The Word."));
                //isAngry = true;
                setAngry(true);
                player.addPotionEffect(new PotionEffect(Potion.blindness.id, 700, 50));
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 250, 50));
                player.addPotionEffect(new PotionEffect(Potion.harm.id, 1, 1));
                player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80, 2));
            }
            else {
                this.stareTime = 0;
            }
        }
        return null;
    }

    private boolean shouldAttackPlayer(EntityPlayer player) {
        if (player == null) {
            return false;
        } else {
            Vec3 vec3 = player.getLook(1.0F).normalize();
            Vec3 vec31 = Vec3.createVectorHelper(
                    posX - player.posX,
                    boundingBox.minY + (double) (height / 2.0F) - (player.posY + (double) player.getEyeHeight()),
                    posZ - player.posZ);
            double d0 = vec31.lengthVector();
            vec31 = vec31.normalize();
            double d1 = vec3.dotProduct(vec31);
            return d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(this);
        }
    }

    public void onLivingUpdate() {
        super.motionX = 0.0D;
        super.motionZ = 0.0D;
        isAttacking = entityToAttack != null;
        super.moveSpeed = super.entityToAttack == null ? 0.3F : 6.5F;

        if (super.worldObj.isDaytime() && !super.worldObj.isRemote) {
            float f1 = this.getBrightness(1.0F);
            if (super.rand.nextFloat() * 30.0F < (f1 - 0.4F) * 2.0F) {
                if (f1 > 0.5F && super.worldObj.canBlockSeeTheSky(
                        MathHelper.floor_double(super.posX),
                        MathHelper.floor_double(super.posY),
                        MathHelper.floor_double(super.posZ))) {
                    super.entityToAttack = null;
                }
            }
        }

        super.isJumping = false;
        if (super.entityToAttack != null) {
            this.faceEntity(super.entityToAttack, 100.0F, 100.0F);
        }

        if (!super.worldObj.isRemote && this.isEntityAlive()) {
            if (super.entityToAttack != null && super.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer) super.entityToAttack)) {
                super.moveStrafing = super.moveForward = 0.0F;
                super.moveSpeed = 0.0F;
            }
            super.onLivingUpdate();
        }
    }
}
