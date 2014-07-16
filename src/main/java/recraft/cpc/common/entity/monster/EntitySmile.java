package recraft.cpc.common.entity.monster;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
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

public class EntitySmile extends CPEntity {

	public static ResourceLocation resourceLocation;
	private int field_35185_e;
	public boolean isAttacking;
	private boolean isAngry;
	public ItemStack itemstack;


	public EntitySmile(World par1World)
	{
		super(par1World);
		this.setSize(0.6F, 0.8F);
		this.stepHeight = 1.0F;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(7.0F);
	}

	public boolean isAIEnabled() {
		return this.isAngry;
	}

	/**
	 * Determines whether this wolf is angry or not.
	 */
	public boolean isAngry()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
	}

	/**
	 * Sets whether this wolf is angry or not.
	 */
	public void setAngry(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			this.dataWatcher.updateObject(16, (byte) (b0 | 2));
		}
		else
		{
			this.dataWatcher.updateObject(16, (byte) (b0 & -3));
		}
	}

	protected void entityInit() {
		super.entityInit();
		super.dataWatcher.addObject(16, (byte) 0);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Angry", this.isAngry());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
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
		EntityPlayer entityplayer = super.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);
		if(entityplayer != null) {
			Minecraft mc = Minecraft.getMinecraft();
			if(this.shouldAttackPlayer(entityplayer) && mc.playerController.isNotCreative()) {
				if(this.field_35185_e++ == 5) {
					this.field_35185_e = 0;
					return entityplayer;
				}

				mc.thePlayer.addChatMessage(new ChatComponentText("Spread The Word."));
				this.isAngry = true;
				entityplayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 700, 50));
				entityplayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 250, 50));
				entityplayer.addPotionEffect(new PotionEffect(Potion.harm.id, 1, 1));
				entityplayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80, 2));
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
			if(d1 > 1.0D - 0.025D / d && mc.playerController.isNotCreative()) {
				this.isAngry = true;
				return par1EntityPlayer.canEntityBeSeen(this);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void onLivingUpdate(ItemStack par1ItemStack, double par4, double par5, int var12, double par6) {
		EntityClientPlayerMP entityplayer = Minecraft.getMinecraft().thePlayer;
		super.motionX = 0.0D;
		super.motionZ = 0.0D;
		this.isAttacking = super.entityToAttack != null;
		super.moveSpeed = super.entityToAttack == null?0.3F:6.5F;
		if(!super.worldObj.isRemote) {
			;
		}

		if(this.shouldAttackPlayer(entityplayer)) {
			WorldClient f = Minecraft.getMinecraft().theWorld;
		}

		if(super.worldObj.isDaytime() && !super.worldObj.isRemote) {
			float f1 = this.getBrightness(1.0F);
			if(f1 > 0.5F && super.worldObj.canBlockSeeTheSky(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)) && super.rand.nextFloat() * 30.0F < (f1 - 0.4F) * 2.0F) {
				super.entityToAttack = null;
			}
		}

		super.isJumping = false;
		if(super.entityToAttack != null) {
			this.faceEntity(super.entityToAttack, 100.0F, 100.0F);
		}

		if(!super.worldObj.isRemote && this.isEntityAlive()) {
			if(super.entityToAttack != null && super.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer)super.entityToAttack)) {
				super.moveStrafing = super.moveForward = 0.0F;
				super.moveSpeed = 0.0F;
			}

			super.onLivingUpdate();
		}

	}
}
