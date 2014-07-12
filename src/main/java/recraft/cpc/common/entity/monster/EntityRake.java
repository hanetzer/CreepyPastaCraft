package recraft.cpc.common.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import recraft.cpc.common.entity.passive.EntityCry;
import recraft.cpc.common.entity.passive.EntityPewds;

public class EntityRake extends EntityMob implements IMob
{
	public EntityRake(World par1World)
	{
		super(par1World);
		this.setSize(0.7F, 0.9F);
		this.getNavigator().setAvoidsWater(true);
		float var2 = 0.25F;
		this.getNavigator().setBreakDoors(true);
		super.experienceValue = 5;
		super.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPewds.class, 0.7D, false));
		super.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCry.class, 0.7D, false));
		super.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPewds.class, 0, true));
		super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCry.class, 0, true));
		super.tasks.addTask(0, new EntityAISwimming(this));
		super.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.7F, false));
		super.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.7F, true));
		super.tasks.addTask(3, new EntityAIWander(this, var2));
		super.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		super.tasks.addTask(5, new EntityAILookIdle(this));
		super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(48.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(7.0F);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	protected Item getDropItem() {
		return this.isBurning() ? Items.cooked_porkchop : Items.porkchop;
	}

	protected void dropFewItems(boolean par1, int par2) {
		int var3 = super.rand.nextInt(3) + 1 + super.rand.nextInt(1 + par2);

		for(int var4 = 0; var4 < var3; ++var4) {
			if(this.isBurning()) {
				this.dropItem(Items.cooked_porkchop, 1);
			} else {
				this.dropItem(Items.porkchop, 1);
			}
		}

	}
}
