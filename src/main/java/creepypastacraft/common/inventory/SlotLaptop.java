package creepypastacraft.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotLaptop extends Slot {

    private EntityPlayer player;
    private int field_75228_b;

    public SlotLaptop(EntityPlayer player, IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.player = player;
    }

    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    public ItemStack decrStackSize(int par1) {
        if (this.getHasStack()) {
            this.field_75228_b += Math.min(par1, this.getStack().stackSize);
        }
        return super.decrStackSize(par1);
    }

    public void onPickupFromSlot(ItemStack stack) {
        this.onCrafting(stack);
        super.onPickupFromSlot(this.player, stack);
    }

    protected void onCrafting(ItemStack stack, int par2) {
        this.field_75228_b += par2;
        this.onCrafting(stack);
    }

    protected void onCrafting(ItemStack stack) {
        stack.onCrafting(this.player.worldObj, this.player, this.field_75228_b);
        this.field_75228_b = 0;
    }
}
