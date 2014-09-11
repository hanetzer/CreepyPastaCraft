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

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@SuppressWarnings("unchecked")
public class ItemCPCArchive extends Item {

    public ItemCPCArchive() {
        this.setHasSubtypes(true);
        this.setCreativeTab(CPC.tabCPC);
        this.setMaxStackSize(1);
		this.setMaxDurability(0);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        String s = PastaRegistry.pastaList.get(stack.getCurrentDurability()).name;
        list.add(I18n.format("entity." + s + ".name"));
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
							 int x, int y, int z, int dir,
							 float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            Block block = world.getBlock(x, y, z);
            x += Facing.offsetsXForSide[dir];
            y += Facing.offsetsYForSide[dir];
            z += Facing.offsetsZForSide[dir];
            double d0 = 0.0D;

            if (dir == 1 && block.getRenderType() == 11) {
                d0 = 0.5D;
            }

            Entity entity = spawnCreature(world, stack.getCurrentDurability(), (double) x + 0.5D,
					(double) y + d0, (double) z + 0.5D);

            if (entity != null) {
                if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
                    ((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
                }

                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
            }
            return true;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) {
            return stack;
        } else {
            MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

            if (movingobjectposition == null) {
                return stack;
            } else {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    int x = movingobjectposition.blockX;
                    int y = movingobjectposition.blockY;
                    int z = movingobjectposition.blockZ;

                    if (!world.canMineBlock(player, x, y, z)) {
                        return stack;
                    }

                    if (!player.canPlayerEdit(x, y, z, movingobjectposition.sideHit, stack)) {
                        return stack;
                    }

                    if (world.getBlock(x, y, z) instanceof BlockLiquid) {
                        Entity entity = spawnCreature(world,
								stack.getCurrentDurability(),
								(double) x, (double) y, (double) z);

                        if (entity != null) {
                            if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
                                ((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
                            }

                            if (!player.capabilities.isCreativeMode) {
                                --stack.stackSize;
                            }
                        }
                    }
                }

                return stack;
            }
        }
    }

    /**
     * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
     * Parameters: world, par2EntityID, x, y, z.
     */
    public static Entity spawnCreature(World w, int ID, double x, double y, double z) {
        if (PastaRegistry.pastaList.get(ID) == null) {
            return null;
        } else {
            Entity entity = null;

            for (int j = 0; j < 1; ++j) {
				try
				{
					entity = (Entity)PastaRegistry.pastaList.get(ID).pasta.getConstructor(new Class[] {World.class}).newInstance(w);
				} catch (Exception e)
				{
					e.printStackTrace();
				}

				if (entity != null && entity instanceof EntityLivingBase) {
                    EntityLiving entityliving = (EntityLiving) entity;
                    entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(w.rand.nextFloat() * 360.0F), 0.0F);
                    entityliving.rotationYawHead = entityliving.rotationYaw;
                    entityliving.renderYawOffset = entityliving.rotationYaw;
                    entityliving.onSpawnWithEgg(null);
                    w.spawnEntityInWorld(entity);
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
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
		for (int i = 0; i < PastaRegistry.pastaList.size(); ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("creepypastacraft:archive");
    }
}
