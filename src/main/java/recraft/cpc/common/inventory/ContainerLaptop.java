package recraft.cpc.common.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import recraft.cpc.api.registry.PastaRegistry;
import recraft.cpc.common.tileentity.TileEntityLaptop;

public class ContainerLaptop extends Container {
	private TileEntityLaptop laptop;
	private IInventory lowerInventory;
	public static final int INPUT = 0, OUTPUT = 1;
	private int lastCookTime = 0;
	private int lastBurnTime = 0;
	private int lastItemBurnTime = 0;

	public ContainerLaptop(TileEntityLaptop par1TileEntityLaptop, InventoryPlayer par2InventoryPlayer) {
		super();
		this.laptop = par1TileEntityLaptop;
		this.addSlotToContainer(new Slot(par1TileEntityLaptop, INPUT, 56, 34));
		this.addSlotToContainer(new SlotLaptop(Minecraft.getMinecraft().thePlayer, par1TileEntityLaptop, OUTPUT, 116, 35));
		lowerInventory = par2InventoryPlayer;
		laptop.openInventory();
		int i;

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(par2InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(par2InventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (Object crafter : super.crafters) {
			ICrafting icrafting = (ICrafting) crafter;
			if (this.lastCookTime != this.laptop.standardPrintTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.laptop.standardPrintTime);
			}

			if (this.lastBurnTime != this.laptop.printTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.laptop.printTime);
			}

			if (this.lastItemBurnTime != this.laptop.currentPrintTime) {
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

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2SlotIndex) {
		ItemStack itemStack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2SlotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemStack1 = slot.getStack();
			itemStack = itemStack1.copy();

			if (par2SlotIndex == OUTPUT) {
				if (!this.mergeItemStack(itemStack1, OUTPUT+1, OUTPUT+36+1, true)) {
					return null;
				}

				slot.onSlotChange(itemStack1, itemStack);
			}
			else if (par2SlotIndex != INPUT) {
				if (PastaRegistry.getPrintingResult(itemStack1) != null) {
					if (!this.mergeItemStack(itemStack1, INPUT, INPUT+1, false)) {
						return null;
					}
				}
				else if (par2SlotIndex >= OUTPUT+1 && par2SlotIndex < OUTPUT+28) {
					if (!this.mergeItemStack(itemStack1, OUTPUT+28, OUTPUT+37, false)) {
						return null;
					}
				}
				else if (par2SlotIndex >= OUTPUT+28 && par2SlotIndex < OUTPUT+37 && !this.mergeItemStack(itemStack1, OUTPUT+1, OUTPUT+28, false)) {
					return null;
				}
			}
			else if (!this.mergeItemStack(itemStack1, OUTPUT+1, OUTPUT+37, false)) {
				return null;
			}

			if (itemStack1.stackSize == 0) {
				slot.putStack(null);
			}
			else {
				slot.onSlotChanged();
			}

			if (itemStack1.stackSize == itemStack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(par1EntityPlayer, itemStack1);
		}
		return itemStack;
	}

	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		this.lowerInventory.closeInventory();
		laptop.closeInventory();
	}

	/**
	 * Return this chest container's lower chest inventory.
	 */
	 public IInventory getLowerChestInventory()
	 {
		 return this.lowerInventory;
	 }
}

