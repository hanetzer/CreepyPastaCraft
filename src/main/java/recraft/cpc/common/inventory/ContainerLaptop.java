package recraft.cpc.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import recraft.cpc.api.registry.PastaRegistry;
import recraft.cpc.common.tileentity.TileEntityLaptop;

public class ContainerLaptop extends Container {
    private TileEntityLaptop laptop;
    public static final int INPUT = 0, OUTPUT = 1;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerLaptop(InventoryPlayer inventory, TileEntityLaptop entityLaptop) {
        super();
        laptop = entityLaptop;
        addSlotToContainer(new Slot(entityLaptop, INPUT, 56, 34));
        addSlotToContainer(new SlotLaptop(inventory.player, entityLaptop, OUTPUT, 116, 35));
        laptop.openInventory();
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object crafter : super.crafters) {
            ICrafting crafting = (ICrafting) crafter;
            if (this.lastCookTime != this.laptop.standardPrintTime) {
                crafting.sendProgressBarUpdate(this, 0, laptop.standardPrintTime);
            }

            if (this.lastBurnTime != this.laptop.printTime) {
                crafting.sendProgressBarUpdate(this, 1, laptop.printTime);
            }

            if (this.lastItemBurnTime != this.laptop.currentPrintTime) {
                crafting.sendProgressBarUpdate(this, 2, laptop.currentPrintTime);
            }
        }
        lastCookTime = laptop.standardPrintTime;
        lastBurnTime = laptop.printTime;
        lastItemBurnTime = laptop.currentPrintTime;
    }

    public void updateProgressBar(int par1, int par2) {
        switch (par1) {
            case 0:
                this.laptop.standardPrintTime = par2;
                break;
            case 1:
                this.laptop.printTime = par2;
                break;
            case 2:
                this.laptop.currentPrintTime = par2;
                break;
        }

    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.laptop.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemStack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (index == OUTPUT) {
                if (!mergeItemStack(itemStack1, OUTPUT + 1, OUTPUT + 36 + 1, true)) {
                    return null;
                }
                slot.onSlotChange(itemStack1, itemStack);
            } else if (index != INPUT) {
                if (PastaRegistry.getPrinting(itemStack1) != null) {
                    if (!mergeItemStack(itemStack1, INPUT, INPUT + 1, false)) {
                        return null;
                    }
                } else if (index >= OUTPUT + 1 && index < OUTPUT + 28) {
                    if (!mergeItemStack(itemStack1, OUTPUT + 28, OUTPUT + 37, false)) {
                        return null;
                    }
                } else if (index >= OUTPUT + 28 && index < OUTPUT + 37 && !mergeItemStack(itemStack1, OUTPUT + 1, OUTPUT + 28, false)) {
                    return null;
                }
            } else if (!mergeItemStack(itemStack1, OUTPUT + 1, OUTPUT + 37, false)) {
                return null;
            }

            if (itemStack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemStack1.stackSize == itemStack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemStack1);
        }
        return itemStack;
    }

    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        laptop.closeInventory();
    }
}
