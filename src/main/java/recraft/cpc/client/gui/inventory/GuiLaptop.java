package recraft.cpc.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import recraft.cpc.common.inventory.ContainerLaptop;
import recraft.cpc.common.tileentity.TileEntityLaptop;

import org.lwjgl.opengl.GL11;

public class GuiLaptop extends GuiContainer {
	private TileEntityLaptop laptopInventory;
	private IInventory playerInventory;
	private int inventoryRows;

	public GuiLaptop(InventoryPlayer inventoryPlayer, TileEntityLaptop tileEntityLaptop) {
		super(new ContainerLaptop(inventoryPlayer, tileEntityLaptop));
		this.laptopInventory = tileEntityLaptop;
		this.playerInventory = inventoryPlayer;
		this.allowUserInput = false;
		short short1 = 222;
		int i = short1 - 108;
		this.inventoryRows = inventoryPlayer.getSizeInventory() / 9;
		this.ySize = i + this.inventoryRows * 18;
	}

	protected void drawGuiContainerForegroundLayer(int par1int, int par2int) {}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		super.mc.renderEngine.bindTexture(new ResourceLocation("cpc:textures/gui/laptop.png"));
		int j = (super.width - super.xSize) / 2;
		int k = (super.height - super.ySize) / 2;
		this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
		int i1;

		i1 = this.laptopInventory.getPrintProgressScaled(24);
		this.drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
	}
}
