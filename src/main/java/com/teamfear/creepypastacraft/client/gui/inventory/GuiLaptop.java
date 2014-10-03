package com.teamfear.creepypastacraft.client.gui.inventory;

import com.teamfear.creepypastacraft.common.inventory.ContainerLaptop;
import com.teamfear.creepypastacraft.common.tileentity.TileEntityLaptop;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.glColor4f;

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
        this.texture = new ResourceLocation("creepypastacraft:textures/gui/laptop.png");
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture(texture);
        int w = (super.width - super.xSize) / 2;
        int h = (super.height - super.ySize) / 2;
        this.drawTexturedModalRect(w, h, 0, 0, super.xSize, super.ySize);
        int i1;

        i1 = this.laptopInventory.getPrintProgressScaled(24);
        this.drawTexturedModalRect(w + 79, h + 34, 176, 14, i1 + 1, 16);
    }
}
