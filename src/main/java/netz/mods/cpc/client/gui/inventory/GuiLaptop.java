package netz.mods.cpc.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import netz.mods.cpc.common.inventory.ContainerLaptop;
import netz.mods.cpc.common.tileentity.TileEntityLaptop;

import org.lwjgl.opengl.GL11;

public class GuiLaptop extends GuiContainer
{
	private TileEntityLaptop laptopInventory;
	private IInventory playerInventory;
	private int inventoryRows;

	public GuiLaptop(TileEntityLaptop par1TileEntityLaptop, InventoryPlayer par2InventoryPlayer)
	{
		super(new ContainerLaptop(par1TileEntityLaptop, par2InventoryPlayer));
		this.laptopInventory = par1TileEntityLaptop;
		this.playerInventory = par2InventoryPlayer;
		this.allowUserInput = false;
		short short1 = 222;
		int i = short1 - 108;
		this.inventoryRows = par2InventoryPlayer.getSizeInventory() / 9;
		this.ySize = i + this.inventoryRows * 18;
	}

	protected void drawGuiContainerForegroundLayer()
	{
		this.fontRenderer.drawString(this.playerInventory.isInvNameLocalized() ? this.playerInventory.getInvName() : I18n.getString(this.playerInventory.getInvName()), 8, 6, 4210752);
		this.fontRenderer.drawString(this.laptopInventory.isInvNameLocalized() ? this.laptopInventory.getInvName() : I18n.getString(this.laptopInventory.getInvName()), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		super.mc.renderEngine.bindTexture(new ResourceLocation("cpc:textures/gui/laptop.png"));
		int j = (super.width - super.xSize) / 2;
		int k = (super.height - super.ySize) / 2;
		this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
		int i1;
		if(this.laptopInventory.isBurning()) {
			i1 = this.laptopInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(j + 56, k + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.laptopInventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
	}
}
