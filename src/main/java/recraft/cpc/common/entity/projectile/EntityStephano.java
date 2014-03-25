package recraft.cpc.common.entity.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import recraft.cpc.common.item.CPCItem;

public class EntityStephano extends EntityThrowable {

	public EntityPlayer shootingEntity;
	private ItemStack potionDamage;


	public EntityStephano(World par1World) {
		super(par1World);
	}

	public EntityStephano(World par1World, EntityPlayer par2EntityLiving, int par3) {
		this(par1World, par2EntityLiving, new ItemStack(CPCItem.stephano, 1, par3));
	}

	public EntityStephano(World par1World, EntityPlayer par2EntityLiving, ItemStack par3ItemStack) {
		super(par1World, par2EntityLiving);
		this.potionDamage = par3ItemStack;
	}

	public EntityStephano(World par1World, double par2, double par4, double par6, int par8) {
		this(par1World, par2, par4, par6, new ItemStack(CPCItem.stephano, 1, par8));
	}

	public EntityStephano(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
		super(par1World, par2, par4, par6);
		this.potionDamage = par8ItemStack;
	}

	protected float getGravityVelocity() {
		return 0.03F;
	}

	protected float func_70182_d() {
		return 0.5F;
	}

	protected float func_70183_g() {
		return -20.0F;
	}

	public void setPotionDamage(int par1) {
		if(this.potionDamage == null) {
			this.potionDamage = new ItemStack(CPCItem.stephano, 1, 0);
		}

		this.potionDamage.setItemDamage(par1);
	}

	public int getPotionDamage() {
		if(this.potionDamage == null) {
			this.potionDamage = new ItemStack(CPCItem.stephano, 1, 0);
		}
		return this.potionDamage.getItemDamage();
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		if(!this.worldObj.isRemote) {
			if(par1MovingObjectPosition.entityHit != null) {
				par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.shootingEntity), 6);
				par1MovingObjectPosition.entityHit.setFire(6);
			}

			if(!this.worldObj.isRemote) {
				this.worldObj.createExplosion(this, posX, posY, posZ, 0.5F, true);
			}

			this.worldObj.playSoundAtEntity(this, "random.glass", 1.0F, 1.0F);
			this.setDead();
		}

	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		if(par1NBTTagCompound.hasKey("Potion")) {
			this.potionDamage = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("Potion"));
		} else {
			this.setPotionDamage(par1NBTTagCompound.getInteger("potionValue"));
		}

		if(this.potionDamage == null) {
			this.setDead();
		}

	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		if(this.potionDamage != null) {
			par1NBTTagCompound.setCompoundTag("Potion", this.potionDamage.writeToNBT(new NBTTagCompound()));
		}

	}

	public void onUpdate() {
		super.onUpdate();
	}
}

