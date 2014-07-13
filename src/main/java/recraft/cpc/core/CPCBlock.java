package recraft.cpc.core;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
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
		regBlocks();
		addRecipes();
	}
	
	private static void regBlocks()
	{
		GameRegistry.registerBlock(laptop,  "laptop");
		GameRegistry.registerTileEntity(TileEntityLaptop.class, "entityLaptop");
		GameRegistry.registerCustomItemStack("laptop", new ItemStack(laptop, 1));
	}

	private static void addRecipes()
	{
		GameRegistry.addRecipe((new ItemStack(laptop, 1)), "xxx", "cdc", "xxx", 'x', (new ItemStack(Items.iron_ingot)), 'c', (new ItemStack(Items.redstone)), 'd', (new ItemStack(Items.diamond)));
	}
}
