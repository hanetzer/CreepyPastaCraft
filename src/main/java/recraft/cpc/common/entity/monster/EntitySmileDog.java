package recraft.cpc.common.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySmileDog extends EntityMob {

    public EntitySmileDog(World world) {
        super(world);
        setSize(0.6F, 0.8F);
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
    }

    public boolean isAIEnabled() {
        return isTransformed();
    }

    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(16, (byte) 0);
    }

    public boolean isTransformed() {
        return dataWatcher.getWatchableObjectByte(16) > 0;
    }

    public void setTransformed(boolean bool) {
        dataWatcher.updateObject(16, (byte) (bool ? 1 : 0));
    }


    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Transformed", isTransformed());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        setTransformed(compound.getBoolean("Transformed"));
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

    private void speak(EntityPlayer player) {
        player.addChatMessage(new ChatComponentText(
                StatCollector.translateToLocal("entity.cpc:smile.say")
        ));
    }

    private void afflictPlayer(EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.blindness.id, 700, 50, true));
        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 250, 50, true));
        player.addPotionEffect(new PotionEffect(Potion.harm.id, 1, 1, true));
        player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80, 2, true));
    }

    protected Entity findPlayerToAttack() {
        EntityPlayer player = worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);

        if (player != null) {
            if (shouldAttackPlayer(player)) {
                setTransformed(true);
                afflictPlayer(player);
                speak(player);
                return player;
            }
            else {
                setTransformed(false);
            }
        }
        return null;
    }

    private boolean shouldAttackPlayer(EntityPlayer player) {
        Vec3 vec3 = player.getLook(1.0F).normalize();
        Vec3 vec31 = Vec3.createVectorHelper(posX - player.posX, boundingBox.minY + (double) (height / 2.0F) - (player.posY + (double) player.getEyeHeight()), posZ - player.posZ);
        double d0 = vec31.lengthVector();
        vec31 = vec31.normalize();
        double d1 = vec3.dotProduct(vec31);
        setTransformed(d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(this));
        return isTransformed();
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.entityToAttack != null) {
            setTransformed(true);
            this.faceEntity(this.entityToAttack, 100.0F, 100.0F);
        }
    }
}
