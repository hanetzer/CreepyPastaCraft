package com.teamfear.creepypastacraft.common.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import com.teamfear.creepypastacraft.common.entity.passive.EntityCry;
import com.teamfear.creepypastacraft.common.entity.passive.EntityPewds;

public abstract class CPCEntity extends EntityMob implements IMob {
    protected int attackStrength = 2;
    float moveSpeed;

    public CPCEntity(World world) {
        super(world);
        super.experienceValue = 5;
        super.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPewds.class, moveSpeed, false));
        super.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCry.class, moveSpeed, false));
        super.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPewds.class, 0, true));
        super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCry.class, 0, true));
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    public void onUpdate() {
        super.onUpdate();
    }

    protected Entity findPlayerToAttack() {
        EntityPlayer player;
        player = super.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
        if (player != null && this.canEntityBeSeen(player)) {
            return player;
        } else {
            return null;
        }
    }

    public boolean attackEntityFrom(DamageSource source, int par2) {
        if (super.attackEntityFrom(source, par2)) {
            Entity var3 = source.getEntity();
            if (super.riddenByEntity != var3 && super.ridingEntity != var3) {
                if (var3 != this) {
                    super.entityToAttack = var3;
                }
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean attackEntityAsMob(Entity entity) {
        int var2 = this.attackStrength;
        if (this.isPotionActive(Potion.damageBoost)) {
            var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness)) {
            var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
    }

    protected void attackEntity(Entity entity, float par2) {
        if (super.attackTime <= 0) {
            if (entity.boundingBox.minY < super.boundingBox.maxY) {
                if (entity.boundingBox.maxY > super.boundingBox.minY) {
                    if (par2 < 2.0F) {
                        super.attackTime = 20;
                        this.attackEntityAsMob(entity);
                    }
                }
            }
        }
    }

    public float getBlockPathWeight(int x, int y, int z) {
        return 0.5F - super.worldObj.getLightBrightness(x, y, z);
    }

    protected boolean isValidLightLevel() {
        int x = MathHelper.floor_double(super.posX);
        int y = MathHelper.floor_double(super.posY);
        int z = MathHelper.floor_double(super.posZ);
        if (super.worldObj.getSavedLightValue(EnumSkyBlock.Sky, x, y, z) > super.rand.nextInt(32)) {
            return false;
        } else {
            int lightValue = super.worldObj.getBlockLightValue(x, y, z);
            if (super.worldObj.isThundering()) {
                int var5 = super.worldObj.skylightSubtracted;
                super.worldObj.skylightSubtracted = 10;
                lightValue = super.worldObj.getBlockLightValue(x, y, z);
                super.worldObj.skylightSubtracted = var5;
            }
            return lightValue <= super.rand.nextInt(8);
        }
    }

    public boolean getCanSpawnHere() {
        return this.isValidLightLevel() && super.getCanSpawnHere();
    }
}
