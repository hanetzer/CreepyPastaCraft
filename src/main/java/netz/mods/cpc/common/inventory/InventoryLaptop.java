package netz.mods.cpc.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import netz.mods.cpc.common.tileentity.TileEntityLaptop;

public class InventoryLaptop extends InventoryBasic
{
	private TileEntityLaptop associated;

	public InventoryLaptop()
	{
		super("container.enderchest", false, 27);
	}

	public void setAssociated(TileEntityLaptop par1TileEntityLaptop)
	{
		this.associated = par1TileEntityLaptop;
	}

	/**public void loadInventoryFromNBT(NBTTagList par1NBTTagList)
	{
		int i;

		for (i = 0; i < this.getSizeInventory(); ++i)
		{
			this.setInventorySlotContents(i, (ItemStack)null);
		}

		for (i = 0; i < par1NBTTagList.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = (NBTTagCompound)par1NBTTagList.tagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.getSizeInventory())
			{
				this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound));
			}
		}
	}

	public NBTTagList saveInventoryToNBT()
	{
		NBTTagList nbttaglist = new NBTTagList("EnderItems");

		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.getStackInSlot(i);

			if (itemstack != null)
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte)i);
				itemstack.writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		return nbttaglist;
	}*/

	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.associated != null && !this.associated.isUseableByPlayer(par1EntityPlayer) ? false : super.isUseableByPlayer(par1EntityPlayer);
	}

	public void openChest()
	{
		if (this.associated != null)
		{
			this.associated.openChest();
		}

		super.openChest();
	}

	public void closeChest()
	{
		if (this.associated != null)
		{
			this.associated.closeChest();
		}

		super.closeChest();
		this.associated = null;
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	 */
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return true;
	}
}
