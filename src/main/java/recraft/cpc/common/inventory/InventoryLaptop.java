package recraft.cpc.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import recraft.cpc.common.tileentity.TileEntityLaptop;

public class InventoryLaptop extends InventoryBasic {
	private TileEntityLaptop associated;

	public InventoryLaptop() {
		super("container.cpc:laptop", false, 27);
	}

	public void setAssociated(TileEntityLaptop par1TileEntityLaptop) {
		this.associated = par1TileEntityLaptop;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return !(this.associated != null && !this.associated.isUseableByPlayer(par1EntityPlayer)) && super.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public void openInventory() {
		if (this.associated != null) {
			this.associated.openInventory();
		}
		super.openInventory();
	}

	@Override
	public void closeInventory() {
		if (this.associated != null) {
			this.associated.closeInventory();
		}
		super.closeInventory();
		this.associated = null;
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	 */
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return true;
	}
}
