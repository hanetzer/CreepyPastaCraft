package recraft.cpc.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import recraft.cpc.common.inventory.ContainerLaptop;
import recraft.cpc.common.tileentity.TileEntityLaptop;

public class GuiLaptop extends GuiContainer {
    private TileEntityLaptop laptopInventory;
    private ResourceLocation texture;

    public GuiLaptop(InventoryPlayer inventory, TileEntityLaptop laptop) {
        super(new ContainerLaptop(inventory, laptop));
        this.laptopInventory = laptop;
        this.allowUserInput = false;
        short short1 = 222;
        int i = short1 - 108;
        int inventoryRows = inventory.getSizeInventory() / 9;
        this.ySize = i + inventoryRows * 18;
        this.texture = new ResourceLocation("cpc:textures/gui/laptop.png");
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {}

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture(texture);
        int j = (super.width - super.xSize) / 2;
        int k = (super.height - super.ySize) / 2;
        this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
        int i1;

        i1 = this.laptopInventory.getPrintProgressScaled(24);
        this.drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
    }
}
