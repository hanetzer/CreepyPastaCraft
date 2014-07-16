package recraft.cpc.common.entity.monster;

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
import recraft.cpc.common.entity.passive.EntityCry;
import recraft.cpc.common.entity.passive.EntityPewds;

public abstract class CPEntity extends EntityMob implements IMob {

	protected int attackStrength = 2;
	float moveSpeed; 

	public CPEntity(World par1World) {
		super(par1World);
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
		EntityPlayer var1 = super.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return var1 != null && this.canEntityBeSeen(var1)?var1:null;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if(super.attackEntityFrom(par1DamageSource, par2)) {
			Entity var3 = par1DamageSource.getEntity();
			if(super.riddenByEntity != var3 && super.ridingEntity != var3) {
				if(var3 != this) {
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

	public boolean attackEntityAsMob(Entity par1Entity) {
		int var2 = this.attackStrength;
		if(this.isPotionActive(Potion.damageBoost)) {
			var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
		}

		if(this.isPotionActive(Potion.weakness)) {
			var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
		}

		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
	}

	protected void attackEntity(Entity par1Entity, float par2) {
		if(super.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > super.boundingBox.minY && par1Entity.boundingBox.minY < super.boundingBox.maxY) {
			super.attackTime = 20;
			this.attackEntityAsMob(par1Entity);
		}

	}

	public float getBlockPathWeight(int par1, int par2, int par3) {
		return 0.5F - super.worldObj.getLightBrightness(par1, par2, par3);
	}

	protected boolean isValidLightLevel() {
		int var1 = MathHelper.floor_double(super.posX);
		int var2 = MathHelper.floor_double(super.boundingBox.minY);
		int var3 = MathHelper.floor_double(super.posZ);
		if(super.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > super.rand.nextInt(32)) {
			return false;
		} else {
			int var4 = super.worldObj.getBlockLightValue(var1, var2, var3);
			if(super.worldObj.isThundering()) {
				int var5 = super.worldObj.skylightSubtracted;
				super.worldObj.skylightSubtracted = 10;
				var4 = super.worldObj.getBlockLightValue(var1, var2, var3);
				super.worldObj.skylightSubtracted = var5;
			}
			return var4 <= super.rand.nextInt(8);
		}
	}

	public boolean getCanSpawnHere() {
		return this.isValidLightLevel() && super.getCanSpawnHere();
	}
}
