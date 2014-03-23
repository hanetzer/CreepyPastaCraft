package netz.mods.cpc.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import netz.mods.cpc.CPC;

public class ItemPasta extends ItemFood
{
	public ItemPasta(int par1, int par2, float par3, boolean par4)
	{
		super(par1, par2, par3, par4);
		this.setAlwaysEdible();
		this.setUnlocalizedName("cpc.pasta");
		this.setCreativeTab(CPC.tabCPC);
		this.setTextureName("cpc:pasta");
	}
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
		return new ItemStack(Item.bowlEmpty);
	}
}
