package netz.mods.cpc.tileentity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import netz.mods.cpc.CPC;
import netz.mods.cpc.block.CPCBlock;
import netz.mods.cpc.item.CPArchive;
import netz.mods.cpc.item.crafting.LaptopRecipes;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityLaptop extends TileEntity implements IInventory {
	private ItemStack[] itemStacks = new ItemStack[3];
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

	public TileEntityLaptop() {
		super();
	}

	public int getSizeInventory() {
		return this.itemStacks.length;
	}

	public ItemStack getStackInSlot(int var1) {
		return this.itemStacks[var1];
	}

	public ItemStack decrStackSize(int var1, int var2) {
		if (this.itemStacks[var1] != null) {
			ItemStack var3;
			if (this.itemStacks[var1].stackSize <= var2) {
				var3 = this.itemStacks[var1];
				this.itemStacks[var1] = null;
				this.onInventoryChanged();
				return var3;
			} else {
				var3 = this.itemStacks[var1].splitStack(var2);
				if (this.itemStacks[var1].stackSize == 0) {
					this.itemStacks[var1] = null;
				}

				this.onInventoryChanged();
				return var3;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int var1) {
		if (this.itemStacks[var1] != null) {
			ItemStack var2 = this.itemStacks[var1];
			this.itemStacks[var1] = null;
			return var2;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int var1, ItemStack var2) {
		this.itemStacks[var1] = var2;
		if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
			var2.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	public String getInvName() {
		return "container.laptop";
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.itemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.tagAt(i);
			byte byte0 = nbttagcompound.getByte("Slot");
			if (byte0 >= 0 && byte0 < this.itemStacks.length) {
				this.itemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}
		this.printTime = par1NBTTagCompound.getShort("BurnTime");
		this.standardPrintTime = par1NBTTagCompound.getShort("CookTime");
		this.currentPrintTime = getItemBurnTime(this.itemStacks[1]);
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short) this.printTime);
		par1NBTTagCompound.setShort("CookTime", (short) this.standardPrintTime);
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

		if (!worldObj.isRemote) {
			if (this.printTime == 0 && this.canSmelt()) {
				this.currentPrintTime = this.printTime = getItemBurnTime(this.itemStacks[1]);
				if (this.printTime > 0) {
					flag1 = true;
					if (this.itemStacks[1] != null) {
						if (this.itemStacks[1].getItem().getShareTag()) {
							this.itemStacks[1] = new ItemStack(this.itemStacks[1].getItem().setFull3D());
						} else {
							--this.itemStacks[1].stackSize;
						}

						if (this.itemStacks[1].stackSize == 0) {
							this.itemStacks[1] = null;
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt()) {
				++this.standardPrintTime;
				if (this.standardPrintTime == 200) {
					this.standardPrintTime = 0;
					this.smeltItem();
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
			this.onInventoryChanged();
		}

		/**
		 * Begin of chest-like functions
		 */
		if (++this.ticksSinceSync % 20 * 4 == 0) {
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, CPCBlock.laptop.blockID, 1, this.numUsingPlayers);
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

	public boolean isUseableByPlayer(EntityPlayer var1) {
		return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this ? false : var1.getDistanceSq((double) super.xCoord + 0.5D, (double) super.yCoord + 0.5D, (double) super.zCoord + 0.5D) <= 64.0D;
	}

	public void openChest() {
		if (numUsingPlayers < 0)
			numUsingPlayers = 0;

		this.numUsingPlayers += 1;
		opened = true;
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, CPCBlock.laptop.blockID, 1, this.numUsingPlayers);
	}

	public void closeChest() {
		this.numUsingPlayers -= 1;
		opened = false;
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, CPCBlock.laptop.blockID, 1, this.numUsingPlayers);
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
}

