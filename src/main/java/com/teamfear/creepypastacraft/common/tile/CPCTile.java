package com.teamfear.creepypastacraft.common.tile;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import com.teamfear.creepypastacraft.common.tileentity.TileEntityLaptop;

import static com.teamfear.creepypastacraft.init.CPCBlocks.laptop;
import static net.minecraft.init.Items.diamond;
import static net.minecraft.init.Items.iron_ingot;
import static net.minecraft.init.Items.redstone;

public class CPCTile
{
    public static void init() {
        regBlocks();
        addRecipes();
    }

    private static void regBlocks() {
        laptop = registerBlock(new TileLaptop().setUnlocalizedName("cpc.laptop"));
        GameRegistry.registerTileEntity(TileEntityLaptop.class, "entityLaptop");
    }

    private static void addRecipes() {
        GameRegistry.addRecipe((new ItemStack(laptop, 1)), "xxx", "cdc", "xxx",
				'x', (new ItemStack(iron_ingot)),
				'c', (new ItemStack(redstone)),
				'd', (new ItemStack(diamond)));
    }

    public static Block registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.com.teamfear.creepypastacraft.", ""));

        return block;
    }

    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.com.teamfear.creepypastacraft.", ""));

        return block;
    }

    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, Object... constructorArgs) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.com.teamfear.creepypastacraft.", ""), constructorArgs);

        return block;
    }
}
