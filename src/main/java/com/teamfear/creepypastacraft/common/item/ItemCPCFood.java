package com.teamfear.creepypastacraft.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.teamfear.creepypastacraft.CPC;

public class ItemCPCFood extends ItemFood {
    public int foodType;

    public ItemCPCFood(int heal, float saturation, boolean wolf, int type) {
        super(heal, saturation, wolf);
        this.setAlwaysEdible();
        this.setMaxStackSize(1);
        this.foodType = type;
        this.setCreativeTab(CPC.tabCPC);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
        super.onItemUseFinish(stack, world, player);
        switch (foodType) {
            case 0:
				if(!player.capabilities.isCreativeMode) {
					if(stack.stackSize <= 0)
					{
						return new ItemStack(Items.bowl);
					}
					player.inventory.addItemStackToInventory(new ItemStack(Items.bowl));
				}
                return new ItemStack(Items.bowl);
            default:
                return null;
        }
    }

	@Override
    public void registerIcons(IIconRegister iconRegister) {
        switch (foodType) {
            case 0:
                itemIcon = iconRegister.registerIcon("creepypastacraft:pasta");
                break;
            default:
                itemIcon = iconRegister.registerIcon("creepypastacraft:horror");
                break;
        }
    }
}
