package creepypastacraft.common.entity.monster;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityMothman extends CPCEntity
{
    private ChunkCoordinates currentFlightTarget;

    public EntityMothman(World world) {
        super(world);
        super.isImmuneToFire = true;
        this.getNavigator().getAvoidsWater();
        super.tasks.addTask(0, new EntityAISwimming(this));
        super.tasks.addTask(1, new EntityAIBreakDoor(this));
        super.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.7D, false));
        super.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        super.tasks.addTask(3, new EntityAILookIdle(this));
        super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
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

    protected Entity findPlayerToAttack() {
        EntityPlayer entityplayer = super.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);
        if (entityplayer != null) {
            Minecraft mc = Minecraft.getMinecraft();
        }
        return null;
    }

    private boolean shouldAttackPlayer(EntityPlayer player) {
        if (player != null) {
            Vec3 vec3d = player.getLook(1.0F).normalize();
            Vec3 vec3d1 = Vec3.createVectorHelper(super.posX - player.posX, super.boundingBox.minY + (double) (super.height / 2.0F) - (player.posY + (double) player.getEyeHeight()), super.posZ - player.posZ);
            vec3d1 = vec3d1.normalize();
            vec3d.dotProduct(vec3d1);
            return player.canEntityBeSeen(this);
        } else {
            return false;
        }
    }

    public void onUpdate() {
        super.onUpdate();
        super.motionY *= 0.3000000238418579D;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (currentFlightTarget != null)
            if ((!super.worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || this.currentFlightTarget.posY < 1)) {
                EntityPlayer var1 = super.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);
                this.currentFlightTarget = null;
            }

        if (currentFlightTarget == null) {
            this.currentFlightTarget = new ChunkCoordinates((int) super.posX + super.rand.nextInt(7) - super.rand.nextInt(7), (int) super.posY + super.rand.nextInt(6) - 2, (int) super.posZ + super.rand.nextInt(7) - super.rand.nextInt(7));
        } else if (super.rand.nextInt(60) == 0 || this.currentFlightTarget.getDistanceSquared((int) super.posX, (int) super.posY, (int) super.posZ) < 4.0F) {
            this.currentFlightTarget = new ChunkCoordinates((int) super.posX + super.rand.nextInt(7) - super.rand.nextInt(7), (int) super.posY + super.rand.nextInt(6) - 2, (int) super.posZ + super.rand.nextInt(7) - super.rand.nextInt(7));
        }

        double var11 = (double) this.currentFlightTarget.posX + 0.5D - super.posX;
        double var3 = (double) this.currentFlightTarget.posY + 0.1D - super.posY;
        double var5 = (double) this.currentFlightTarget.posZ + 0.5D - super.posZ;
        super.motionX += (Math.signum(var11) * 0.5D - super.motionX) * 0.10000000149011612D;
        super.motionY += (Math.signum(var3) * 0.699999988079071D - super.motionY) * 0.10000000149011612D;
        super.motionZ += (Math.signum(var5) * 0.5D - super.motionZ) * 0.10000000149011612D;
        float var7 = (float) (Math.atan2(super.motionZ, super.motionX) * 180.0D / 3.141592653589793D) - 90.0F;
        float var8 = MathHelper.wrapAngleTo180_float(var7 - super.rotationYaw);
        super.moveForward = 0.5F;
        super.rotationYaw += var8;
    }
}
