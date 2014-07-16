package recraft.cpc.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotLaptop extends Slot
{

	private EntityPlayer thePlayer;
	private int field_48437_f;

	public SlotLaptop(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4, int par5) {
		super(par2IInventory, par3, par4, par5);
		this.thePlayer = par1EntityPlayer;
	}

	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return false;
	}

	public ItemStack decrStackSize(int par1)
	{
		if(this.getHasStack())
		{
			this.field_48437_f += Math.min(par1, this.getStack().stackSize);
		}
		return super.decrStackSize(par1);
	}

	public void onPickupFromSlot(ItemStack par1ItemStack) {
		this.func_48434_c(par1ItemStack);
		super.onPickupFromSlot(this.thePlayer, par1ItemStack);
	}

	protected void func_48435_a(ItemStack par1ItemStack, int par2) {
		this.field_48437_f += par2;
		this.func_48434_c(par1ItemStack);
	}

	protected void func_48434_c(ItemStack par1ItemStack)
	{
		par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_48437_f);
		this.field_48437_f = 0;
		//GameRegistry.onItemSmelted(this.thePlayer, par1ItemStack);
	}
}
