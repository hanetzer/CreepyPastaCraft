package recraft.cpc.common.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import recraft.cpc.CPC;
import recraft.cpc.common.block.BlockLaptop;
import recraft.cpc.common.block.CPCBlock;
import recraft.cpc.common.item.crafting.LaptopRecipes;
import recraft.cpc.common.registry.PastaRegistry;
import recraft.cpc.init.CPCBlocks;

import java.util.Map;
import java.util.Random;

public class TileEntityLaptop extends TileEntity implements ISidedInventory {
	private ItemStack[] itemStacks = new ItemStack[2];
	public int printTime = 0;
	public int currentPrintTime = 0;
	public int standardPrintTime = 0;
	public float lidAngle;
	public float prevLidAngle;
	private int ticksSinceSync;
	public Random rand = new Random();
	public CPC cpc;
	public int numUsingPlayers;
	public boolean opened;
	private String customName;

	public TileEntityLaptop() {
		super();
	}

	public int getSizeInventory() {
		return this.itemStacks.length;
	}

	public ItemStack getStackInSlot(int var1) {
		return this.itemStacks[var1];
	}

	public ItemStack decrStackSize(int par1, int par2) {
		if (this.itemStacks[par1] != null)
		{
			ItemStack itemStack;

			if (this.itemStacks[par1].stackSize <= par2)
			{
				itemStack = this.itemStacks[par1];
				this.itemStacks[par1] = null;
				return itemStack;
			}
			else
			{
				itemStack = this.itemStacks[par1].splitStack(par2);

				if (this.itemStacks[par1].stackSize == 0)
				{
					this.itemStacks[par1] = null;
				}

				return itemStack;
			}
		}
		else
		{
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.itemStacks[par1] != null)
		{
			ItemStack itemStack = this.itemStacks[par1];
			this.itemStacks[par1] = null;
			return itemStack;
		}
		else
		{
   			return null;
		}
	}

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.itemStacks[par1] = par2ItemStack;
		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
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
		this.itemStacks = new ItemStack[this.getSizeInventory()];

		if(par1NBTTagC.hasKey("CustomName", 8))
		{
			this.customName = par1NBTTagC.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;
			if (j >= 0 && j < this.itemStacks.length) {
				this.itemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}
		this.printTime = par1NBTTagC.getShort("PrintTime");
		this.standardPrintTime = par1NBTTagC.getShort("StandardPrintTime");
		this.currentPrintTime = getItemBurnTime(this.itemStacks[1]);
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("PrintTime", (short) this.printTime);
		par1NBTTagCompound.setShort("StandardPrintTime", (short) this.standardPrintTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.itemStacks.length; ++i) {
			if (this.itemStacks[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(nbttagcompound);
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
			if (this.printTime == 0 && this.canSmelt())
			{
				this.currentPrintTime = this.printTime = getItemBurnTime(this.itemStacks[1]);

				if (this.printTime > 0)
				{
					flag1 = true;

					if (this.itemStacks[1] != null)
					{
						--this.itemStacks[1].stackSize;

						if (this.itemStacks[1].stackSize == 0)
						{
							this.itemStacks[1] = itemStacks[1].getItem().getContainerItem(itemStacks[1]);
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt())
			{
				++this.standardPrintTime;

				if (this.standardPrintTime == 200)
				{
					this.standardPrintTime = 0;
					this.smeltItem();
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
		if (++this.ticksSinceSync % 20 * 4 == 0) {
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, CPCBlocks.laptop, 1, this.numUsingPlayers);
		}

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

	private boolean canSmelt() {
		if (this.itemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = LaptopRecipes.smelting().getSmeltingResult(this.itemStacks[0].getItem().itemID);
			return itemstack == null ? false : (this.itemStacks[2] == null ? true : (!this.itemStacks[2].isItemEqual(itemstack) ? false : (this.itemStacks[2].stackSize < this.getInventoryStackLimit() && this.itemStacks[2].stackSize < this.itemStacks[2].getMaxStackSize() ? true : this.itemStacks[2].stackSize < itemstack.getMaxStackSize())));
		}
	}

	@SuppressWarnings("unused")
	public void smeltItem() {
		if (this.canSmelt()) {
			for (Map.Entry<String, Class> entry : PastaRegistry.pastaListStringClass.entrySet());
			ItemStack itemstack = LaptopRecipes.smelting().getSmeltingResult(this.itemStacks[0].getItem().itemID);
			Item[] var10000 = new Item[10];
			CPC var10003 = this.cpc;
			var10000[0] = CPArchive.jeff;
			var10003 = this.cpc;
			var10000[1] = CPArchive.rake;
			var10003 = this.cpc;
			var10000[2] = CPArchive.smile;
			var10003 = this.cpc;
			var10000[3] = CPArchive.jane;
			var10003 = this.cpc;
			var10000[4] = CPArchive.strider;
			var10003 = this.cpc;
			var10000[5] = CPArchive.squid;
			var10003 = this.cpc;
			var10000[6] = CPArchive.slender;
			var10003 = this.cpc;
			var10000[7] = CPArchive.moth;
			var10003 = this.cpc;
			var10000[8] = CPArchive.jack;
			var10003 = this.cpc;
			var10000[9] = CPArchive.seed;
			Item[] archives = var10000;
			int q = this.rand.nextInt(1);
			int maxArchives = this.rand.nextInt(archives.length);
			if (q == 0) {
				itemstack = new ItemStack(archives[maxArchives]);
			}

			if (this.itemStacks[2] == null) {
				this.itemStacks[2] = itemstack.copy();
			} else if (this.itemStacks[2].itemID == itemstack.itemID) {
				this.itemStacks[2].stackSize += itemstack.stackSize;
			}

			if (this.itemStacks[0].getItem().hasContainerItem()) {
				this.itemStacks[0] = new ItemStack(this.itemStacks[0].getItem().setFull3D());
			} else {
				--this.itemStacks[0].stackSize;
			}

			if (this.itemStacks[0].stackSize <= 0) {
				this.itemStacks[0] = null;
			}


		}
	}

	public static int getItemBurnTime(ItemStack par1ItemStack) {
		if (par1ItemStack == null) {
			return 1;
		} else {
			// int i = par1ItemStack.getItem().itemID;
			return GameRegistry.getFuelValue(par1ItemStack);
		}
	}

	public static boolean func_52005_b(ItemStack par0ItemStack) {
		return getItemBurnTime(par0ItemStack) > 0;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) super.xCoord + 0.5D, (double) super.yCoord + 0.5D, (double) super.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {
		if (numUsingPlayers < 0)
		{
			numUsingPlayers = 0;
		}

		++this.numUsingPlayers;
		opened = true;
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
	}

	public void closeInventory() {
		if (this.getBlockType() instanceof BlockLaptop)
		{
			this.numUsingPlayers -= 1;
			opened = false;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
		}
	}

	public boolean receiveClientEvent(int par1, int par2) {
		if (par1 == 1) {
			this.numUsingPlayers = par2;
			return true;
		} else {
			return super.receiveClientEvent(par1, par2);
		}
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	/**
	 * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
	 * block.
	 *
	 * @param p_94128_1_
	 */
	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return new int[0];
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 *
	 * @param par1int
	 * @param par2Itemstack
	 * @param p_102007_3_
	 */
	@Override
	public boolean canInsertItem(int par1int, ItemStack par2Itemstack, int p_102007_3_) {
		return this.isItemValidForSlot(par1int, par2Itemstack);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 *
	 * @param p_102008_1_
	 * @param p_102008_2_
	 * @param p_102008_3_
	 */
	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return false;
	}
}

