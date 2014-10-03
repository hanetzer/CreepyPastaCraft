package com.teamfear.creepypastacraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.teamfear.creepypastacraft.client.model.ModelVampireBat;
import com.teamfear.creepypastacraft.common.entity.monster.EntityVampireBat;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderVampireBat extends RenderLiving
{
	private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");

	/**
	 * not actually sure this is size, is not used as of now, but the model would be recreated if the value changed and
	 * it seems a good match for a bats size
	 */
	private int renderedBatSize;

	public RenderVampireBat()
	{
		super(new ModelVampireBat(), 0.25F);
		this.renderedBatSize = ((ModelVampireBat)this.mainModel).getBatSize();
	}

	public void func_82443_a(EntityVampireBat entity, double par2, double par4, double par6, float par8, float par9)
	{
		int i = ((ModelVampireBat)this.mainModel).getBatSize();

		if (i != this.renderedBatSize)
		{
			this.renderedBatSize = i;
			this.mainModel = new ModelBat();
		}

		super.doRender(entity, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation getBatTextures(EntityVampireBat par1EntityKeese)
	{
		return batTextures;
	}

	protected void func_82442_a(EntityVampireBat par1EntityKeese, float par2)
	{
		GL11.glScalef(0.35F, 0.35F, 0.35F);
	}

	protected void func_82445_a(EntityVampireBat entity, double par2, double par4, double par6)
	{
		super.renderLivingAt(entity, par2, par4, par6);
	}

	protected void func_82444_a(EntityVampireBat entity, float par2, float par3, float par4)
	{
		if (!entity.getIsBatHanging())
		{
			GL11.glTranslatef(0.0F, MathHelper.cos(par2 * 0.3F) * 0.1F, 0.0F);
		}
		else
		{
			GL11.glTranslatef(0.0F, -0.1F, 0.0F);
		}

		super.rotateCorpse(entity, par2, par3, par4);
	}

	public void doRender(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.func_82443_a((EntityVampireBat)entity, par2, par4, par6, par8, par9);
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
	 * entityLiving, partialTickTime
	 */
	 protected void preRenderCallback(EntityLivingBase entity, float par2)
	 {
		 this.func_82442_a((EntityVampireBat)entity, par2);
	 }

	 protected void rotateCorpse(EntityLivingBase entity, float par2, float par3, float par4)
	 {
		 this.func_82444_a((EntityVampireBat)entity, par2, par3, par4);
	 }

	 /**
	  * Sets a simple glTranslate on a LivingEntity.
	  */
	 protected void renderLivingAt(EntityLivingBase entity, double par2, double par4, double par6)
	 {
		 this.func_82445_a((EntityVampireBat)entity, par2, par4, par6);
	 }

	 public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
	 {
		 this.func_82443_a((EntityVampireBat)par1EntityLivingBase, par2, par4, par6, par8, par9);
	 }

	 /**
	  * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	  */
	 protected ResourceLocation getEntityTexture(Entity entity)
	 {
		 return this.getBatTextures((EntityVampireBat)entity);
	 }

	 public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9)
	 {
		 this.func_82443_a((EntityVampireBat)entity, par2, par4, par6, par8, par9);
	 }
}
