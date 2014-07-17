package recraft.cpc.common.entity.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import recraft.cpc.init.CPCItems;

public class EntityStephano extends EntityThrowable {
	public EntityPlayer shootingEntity;
	private ItemStack potionDamage;

	public EntityStephano(World world) {
		super(world);
	}

	public EntityStephano(World world, EntityPlayer player, int damage) {
		this(world, player, new ItemStack(CPCItems.stephano, 1, damage));
	}

	public EntityStephano(World world, EntityPlayer player, ItemStack stack) {
		super(world, player);
		potionDamage = stack;
	}

	public EntityStephano(World world, double x, double y, double z, int damage) {
		this(world, x, y, z, new ItemStack(CPCItems.stephano, 1, damage));
	}

	public EntityStephano(World world, double x, double y, double z, ItemStack stack) {
		super(world, x, y, z);
		potionDamage = stack;
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

	public void setPotionDamage(int damage) {
		if(potionDamage == null) {
			potionDamage = new ItemStack(CPCItems.stephano, 1, 0);
		}

		potionDamage.setItemDamage(damage);
	}

	public int getPotionDamage() {
		if(potionDamage == null) {
			potionDamage = new ItemStack(CPCItems.stephano, 1, 0);
		}
		return potionDamage.getItemDamage();
	}

	@Override
	protected void onImpact(MovingObjectPosition objectPosition) {
		if(!worldObj.isRemote) {
			if(objectPosition.entityHit != null) {
				objectPosition.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, shootingEntity), 6);
				objectPosition.entityHit.setFire(6);
			}

			if(!worldObj.isRemote) {
				worldObj.createExplosion(this, posX, posY, posZ, 0.5F, true);
			}
			worldObj.playSoundAtEntity(this, "random.glass", 1.0F, 1.0F);
			setDead();
		}
	}

	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if(compound.hasKey("Potion")) {
			potionDamage = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Potion"));
		}
        else {
			setPotionDamage(compound.getInteger("potionValue"));
		}

		if(potionDamage == null) {
			setDead();
		}

	}

	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		if(potionDamage != null) {
			compound.setTag("Potion", potionDamage.writeToNBT(new NBTTagCompound()));
		}

	}

	public void onUpdate() {
		super.onUpdate();
	}
}
