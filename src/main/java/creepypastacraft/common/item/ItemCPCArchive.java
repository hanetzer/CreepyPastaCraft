package creepypastacraft.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import creepypastacraft.CPC;
import creepypastacraft.api.registry.PastaRegistry;

import java.util.List;

@SuppressWarnings("unchecked")
public class ItemCPCArchive extends Item {

    public ItemCPCArchive() {
        this.setHasSubtypes(true);
        this.setCreativeTab(CPC.tabCPC);
        this.setMaxStackSize(1);
    }

    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {
        String s = PastaRegistry.getStringFromID(itemStack.getItemDamage());
        list.add(I18n.format("entity." + s + ".name"));
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par3World.isRemote) {
            return true;
        } else {
            Block block = par3World.getBlock(par4, par5, par6);
            par4 += Facing.offsetsXForSide[par7];
            par5 += Facing.offsetsYForSide[par7];
            par6 += Facing.offsetsZForSide[par7];
            double d0 = 0.0D;

            if (par7 == 1 && block.getRenderType() == 11) {
                d0 = 0.5D;
            }

            Entity entity = spawnCreature(par3World, par1ItemStack.getItemDamage(), (double) par4 + 0.5D, (double) par5 + d0, (double) par6 + 0.5D);

            if (entity != null) {
                if (entity instanceof EntityLivingBase && par1ItemStack.hasDisplayName()) {
                    ((EntityLiving) entity).setCustomNameTag(par1ItemStack.getDisplayName());
                }

                if (!par2EntityPlayer.capabilities.isCreativeMode) {
                    --par1ItemStack.stackSize;
                }
            }
            return true;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote) {
            return par1ItemStack;
        } else {
            MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

            if (movingobjectposition == null) {
                return par1ItemStack;
            } else {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    int i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;

                    if (!par2World.canMineBlock(par3EntityPlayer, i, j, k)) {
                        return par1ItemStack;
                    }

                    if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack)) {
                        return par1ItemStack;
                    }

                    if (par2World.getBlock(i, j, k) instanceof BlockLiquid) {
                        Entity entity = spawnCreature(par2World, par1ItemStack.getItemDamage(), (double) i, (double) j, (double) k);

                        if (entity != null) {
                            if (entity instanceof EntityLivingBase && par1ItemStack.hasDisplayName()) {
                                ((EntityLiving) entity).setCustomNameTag(par1ItemStack.getDisplayName());
                            }

                            if (!par3EntityPlayer.capabilities.isCreativeMode) {
                                --par1ItemStack.stackSize;
                            }
                        }
                    }
                }

                return par1ItemStack;
            }
        }
    }

    /**
     * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
     * Parameters: world, par2EntityID, x, y, z.
     */
    public static Entity spawnCreature(World par1World, int par2EntityID, double par3x, double par4y, double par5z) {
        if (!PastaRegistry.pastaList.containsKey(par2EntityID)) {
            return null;
        } else {
            Entity entity = null;

            for (int j = 0; j < 1; ++j) {
                entity = PastaRegistry.createEntityByID(par2EntityID, par1World);

                if (entity != null && entity instanceof EntityLivingBase) {
                    EntityLiving entityliving = (EntityLiving) entity;
                    entity.setLocationAndAngles(par3x, par4y, par5z, MathHelper.wrapAngleTo180_float(par1World.rand.nextFloat() * 360.0F), 0.0F);
                    entityliving.rotationYawHead = entityliving.rotationYaw;
                    entityliving.renderYawOffset = entityliving.rotationYaw;
                    entityliving.onSpawnWithEgg(null);
                    par1World.spawnEntityInWorld(entity);
                    entityliving.playLivingSound();
                }
            }
            return entity;
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1Item, CreativeTabs par2CreativeTabs, List par3List) {

        for (Object o : PastaRegistry.pastaList.values()) {
            PastaRegistry.PastaInfo pastaInfo = (PastaRegistry.PastaInfo) o;
            par3List.add(new ItemStack(par1Item, 1, pastaInfo.spawnedID));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("cpc:archive");
    }
}
