package netz.mods.cpc.common.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import netz.mods.cpc.common.entity.passive.EntityCry;
import netz.mods.cpc.common.entity.passive.EntityPewds;

public class EntityJack extends CPEntity {

	public boolean isAttacking;
	public static int sleepDelay;
	public static boolean wasPlayerSleeping;
	private int field_35185_e;
	private int[] transparentBlocks = new int[]{8, 9, 10, 11, 18, 27, 28, 30, 31, 32, 37, 38, 39, 40, 44, 50, 51, 52, 59, 64, 65, 66, 67, 69, 70, 72, 75, 76, 77, 78, 83, 85, 90, 92, 106, 71, 107, 108, 109, 111, 113, 114, 114, 117};

	public EntityJack(World par1World) {
		super(par1World);
		this.attackStrength = 6;
		wasPlayerSleeping = false;
		super.isImmuneToFire = true;
		this.getNavigator().setBreakDoors(true);
		this.getNavigator().setEnterDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIBreakDoor(this));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3F, false));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.3F, true));
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 0.3F));
		this.tasks.addTask(7, new EntityAIMoveThroughVillage(this, 0.3F, false));
		this.tasks.addTask(8, new EntityAIWander(this, 0.3F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPewds.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCry.class, 0, true));
	}

	private boolean isBlockTransparent(int i) {
		for(int j = 0; j < this.transparentBlocks.length; ++j) {
			if(i == this.transparentBlocks[j]) {
				return true;
			}
		}

		return false;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean flag = super.attackEntityAsMob(par1Entity);

		if (flag && this.getHeldItem() == null && this.rand.nextFloat() < (float)this.worldObj.difficultySetting * 0.3F)
		{
			par1Entity.setFire(2 * this.worldObj.difficultySetting);
		}

		return flag;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(6.0D);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer entityplayer = super.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);
		if(entityplayer != null) {
			Minecraft mc = Minecraft.getMinecraft();
			if(this.shouldAttackPlayer(entityplayer) && mc.playerController.isNotCreative()) {
				if(this.field_35185_e++ == 5) {
					this.field_35185_e = 0;
					return entityplayer;
				}

				this.teleportToEntity(entityplayer);
			} else {
				this.field_35185_e = 0;
			}
		}

		return null;
	}

	private boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer) {
		if(par1EntityPlayer != null) {
			Vec3 vec3d = par1EntityPlayer.getLook(1.0F).normalize();
			Vec3 vec3d1 = Vec3.createVectorHelper(super.posX - par1EntityPlayer.posX, super.boundingBox.minY + (double)(super.height / 2.0F) - (par1EntityPlayer.posY + (double)par1EntityPlayer.getEyeHeight()), super.posZ - par1EntityPlayer.posZ);
			double d = vec3d1.lengthVector();
			vec3d1 = vec3d1.normalize();
			double d1 = vec3d.dotProduct(vec3d1);
			Minecraft mc = Minecraft.getMinecraft();
			return d1 > 1.0D - 0.025D / d && mc.playerController.isNotCreative()?par1EntityPlayer.canEntityBeSeen(this):false;
		} else {
			return false;
		}
	}

	public void onLivingUpdate() {
		if(sleepDelay != 0) {
			--sleepDelay;
			System.out.println(sleepDelay);
		}

		this.isAttacking = super.entityToAttack != null;
		EntityClientPlayerMP entityplayer = Minecraft.getMinecraft().thePlayer;
		if(!super.worldObj.isRemote && entityplayer != null && entityplayer.isPlayerSleeping()) {
			wasPlayerSleeping = true;
		}

		if(wasPlayerSleeping) {
			sleepDelay = 20;
			wasPlayerSleeping = false;
		}

		if(sleepDelay == 1 && !wasPlayerSleeping) {
			for(int times = 0; times <= 5; ++times) {
				this.teleportToEntity(entityplayer);
			}
		}

		if(super.entityToAttack != null) {
			this.faceEntity(super.entityToAttack, 100.0F, 100.0F);
		}

		if(!super.worldObj.isRemote && this.isEntityAlive() && super.entityToAttack != null && super.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer)super.entityToAttack)) {
			super.moveStrafing = super.moveForward = 0.0F;
		}

		super.onLivingUpdate();
	}

	protected boolean teleportToEntity(Entity par1Entity) {
		Vec3 var2 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX - par1Entity.posX, super.boundingBox.minY + (double)(super.height / 2.0F) - par1Entity.posY + (double)par1Entity.getEyeHeight(), super.posZ - par1Entity.posZ);
		var2 = var2.normalize();
		double var3 = 16.0D;
		double var5 = super.posX + (super.rand.nextDouble() - 0.5D) * 8.0D - var2.xCoord * var3;
		double var7 = super.posY + (double)(super.rand.nextInt(16) - 8) - var2.yCoord * var3;
		double var9 = super.posZ + (super.rand.nextDouble() - 0.5D) * 8.0D - var2.zCoord * var3;
		return this.teleportTo(var5, var7, var9);
	}

	protected boolean teleportTo(double par1, double par3, double par5) {
		double var7 = super.posX;
		double var9 = super.posY;
		double var11 = super.posZ;
		super.posX = par1;
		super.posY = par3;
		super.posZ = par5;
		boolean var13 = false;
		int var14 = MathHelper.floor_double(super.posX);
		int var15 = MathHelper.floor_double(super.posY);
		int var16 = MathHelper.floor_double(super.posZ);
		int var18;
		if(super.worldObj.blockExists(var14, var15, var16)) {
			boolean var30 = false;

			while(!var30 && var15 > 0) {
				var18 = super.worldObj.getBlockId(var14, var15 - 1, var16);
				if(var18 != 0 && Block.blocksList[var18].blockMaterial.blocksMovement()) {
					var30 = true;
				} else {
					--super.posY;
					--var15;
				}
			}

			if(var30) {
				this.setPosition(super.posX, super.posY, super.posZ);
				if(super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox)) {
					var13 = true;
				}
			}
		}

		if(!var13) {
			this.setPosition(var7, var9, var11);
			return false;
		} else {
			short var301 = 128;

			for(var18 = 0; var18 < var301; ++var18) {
				double var19 = (double)var18 / ((double)var301 - 1.0D);
				double var24 = var7 + (super.posX - var7) * var19 + (super.rand.nextDouble() - 0.5D) * (double)super.width * 2.0D;
				double var26 = var9 + (super.posY - var9) * var19 + super.rand.nextDouble() * (double)super.height;
				double var28 = var11 + (super.posZ - var11) * var19 + (super.rand.nextDouble() - 0.5D) * (double)super.width * 2.0D;
				super.worldObj.spawnParticle("portal", var24, var26, var28, 255.0D, 255.0D, 255.0D);
			}

			super.worldObj.playSoundEffect(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
			super.worldObj.playSoundAtEntity(this, "mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}

	public void onKillEntity(EntityLiving par1EntityLiving) {}

	protected int getDropItemId() {
		return 0;
	}
}
