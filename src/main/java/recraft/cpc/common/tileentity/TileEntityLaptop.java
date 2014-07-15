package recraft.cpc.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import recraft.cpc.CPC;
import recraft.cpc.api.registry.PastaRegistry;
import recraft.cpc.common.block.BlockLaptop;

import java.util.Random;

public class TileEntityLaptop extends TileEntity implements IInventory {
	private ItemStack[] laptopItemStacks;
	public int printTime = 0;
	public int currentPrintTime = 0;
	public int standardPrintTime = 0;
	public float lidAngle;
	public float prevLidAngle;
	public Random rand = new Random();
	public CPC cpc;
	public int numUsingPlayers;
	public boolean opened;
	private String customName;

	public int getSizeInventory() {
		return this.laptopItemStacks.length;
	}

	public TileEntityLaptop() {
		laptopItemStacks = new ItemStack[2];
		printTime = 0;
		currentPrintTime = 0;

	}

	public ItemStack getStackInSlot(int var1) {
		return this.laptopItemStacks[var1];
	}

	public ItemStack decrStackSize(int par1, int par2) {
		if (this.laptopItemStacks[par1] != null) {
			ItemStack itemStack;
			if (this.laptopItemStacks[par1].stackSize <= par2) {
				itemStack = this.laptopItemStacks[par1];
				this.laptopItemStacks[par1] = null;
				return itemStack;
			}
			else {
				itemStack = this.laptopItemStacks[par1].splitStack(par2);

				if (this.laptopItemStacks[par1].stackSize == 0) {
					this.laptopItemStacks[par1] = null;
				}
				return itemStack;
			}
		}
		else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.laptopItemStacks[par1] != null) {
			ItemStack itemStack = this.laptopItemStacks[par1];
			this.laptopItemStacks[par1] = null;
			return itemStack;
		}
		else {
   			return null;
		}
	}

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.laptopItemStacks[par1] = par2ItemStack;
		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.laptop";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	public void setCustomInventoryName(String par1) {
		this.customName = par1;
	}

	public void readFromNBT(NBTTagCompound par1NBTTagC) {
		super.readFromNBT(par1NBTTagC);
		NBTTagList nbttaglist = par1NBTTagC.getTagList("Items", 10);
		this.laptopItemStacks = new ItemStack[this.getSizeInventory()];

		if(par1NBTTagC.hasKey("CustomName", 8))
		{
			this.customName = par1NBTTagC.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;
			if (j >= 0 && j < this.laptopItemStacks.length) {
				this.laptopItemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}
		this.printTime = par1NBTTagC.getShort("PrintTime");
		this.standardPrintTime = par1NBTTagC.getShort("StandardPrintTime");
		this.currentPrintTime = getItemBurnTime(this.laptopItemStacks[1]);
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("PrintTime", (short) this.printTime);
		par1NBTTagCompound.setShort("StandardPrintTime", (short) this.standardPrintTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.laptopItemStacks.length; ++i) {
			if (this.laptopItemStacks[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				this.laptopItemStacks[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);

		if(this.hasCustomInventoryName())
		{
			par1NBTTagCompound.setString("CustomName", this.customName);
		}
	}

	public int getInventoryStackLimit() {
		return 8;
	}

	public int getCookProgressScaled(int par1) {
		return this.standardPrintTime * par1 / 200;
	}

	public int getBurnTimeRemainingScaled(int par1) {
		if (this.currentPrintTime == 0) {
			this.currentPrintTime = 200;
		}
		return this.printTime * par1 / this.currentPrintTime;
	}

	public boolean isBurning() {
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

		if (!this.worldObj.isRemote)
		{
			if (this.printTime == 0 && this.canPrint())
			{
				this.currentPrintTime = this.printTime = getItemBurnTime(this.laptopItemStacks[1]);

				if (this.printTime > 0)
				{
					flag1 = true;

					if (this.laptopItemStacks[1] != null)
					{
						--this.laptopItemStacks[1].stackSize;

						if (this.laptopItemStacks[1].stackSize == 0)
						{
							this.laptopItemStacks[1] = laptopItemStacks[1].getItem().getContainerItem(laptopItemStacks[1]);
						}
					}
				}
			}

			if (this.isBurning() && this.canPrint())
			{
				++this.standardPrintTime;

				if (this.standardPrintTime == 200)
				{
					this.standardPrintTime = 0;
					this.printItem();
					flag1 = true;
				}
			}
			else
			{
				this.standardPrintTime = 0;
			}

			if (flag != this.printTime > 0)
			{
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
			this.worldObj.playSoundEffect(d1, (double) this.yCoord + 0.5D, d0, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
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
				this.worldObj.playSoundEffect(d0, (double) this.yCoord + 0.5D, d2, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
			}
		}
	}

	private boolean canPrint() {
		if (this.laptopItemStacks[0] == null) {
			return false;
		}
		else {
			ItemStack itemStack = PastaRegistry.getPrintingResult(laptopItemStacks[0]);
			if (itemStack == null) return false;
			if (this.laptopItemStacks[1] == null) return true;
			if (!this.laptopItemStacks[1].isItemEqual(itemStack)) return false;
			int result = laptopItemStacks[1].stackSize + itemStack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.laptopItemStacks[1].getMaxStackSize();
		}
	}

	public void printItem() {
		if (this.canPrint()) {
			ItemStack itemStack = PastaRegistry.getPrintingResult(laptopItemStacks[0]);

			if (this.laptopItemStacks[1] == null) {
				this.laptopItemStacks[1] = itemStack.copy();
			}

			--this.laptopItemStacks[0].stackSize;

			if (this.laptopItemStacks[0].stackSize <= 0) {
				this.laptopItemStacks[0] = null;
			}
		}
	}

	public static int getItemBurnTime(ItemStack par1ItemStack) {
		if (par1ItemStack == null) {
			return 0;
		}
		else if (par1ItemStack.getItem() == Items.paper) {
				return 2000;
		}
		else {
			return 0;
		}
	}

	public static boolean isPaper(ItemStack par0ItemStack) {
		return getItemBurnTime(par0ItemStack) > 0;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord)==this && par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {
		if (numUsingPlayers < 0) {
			numUsingPlayers = 0;
		}

		++this.numUsingPlayers;
		opened = true;
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
	}

	public void closeInventory() {
		if (this.getBlockType() instanceof BlockLaptop) {
			this.numUsingPlayers -= 1;
			opened = false;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
		}
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return false;
	}

	public boolean receiveClientEvent(int par1, int par2) {
		if (par1 == 1) {
			this.numUsingPlayers = par2;
			return true;
		}
		else {
			return super.receiveClientEvent(par1, par2);
		}
	}

}

