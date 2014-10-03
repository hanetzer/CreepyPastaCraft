package com.teamfear.creepypastacraft.client.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import com.teamfear.creepypastacraft.client.model.ModelLaptop;
import com.teamfear.creepypastacraft.common.tileentity.TileEntityLaptop;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class TileEntityLaptopRenderer extends TileEntitySpecialRenderer {
    private ModelLaptop model = new ModelLaptop();

    public void renderLaptop(TileEntityLaptop laptop,
                             double x, double y, double z, float par5) {
        int i = 0;
        if (laptop.hasWorldObj()) {
            i = laptop.getBlockMetadata();
        }

        glPushMatrix();
        glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        glScalef(1.0F, -1.0F, -1.0F);
        bindTexture(new ResourceLocation("creepypastacraft:textures/entity/laptop.png"));
        short short1;
        switch (i) {
            case 2:
                short1 = 180;
                break;
            case 3:
                short1 = 0;
                break;
            case 4:
                short1 = 90;
                break;
            case 5:
                short1 = -90;
                break;
            default:
                short1 = 0;
        }

        glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
        float f1 = laptop.prevLidAngle + (laptop.lidAngle - laptop.prevLidAngle) * par5;
        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;
        model.base.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
        model.renderAll();
        glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile,
                                   double x, double y, double z, float par5) {
        renderLaptop((TileEntityLaptop) tile, x, y, z, par5);
    }
}
