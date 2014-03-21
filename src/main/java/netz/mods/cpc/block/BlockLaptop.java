package netz.mods.cpc.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import netz.mods.cpc.CPC;
import netz.mods.cpc.inventory.InventoryLaptop;
import netz.mods.cpc.tileentity.TileEntityLaptop;

public class BlockLaptop extends BlockContainer {
	public BlockLaptop(int par1) {
		super(par1, Material.iron);
		setCreativeTab(CPC.tabCPC);
		setTextureName("cpc:laptop");
		setUnlocalizedName("computer");
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return CPCBlock.laptop.blockID;
	}

	public int quantityDropped(Random random) {
		return 1;
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public void breakBlock(World var1, int var2, int var3, int var4, int par5EntityPlayer, int par6i) {
		var1.removeBlockTileEntity(var2, var3, var4);
		super.breakBlock(var1, var2, var3, var4, par5EntityPlayer, par6i);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
		if (world.blockHasTileEntity(x, y, z) && world.isRemote) {
			TileEntityLaptop tileentitylaptop = (TileEntityLaptop) world.getBlockTileEntity(x, y, z);
			InventoryLaptop inventoryLaptop = new InventoryLaptop();
			inventoryLaptop.setAssociated(tileentitylaptop);
			entityPlayer.openGui(CPC.instance, 0, world, x, y, z);
			return true;
		}
		return false;
	}

	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityLaptop();
	}

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		byte b0 = 0;
		int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			b0 = 2;
		}

		if (l == 1) {
			b0 = 5;
		}

		if (l == 2) {
			b0 = 3;
		}

		if (l == 3) {
			b0 = 4;
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
	}
}

