package netz.mods.cpc.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import netz.mods.cpc.common.block.BlockLaptop;
import netz.mods.cpc.common.tileentity.TileEntityLaptop;

/**
 * Created by netz on 3/21/14.
 */
public class CPCBlocks {
	public static void init() {
		registerBlocks();
	}
	private static void registerBlocks() {
		registerBlock(new BlockLaptop().setHardness(2.0F).setResistance(20.0F), TileEntityLaptop.class, "cpc.entityLaptop");
	}

	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerBlock(Block block, Class<? extends TileEntity> tileEntity, String string) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
		GameRegistry.registerTileEntity(tileEntity, string);
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
	{
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, Object... constructorArgs)
	{
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""), null, constructorArgs);
	}

}
