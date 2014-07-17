package recraft.cpc.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import recraft.cpc.CPC;
import recraft.cpc.common.tileentity.TileEntityLaptop;
import recraft.cpc.init.CPCBlocks;

import java.util.Random;

public class BlockLaptop extends BlockContainer {
	public BlockLaptop() {
		super(Material.iron);
		setCreativeTab(CPC.tabCPC);
		setBlockTextureName("cpc:laptop");
		setBlockName("cpc:laptop");
	}

	public Item getBlockDropped(int par1, Random random, int par3) {
		return Item.getItemFromBlock(CPCBlocks.laptop);
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

	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		world.func_147453_f(x, y, z, block);
		super.breakBlock(world, x, y, z, block, par6);
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
									EntityPlayer entityPlayer, int par6,
									float par7, float par8, float par9) {
		TileEntity tileEntity = world.getTileEntity(x, y ,z);
		if (tileEntity instanceof TileEntityLaptop) {
			entityPlayer.openGui(CPC.instance, 0, world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityLaptop();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {
		byte b0 = 0;
		int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        switch (l) {
            case 0:
                b0 = 2;
                break;
            case 1:
                b0 = 5;
                break;
            case 2:
                b0 = 3;
                break;
            case 3:
                b0 = 4;
                break;
        }
		world.setBlockMetadataWithNotify(x, y, z, b0, 2);
	}
}
