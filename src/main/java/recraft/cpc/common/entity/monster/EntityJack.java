package recraft.cpc.common.entity.monster;

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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import recraft.cpc.common.entity.passive.EntityCry;
import recraft.cpc.common.entity.passive.EntityPewds;

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

		if (flag && this.getHeldItem() == null && this.rand.nextFloat() < (float)this.worldObj.difficultySetting.getDifficultyId() * 0.3F)
		{
			par1Entity.setFire(2 * this.worldObj.difficultySetting.getDifficultyId());
		}

		return flag;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
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

	protected boolean teleportToEntity(Entity par1Entity)
	{
		Vec3 vec3 = Vec3.createVectorHelper(this.posX - par1Entity.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - par1Entity.posY + (double)par1Entity.getEyeHeight(), this.posZ - par1Entity.posZ);
		vec3 = vec3.normalize();
		double d0 = 16.0D;
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
		double d2 = this.posY + (double)(this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
		double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
		return this.teleportTo(d1, d2, d3);
	}

	protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_)
	{
		EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0);
		if (MinecraftForge.EVENT_BUS.post(event)){
			return false;
		}
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = event.targetX;
		this.posY = event.targetY;
		this.posZ = event.targetZ;
		boolean flag = false;
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		if (this.worldObj.blockExists(i, j, k))
		{
			boolean flag1 = false;

			while (!flag1 && j > 0)
			{
				Block block = this.worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial().blocksMovement())
				{
					flag1 = true;
				}
				else
				{
					--this.posY;
					--j;
				}
			}

			if (flag1)
			{
				this.setPosition(this.posX, this.posY, this.posZ);

				if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
				{
					flag = true;
				}
			}
		}

		if (!flag)
		{
			this.setPosition(d3, d4, d5);
			return false;
		}
		else
		{
			short short1 = 128;

			for (int l = 0; l < short1; ++l)
			{
				double d6 = (double)l / ((double)short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
				double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
				double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
				this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
			}

			this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
			this.playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}

	public void onKillEntity(EntityLiving par1EntityLiving) {}

	protected int getDropItemId() {
		return 0;
	}
}
