package recraft.cpc.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import recraft.cpc.CPC;

public class ItemCPCFood extends ItemFood {
	public int foodType;
	public ItemCPCFood(int par1healAmount, float par2SaturationModifier, boolean par3IsWolfsFavoriteFood, int par4FoodType) {
		super(par1healAmount, par2SaturationModifier, par3IsWolfsFavoriteFood);
		this.setAlwaysEdible();
		this.foodType = par4FoodType;
		this.setCreativeTab(CPC.tabCPC);
	}
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
		if (foodType == 0) { return new ItemStack(Items.bowl); }
		else return null;
	}

	public void registerIcons(IIconRegister iconRegister) {
		if (foodType == 0) { itemIcon = iconRegister.registerIcon("cpc:pasta"); }
		else { itemIcon = iconRegister.registerIcon("cpc:horror"); }
	}
}
