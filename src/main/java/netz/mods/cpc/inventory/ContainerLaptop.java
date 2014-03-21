package netz.mods.cpc.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import netz.mods.cpc.tileentity.TileEntityLaptop;

public class ContainerLaptop extends Container
{
	private TileEntityLaptop laptop;
	private IInventory lowerInventory;
	private int lastCookTime = 0;
	private int lastBurnTime = 0;
	private int lastItemBurnTime = 0;

	public ContainerLaptop(TileEntityLaptop par1TileEntityLaptop, InventoryPlayer par2InventoryPlayer)
	{
		super();
		this.laptop = par1TileEntityLaptop;
		this.addSlotToContainer(new Slot(par1TileEntityLaptop, 0, 56, 34));
		this.addSlotToContainer(new SlotLaptop(Minecraft.getMinecraft().thePlayer, par1TileEntityLaptop, 2, 116, 35));
		lowerInventory = par2InventoryPlayer;
		laptop.openChest();
		int i;

		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(par2InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(par2InventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for(int i = 0; i < super.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting)super.crafters.get(i);
			if(this.lastCookTime != this.laptop.standardPrintTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.laptop.standardPrintTime);
			}

			if(this.lastBurnTime != this.laptop.printTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.laptop.printTime);
			}

			if(this.lastItemBurnTime != this.laptop.currentPrintTime) {
				icrafting.sendProgressBarUpdate(this, 2, this.laptop.currentPrintTime);
			}
		}

		this.lastCookTime = this.laptop.standardPrintTime;
		this.lastBurnTime = this.laptop.printTime;
		this.lastItemBurnTime = this.laptop.currentPrintTime;
	}

	public void updateProgressBar(int par1, int par2) {
		if(par1 == 0) {
			this.laptop.standardPrintTime = par2;
		}

		if(par1 == 1) {
			this.laptop.printTime = par2;
		}

		if(par1 == 2) {
			this.laptop.currentPrintTime = par2;
		}

	}

	public boolean canInteractWith(EntityPlayer var1) {
		return this.laptop.isUseableByPlayer(var1);
	}

	public ItemStack transferStackInSlot(int var1) {
		return null;
	}
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		this.lowerInventory.closeChest();
		laptop.closeChest();
	}

	/**
	 * Return this chest container's lower chest inventory.
	 */
	 public IInventory getLowerChestInventory()
	 {
		 return this.lowerInventory;
	 }
}

