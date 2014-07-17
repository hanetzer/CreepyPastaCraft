package recraft.cpc.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import recraft.cpc.common.block.BlockLaptop;
import recraft.cpc.common.tileentity.TileEntityLaptop;

import static recraft.cpc.init.CPCBlocks.laptop;

public class CPCBlock {

    public static void init() {
        regBlocks();
        addRecipes();
    }

    private static void regBlocks() {
        laptop = registerBlock(new BlockLaptop().setBlockName("cpc:laptop"));
        GameRegistry.registerTileEntity(TileEntityLaptop.class, "entityLaptop");
    }

    private static void addRecipes() {
        GameRegistry.addRecipe((new ItemStack(laptop, 1)), "xxx", "cdc", "xxx", 'x', (new ItemStack(Items.iron_ingot)), 'c', (new ItemStack(Items.redstone)), 'd', (new ItemStack(Items.diamond)));
    }

    public static Block registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.cpc:", ""));

        return block;
    }

    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.cpc:", ""));

        return block;
    }

    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, Object... constructorArgs) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.cpc:", ""), constructorArgs);

        return block;
    }
}
