package netz.mods.cpc.common.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import netz.mods.cpc.common.entity.monster.EntityJeff;

public class EntityJane extends EntityMob implements IMob {

	public boolean isAttacking;


	public EntityJane(World par1World) {
		super(par1World);
		super.isImmuneToFire = true;
		float var2 = 0.25F;
		this.getNavigator().setBreakDoors(true);
		super.experienceValue = 5;
		super.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPewds.class, 0.7D, false));
		super.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCry.class, 0.7D, false));
		super.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPewds.class, 0, true));
		super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCry.class, 0, true));
		super.tasks.addTask(0, new EntityAISwimming(this));
		super.tasks.addTask(5, new EntityAIPanic(this, 0.38F));
		super.tasks.addTask(2, new EntityAIBreakDoor(this));
		super.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityJeff.class, 0.7D, false));
		super.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.7D));
		super.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.7F, false));
		super.tasks.addTask(6, new EntityAIWander(this, var2));
		super.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		super.tasks.addTask(8, new EntityAILookIdle(this));
		super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,EntityJeff.class, 0, true));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(32.0D);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public void onKillEntity(EntityLiving par1EntityLiving) {}

	protected int getDropItemId() {
		return Block.plantRed.blockID;
	}
}
