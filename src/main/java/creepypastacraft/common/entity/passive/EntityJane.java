package creepypastacraft.common.entity.passive;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import creepypastacraft.common.entity.monster.EntityJeff;

public class EntityJane extends EntityMob implements IMob {
    public EntityJane(World world) {
        super(world);
        setSize(0.6F, 1.8F);
        isImmuneToFire = true;
        getNavigator().setBreakDoors(true);
        experienceValue = 5;
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPewds.class, 0.7D, false));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCry.class, 0.7D, false));
        tasks.addTask(2, new EntityAIBreakDoor(this));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityJeff.class, 0.7D, false));
        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.7D));
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.7F, false));
        tasks.addTask(5, new EntityAIPanic(this, 0.38F));
        tasks.addTask(6, new EntityAIWander(this, 1.0D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityJeff.class, 0, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPewds.class, 0, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityCry.class, 0, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    public void onKillEntity(EntityLiving entity) {
    }

    protected Item getDropItem() {
        return Item.getItemFromBlock(Blocks.red_flower);
    }
}
