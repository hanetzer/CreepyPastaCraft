package recraft.cpc.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import recraft.cpc.CPC;
import recraft.cpc.common.registry.PastaRegistry;

import java.util.Iterator;
import java.util.List;

public class ItemCPCArchive extends Item {
	public String CPName;
	public ItemCPCArchive() {
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setCreativeTab(CPC.tabCPC);
		this.setTextureName("cpc:archive");
	}

	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	public boolean isFull3D() {
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		list.add(StatCollector.translateToLocal("entity.cpc:"+this.CPName+".name"));
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if (par3World.isRemote)
		{
			return true;
		}
		else
		{
			Block block = par3World.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
			p_77648_4_ += Facing.offsetsXForSide[p_77648_7_];
			p_77648_5_ += Facing.offsetsYForSide[p_77648_7_];
			p_77648_6_ += Facing.offsetsZForSide[p_77648_7_];
			double d0 = 0.0D;

			if (p_77648_7_ == 1 && block.getRenderType() == 11)
			{
				d0 = 0.5D;
			}

			Entity entity = spawnCreature(par3World, par1ItemStack.getItemDamage(), (double) p_77648_4_ + 0.5D, (double) p_77648_5_ + d0, (double) p_77648_6_ + 0.5D);

			if (entity != null)
			{
				if (entity instanceof EntityLivingBase && par1ItemStack.hasDisplayName())
				{
					((EntityLiving)entity).setCustomNameTag(par1ItemStack.getDisplayName());
				}

				if (!par2EntityPlayer.capabilities.isCreativeMode)
				{
					--par1ItemStack.stackSize;
				}
			}

			return true;
		}
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote)
		{
			return par1ItemStack;
		}
		else
		{
			MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

			if (movingobjectposition == null)
			{
				return par1ItemStack;
			}
			else
			{
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
				{
					int i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;

					if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
					{
						return par1ItemStack;
					}

					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
					{
						return par1ItemStack;
					}

					if (par2World.getBlock(i, j, k) instanceof BlockLiquid)
					{
						Entity entity = spawnCreature(par2World, par1ItemStack.getItemDamage(), (double) i, (double) j, (double) k);

						if (entity != null)
						{
							if (entity instanceof EntityLivingBase && par1ItemStack.hasDisplayName())
							{
								((EntityLiving)entity).setCustomNameTag(par1ItemStack.getDisplayName());
							}

							if (!par3EntityPlayer.capabilities.isCreativeMode)
							{
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
	 * Parameters: world, entityID, x, y, z.
	 */
	public static Entity spawnCreature(World par1World, int par2PastaID, double par3x, double par4y, double par5z) {
		if (!PastaRegistry.pastaList.containsKey(par2PastaID))
		{
			return null;
		}
		else
		{
			Entity entity = null;

			for (int j = 0; j < 1; ++j)
			{
				entity = PastaRegistry.createEntityByID(par2PastaID, par1World);

				if (entity != null && entity instanceof EntityLivingBase)
				{
					EntityLiving entityliving = (EntityLiving)entity;
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
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1Item, CreativeTabs par2CreativeTab, List par3List)
	{
		Iterator iterator = PastaRegistry.pastaList.values().iterator();

		while (iterator.hasNext())
		{
			PastaRegistry.PastaInfo pastaInfo = (PastaRegistry.PastaInfo)iterator.next();
			par3List.add(new ItemStack(par1Item, 1, pastaInfo.spawnedID));
		}
	}
}
