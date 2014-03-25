package recraft.cpc.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import recraft.cpc.common.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.registry.GameRegistry;

public class CPCBlock
{
	public static Block laptop;
	public CPCBlock()
	{
		super();
	}
	
	public static void init()
	{
		initBlocks();
		regBlocks();
		addRecipes();
	}
	
	private static void initBlocks()
	{
		laptop	 = (new BlockLaptop(501)).setHardness(2.0F).setResistance(20.0F);
	}

	private static void regBlocks()
	{
		GameRegistry.registerBlock(laptop,  "laptop");
		GameRegistry.registerTileEntity(TileEntityLaptop.class, "entityLaptop");
		GameRegistry.registerCustomItemStack("laptop", new ItemStack(laptop, 1));
	}

	private static void addRecipes()
	{
		GameRegistry.addRecipe((new ItemStack(laptop, 1)), "xxx", "cdc", "xxx", 'x', (new ItemStack(Item.ingotIron)), 'c', (new ItemStack(Item.redstone)), 'd', (new ItemStack(Item.diamond)));
	}
}
