package recraft.cpc.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityCry extends EntityAnimal {
	public EntityCry(World world) 	{
		super(world);
		setSize(1.0F,0.5F);
		isImmuneToFire = true;
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 1.0D));
		tasks.addTask(2, new EntityAIOpenDoor(this, true));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5D));
		tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.5D, false));
		tasks.addTask(6, new EntityAIWander(this, 0.6D));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, EntityMob.class, 8.0F, 0.6D, 0.6D));
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	@Override
	public boolean getCanSpawnHere() {
        if (worldObj.villageCollectionObj.getVillageList().iterator().hasNext()) {
            if (worldObj.villageCollectionObj.findNearestVillage((int) posX, (int) posY, (int) posZ, 10) == null) {
                return false;
            }
        }
		return true;
	}

	protected boolean canDespawn() {
		return true;
	}

	public void onKillEntity(EntityLiving entity) {}

	protected Item getDropItem() {
		return Items.ghast_tear;
	}

	public EntityAgeable createChild(EntityAgeable entity) {
		return entity;
	}
}
