package recraft.cpc.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import recraft.cpc.CPC;
import recraft.cpc.common.entity.projectile.EntityStephano;

public class ItemStephano extends Item
{
	public ItemStephano() {
		setMaxStackSize(8);
		setMaxDamage(0);
		setCreativeTab(CPC.tabCPC);
		setUnlocalizedName("cpc:stephano");
		setTextureName("cpc:stephano");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(!par3EntityPlayer.capabilities.isCreativeMode)
		{
			--itemStack.stackSize;
		}

		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if(!par2World.isRemote)
		{
			par2World.spawnEntityInWorld(new EntityStephano(par2World, par3EntityPlayer, 1));
		}
		return itemStack;
	}
	
}