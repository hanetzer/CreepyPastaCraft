package recraft.cpc.common.entity.monster;

import java.util.Random;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CPEntitySlendermanIdle extends CPEntity {

   private int field_35185_e;
   public boolean isAttacking;
   private boolean isAngry;
   public ItemStack itemstack;


   public CPEntitySlendermanIdle(World par1World) {
	  super(par1World);
	  super.texture = "/CreepyPastaCraft/textures/mobs/Slenderman.png";
	  this.setSize(1.0F, 3.0F);
	  this.attackStrength = 0;
	  super.moveSpeed = 0.4F;
	  super.stepHeight = 1.0F;
	  this.getNavigator().setAvoidsWater(true);
	  float var2 = 0.25F;
	  this.isAngry = false;
	  super.isImmuneToFire = true;
	  super.tasks.addTask(0, new EntityAISwimming(this));
	  super.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, super.moveSpeed, false));
	  super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	  super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
   }

   public boolean isAIEnabled() {
	  return this.isAngry;
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
	  if(par1DamageSource.getEntity() instanceof EntityPlayer) {
		 if(!super.worldObj.isRemote) {
			this.setDead();
			CPEntitySlendermanAngry var10 = new CPEntitySlendermanAngry(super.worldObj);
			var10.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
			var10.setEntityHealth(this.getHealth());
			var10.renderYawOffset = super.renderYawOffset;
			super.worldObj.spawnEntityInWorld(var10);
		 }

		 return false;
	  } else {
		 return super.attackEntityFrom(par1DamageSource, par2);
	  }
   }

   public String getTexture() {
	  EntityClientPlayerMP entityplayer = ModLoader.getMinecraftInstance().thePlayer;
	  return !this.isAngry && !this.shouldAttackPlayer(entityplayer)?"/CreepyPastaCraft/textures/mobs/Slenderman.png":"/CreepyPastaCraft/textures/mobs/Slenderman.png";
   }

   public int getMaxHealth() {
	  return 9001;
   }

   protected void entityInit() {
	  super.entityInit();
	  super.dataWatcher.addObject(16, Byte.valueOf((byte)0));
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
	  super.writeEntityToNBT(par1NBTTagCompound);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
	  super.readEntityFromNBT(par1NBTTagCompound);
   }

   protected boolean canDespawn() {
	  return false;
   }

   protected void fall(float par1) {
	  super.fall(par1);
   }

   protected Entity findPlayerToAttack() {
	  EntityPlayer entityplayer = super.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);
	  if(entityplayer != null) {
		 if(this.shouldAttackPlayer(entityplayer)) {
			if(this.field_35185_e++ == 5) {
			   this.field_35185_e = 0;
			   return entityplayer;
			}

			this.isAngry = true;
			Random rand = new Random();
			int i = rand.nextInt(4);
			if(i == 0) {
			   entityplayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 400, 50));
			}

			if(i == 1) {
			   entityplayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 250, 50));
			}

			if(i == 2) {
			   entityplayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400, 50));
			}

			if(i == 3) {
			   entityplayer.addPotionEffect(new PotionEffect(Potion.weakness.id, 250, 50));
			}

			if(!super.worldObj.isRemote) {
			   this.setDead();
			   CPEntitySlendermanAngry var10 = new CPEntitySlendermanAngry(super.worldObj);
			   var10.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
			   var10.setEntityHealth(this.getHealth());
			   var10.renderYawOffset = super.renderYawOffset;
			   super.worldObj.spawnEntityInWorld(var10);
			}
		 } else {
			this.field_35185_e = 0;
		 }
	  }

	  return null;
   }

   private boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer) {
	  ItemStack itemstack = par1EntityPlayer.inventory.armorInventory[3];
	  Vec3 vec3d = par1EntityPlayer.getLook(1.0F).normalize();
	  Vec3 vec3d1 = Vec3.createVectorHelper(super.posX - par1EntityPlayer.posX, super.boundingBox.minY + (double)(super.height / 2.0F) - (par1EntityPlayer.posY + (double)par1EntityPlayer.getEyeHeight()), super.posZ - par1EntityPlayer.posZ);
	  double d = vec3d1.lengthVector();
	  vec3d1 = vec3d1.normalize();
	  double d1 = vec3d.dotProduct(vec3d1);
	  if(d1 > 1.0D - 0.025D / d) {
		 this.isAngry = true;
		 return par1EntityPlayer.canEntityBeSeen(this);
	  } else {
		 return false;
	  }
   }

   public void onDeath(DamageSource par1DamageSource) {
	  super.onDeath(par1DamageSource);
	  if(!super.worldObj.isRemote) {
		 ModLoader.getMinecraftInstance().thePlayer.addChatMessage("\u00a7lSlendy: \u00a7rYou just won the game...");
	  }

   }

   public void onLivingUpdate(ItemStack par1ItemStack, double par4, double par5, int var12, double par6) {
	  EntityClientPlayerMP entityplayer = ModLoader.getMinecraftInstance().thePlayer;
	  super.motionX = 0.0D;
	  super.motionZ = 0.0D;
	  this.isAttacking = super.entityToAttack != null;
	  super.moveSpeed = super.entityToAttack == null?0.3F:6.5F;
	  if(!super.worldObj.isRemote) {
		 ;
	  }

	  if(this.shouldAttackPlayer(entityplayer)) {
		 WorldClient f = ModLoader.getMinecraftInstance().theWorld;
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
