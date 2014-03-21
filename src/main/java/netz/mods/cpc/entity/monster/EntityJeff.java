package netz.mods.cpc.entity.monster;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import netz.mods.cpc.client.gui.achievement.CPCAchievement;
import netz.mods.cpc.entity.passive.EntityCry;
import netz.mods.cpc.entity.passive.EntityJane;
import netz.mods.cpc.entity.passive.EntityPewds;
import netz.mods.cpc.item.CPCItem;

public class EntityJeff extends EntityMob implements IMob
{
	private boolean hasSaid;
	private boolean isSaying;
	private boolean Said;
	public boolean isAttacking;

	public EntityJeff(World par1World)
	{
		super(par1World);
		isImmuneToFire = true;
		getNavigator().setBreakDoors(true);
		experienceValue = 5;
		tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPewds.class, 0.7D, false));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCry.class, 0.7D, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPewds.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCry.class, 0, true));
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(5, new EntityAIPanic(this, 0.38F));
		tasks.addTask(2, new EntityAIOpenDoor(this, true));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.7D, false));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.7D, true));
		tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityJane.class, 0.7D, false));
		tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 0.7D));
		tasks.addTask(7, new EntityAIMoveThroughVillage(this, 0.7D, false));
		tasks.addTask(8, new EntityAIWander(this, 0.7D));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(9, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityJane.class, 0, true));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(32.0D);
	}

	public void addRandomArmor()
	{
		this.setCurrentItemOrArmor(0, new ItemStack(CPCItem.jeffKnife));
	}

	protected boolean isAIEnabled() {
		return true;
	}

	/*public void onLivingUpdate()
	{
		this.isAttacking = super.entityToAttack != null;
		if(!super.worldObj.isRemote)
		{
			;
		}

		EntityClientPlayerMP entityplayer = Minecraft.getMinecraft().thePlayer;
		if(entityplayer != null && entityplayer.isDead && !this.Said)
		{
			if(!super.worldObj.isRemote)
			{
				this.hasSaid = true;
			}

			if(this.hasSaid && !this.isSaying && !this.Said) {
				//Minecraft.getMinecraft().thePlayer.addChatMessage("\u00a7l"+StatCollector.translateToLocal("entity.jeff.name")+": \u00a7r"+StatCollector.translateToLocal("death.chat.jeff"));
				this.hasSaid = false;
				this.isSaying = true;
				this.Said = true;
			}

		}

		super.onLivingUpdate();

	}*/

	public void onKillEntity(EntityLivingBase par1EntityLivingBase)
	{
		super.onKillEntity(par1EntityLivingBase);

		if(!this.isSaying)
		{
			this.worldObj.playSoundAtEntity(this, "cpc:mob.jeff.kill", 1.0F, 1.0F);
			this.isSaying = true;
		}

		if(this.isSaying)
		{
			this.isSaying = false;
		}
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		if(par1DamageSource.getEntity() instanceof EntityJane)
		{
			EntityClientPlayerMP par1EntityPlayer = Minecraft.getMinecraft().thePlayer;
			this.dropItem(CPCItem.jeffKnife.itemID, 1);
			Minecraft.getMinecraft().thePlayer.addChatMessage("\u00a7lJane The Killer: \u00a7rSleep well.");
			par1EntityPlayer.addStat(CPCAchievement.noSleep, 1);
		}

	}

	protected int getDropItemId() {
		return 0;
	}
}
