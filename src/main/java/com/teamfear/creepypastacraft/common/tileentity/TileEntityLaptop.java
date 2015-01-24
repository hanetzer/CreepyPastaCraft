package com.teamfear.creepypastacraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import com.teamfear.creepypastacraft.api.registry.PastaRegistry;
import com.teamfear.creepypastacraft.common.tile.TileLaptop;

public class TileEntityLaptop extends TileEntity implements IInventory {
    private ItemStack[] slots;
    public int printTime;
    public static final int IN = 0, OUT = 1;
    public int currentPrintTime;
    public int standardPrintTime = 0;
    public float lidAngle;
    public float prevLidAngle;
    public int numUsingPlayers;
    public boolean opened;

    public int getSizeInventory() {
        return this.slots.length;
    }

    public TileEntityLaptop() {
        slots = new ItemStack[2];
        printTime = 0;
        currentPrintTime = 0;
    }

    public ItemStack getStackInSlot(int index) {
        return this.slots[index];
    }

    public ItemStack decrStackSize(int index, int par2) {
        if (this.slots[index] != null) {
            ItemStack stack;
            if (this.slots[index].stackSize <= par2) {
                stack = this.slots[index];
                this.slots[index] = null;
                return stack;
            } else {
                stack = this.slots[index].splitStack(par2);

                if (this.slots[index].stackSize == 0) {
                    this.slots[index] = null;
                }
                return stack;
            }
        } else {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.slots[index] != null) {
            ItemStack itemStack = this.slots[index];
            this.slots[index] = null;
            return itemStack;
        } else {
            return null;
        }
    }

    public void setInventorySlotContents(int index, ItemStack stack) {
        this.slots[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "container.cpc.laptop";
    }

    @Override
    public boolean isCustomInventoryName() {
        return false;
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound compoundTagAt = nbttaglist.getCompoundTagAt(i);
            int j = compoundTagAt.getByte("Slot") & 255;
            if (j >= 0 && j < this.slots.length) {
                this.slots[j] = ItemStack.loadItemStackFromNBT(compoundTagAt);
            }
        }
        this.printTime = compound.getShort("PrintTime");
        this.standardPrintTime = compound.getShort("StandardPrintTime");
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("PrintTime", (short) this.printTime);
        compound.setShort("StandardPrintTime", (short) this.standardPrintTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.slots.length; ++i) {
            if (this.slots[i] != null) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte) i);
                this.slots[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
        compound.setTag("Items", nbttaglist);

    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public int getPrintProgressScaled(int par1) {
        return this.standardPrintTime * par1 / 200;
    }

    public boolean isPrinting() {
        return this.printTime > 0;
    }

    public void updateEntity() {
        if (worldObj.isRemote) {
            if (opened)
                numUsingPlayers = 1;
        }
        /**
         * Begin Furnace-like function
         */
        boolean flag = this.printTime > 0;
        boolean flag1 = false;
        if (this.printTime > 0) {
            --this.printTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.printTime == 0 && this.canPrint()) {
                this.currentPrintTime = this.printTime = getItemBurnTime(this.slots[IN]);

                if (this.printTime > 0) {
                    flag1 = true;

                    if (this.slots[OUT] != null) {
                        --this.slots[1].stackSize;

                        if (this.slots[1].stackSize == 0) {
                            this.slots[1] = slots[1].getItem().getContainerItem(slots[1]);
                        }
                    }
                }
            }

            if (this.isPrinting() && this.canPrint()) {
                ++this.standardPrintTime;

                if (this.standardPrintTime == 200) {
                    this.standardPrintTime = 0;
                    this.printItem();
                    flag1 = true;
                }
            } else {
                this.standardPrintTime = 0;
            }

            if (flag != this.printTime > 0) {
                flag1 = true;
            }
        }

        if (flag1) {
            this.markDirty();
        }

        /**
         * Begin of chest-like functions
         */
        this.prevLidAngle = this.lidAngle;
        float f = 0.1F;
        double d0;

        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F) {
            double d1 = (double) this.xCoord + 0.5D;
            d0 = (double) this.zCoord + 0.5D;
            this.worldObj.playSoundEffect(d1, (double) this.yCoord + 0.5D,
                    d0, "random.chestopen", 0.5F,
                    this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F) {
            float f1 = this.lidAngle;

            if (this.numUsingPlayers > 0) {
                this.lidAngle += f;
            } else {
                this.lidAngle -= f;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;

            if (this.lidAngle < f2 && f1 >= f2) {
                d0 = (double) this.xCoord + 0.5D;
                double d2 = (double) this.zCoord + 0.5D;
                this.worldObj.playSoundEffect(d0, (double) this.yCoord + 0.5D,
                        d2, "random.chestclosed", 0.5F,
                        this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }
    }

    private boolean canPrint() {
        if (this.slots[IN] == null) {
            return false;
        } else {
            ItemStack stack = PastaRegistry.getPrinting(slots[IN]);
            if (stack == null) return false;
            if (this.slots[OUT] == null) return true;
            if (!this.slots[OUT].isItemEqual(stack)) return false;
            int result = slots[OUT].stackSize + stack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.slots[OUT].getMaxStackSize();
        }
    }

    public void printItem() {
        if (this.canPrint()) {
            ItemStack stack = PastaRegistry.getPrinting(slots[IN]);

            if (this.slots[OUT] == null) {
                this.slots[OUT] = stack.copy();
            }

            --this.slots[IN].stackSize;

            if (this.slots[IN].stackSize <= 0) {
                this.slots[IN] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        } else if (stack.getItem() == Items.paper) {
            return 200;
        } else {
            return 0;
        }
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {
        if (numUsingPlayers < 0) {
            numUsingPlayers = 0;
        }

        ++this.numUsingPlayers;
        opened = true;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
                this.getBlockType(), 1, this.numUsingPlayers);
    }

    public void closeChest() {
        if (this.getBlockType() instanceof TileLaptop) {
            this.numUsingPlayers -= 1;
            opened = false;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
                    this.getBlockType(), 1, this.numUsingPlayers);
        }
    }

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack stack) {
        return false;
    }

    public boolean receiveClientEvent(int par1, int par2) {
        switch (par1) {
            case 1:
                this.numUsingPlayers = par2;
                return true;
            default:
                return super.receiveClientEvent(par1, par2);
        }
    }
}

