package netz.mods.cpc.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import netz.mods.cpc.client.model.ModelLaptop;
import netz.mods.cpc.tileentity.TileEntityLaptop;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityLaptopRenderer extends TileEntitySpecialRenderer {

	private ModelLaptop laptopModel = new ModelLaptop();

	public void renderLaptop(TileEntityLaptop par1TileEntityLaptop, double x, double y, double z, float par8)
	{
		int i = 0;

		if (par1TileEntityLaptop.hasWorldObj())
		{
			i = par1TileEntityLaptop.getBlockMetadata();
		}

		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		bindTexture(new ResourceLocation("cpc:textures/entity/laptop.png"));
		short short1 = 0;
				if (i == 2)
		{
			short1 = 180;
		}

		if (i == 3)
		{
			short1 = 0;
		}

		if (i == 4)
		{
			short1 = 90;
		}

		if (i == 5)
		{
			short1 = -90;
		}
		GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
		float f1 = par1TileEntityLaptop.prevLidAngle + (par1TileEntityLaptop.lidAngle - par1TileEntityLaptop.prevLidAngle) * par8;
		f1 = 1.0F - f1;
		f1 = 1.0F - f1 * f1 * f1;
		this.laptopModel.base.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
		this.laptopModel.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderLaptop((TileEntityLaptop) par1TileEntity, par2, par4, par6, par8);
	}
}

