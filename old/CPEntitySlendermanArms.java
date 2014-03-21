package netz.mods.cpc.entity.monster;

import java.util.Random;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
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

public class CPEntitySlendermanArms extends CPEntity {

   private int field_35185_e;
   private boolean isAngry = true;
   public boolean isAttacking;
   public ItemStack itemstack;


   public CPEntitySlendermanArms(World par1World) {
      super(par1World);
      super.texture = "/CreepyPastaCraft/textures/mobs/SlendermanSwing.png";
      this.setSize(1.0F, 3.0F);
      this.attackStrength = 4;
      super.moveSpeed = 0.5F;
      super.stepHeight = 1.0F;
      this.getNavigator().setAvoidsWater(true);
      float var2 = 0.25F;
      this.isAngry = true;
      super.tasks.addTask(0, new EntityAISwimming(this));
      super.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, super.moveSpeed, false));
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected Entity findPlayerToAttack() {
      EntityPlayer entityplayer = super.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);
      if(entityplayer != null) {
         if(this.shouldAttackPlayer(entityplayer)) {
            if(this.field_35185_e++ == 5) {
               this.field_35185_e = 0;
               return entityplayer;
            }

            Random rand = new Random();
            int i = rand.nextInt(5);
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

            if(i == 4) {
               entityplayer.addPotionEffect(new PotionEffect(Potion.poison.id, 250, 50));
            }
         } else {
            this.field_35185_e = 0;
         }
      }

      return null;
   }

   private boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer) {
      if(par1EntityPlayer != null) {
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
      } else {
         return false;
      }
   }

   public int getMaxHealth() {
      return CPEntitySlendermanAngry.healthH;
   }

   public void onDeath(DamageSource par1DamageSource) {
      super.onDeath(par1DamageSource);
      if(!super.worldObj.isRemote) {
         ModLoader.getMinecraftInstance().thePlayer.addChatMessage("\u00a7lSlendy: \u00a7rYou just won the game...");
      }

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

   public void onLivingUpdate() {
      super.onLivingUpdate();
      Random rand = new Random();
      int i = rand.nextInt(25);
      if(i < 2 && !super.worldObj.isRemote) {
         this.setDead();
         CPEntitySlendermanAngry entityplayer = new CPEntitySlendermanAngry(super.worldObj);
         entityplayer.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
         entityplayer.setEntityHealth(this.getHealth());
         entityplayer.renderYawOffset = super.renderYawOffset;
         super.worldObj.spawnEntityInWorld(entityplayer);
      }

      EntityClientPlayerMP entityplayer1 = ModLoader.getMinecraftInstance().thePlayer;
      super.motionX = 0.0D;
      super.motionZ = 0.0D;
      this.isAttacking = super.entityToAttack != null;
      super.moveSpeed = super.entityToAttack == null?0.3F:6.5F;
      if(!super.worldObj.isRemote) {
         ;
      }

      if(this.shouldAttackPlayer(entityplayer1)) {
         WorldClient f = ModLoader.getMinecraftInstance().theWorld;
      }

      if(super.worldObj.isDaytime() && !super.worldObj.isRemote) {
         float f1 = this.getBrightness(1.0F);
         if(f1 > 0.5F && super.worldObj.canBlockSeeTheSky(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)) && rand.nextFloat() * 30.0F < (f1 - 0.4F) * 2.0F) {
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
