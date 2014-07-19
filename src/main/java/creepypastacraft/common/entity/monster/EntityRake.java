package creepypastacraft.common.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import creepypastacraft.common.entity.passive.EntityCry;
import creepypastacraft.common.entity.passive.EntityPewds;

public class EntityRake extends EntityMob implements IMob {
    public EntityRake(World world) {
        super(world);
        setSize(0.7F, 0.9F);
        getNavigator().setAvoidsWater(true);
        getNavigator().setBreakDoors(true);
        experienceValue = 5;
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPewds.class, 0.8D, false));
        tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.8D, false));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCry.class, 0.8D, false));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.8D, true));
        tasks.addTask(3, new EntityAIWander(this, 1.0D));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(5, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPewds.class, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCry.class, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(48.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    protected Item getDropItem() {
        return isBurning() ? Items.cooked_porkchop : Items.porkchop;
    }

    protected void dropFewItems(boolean par1, int par2) {
        int var3 = super.rand.nextInt(3) + 1 + super.rand.nextInt(1 + par2);

        for (int var4 = 0; var4 < var3; ++var4) {
            if (isBurning()) {
                dropItem(Items.cooked_porkchop, 1);
            } else {
                dropItem(Items.porkchop, 1);
            }
        }
    }
}
