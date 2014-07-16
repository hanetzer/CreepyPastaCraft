package recraft.cpc.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import recraft.cpc.common.entity.passive.EntityPewds;

@SideOnly(Side.CLIENT)
public class RenderPewds extends RenderLiving
{
	public RenderPewds(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	protected ResourceLocation getTexture(EntityPewds par1EntityPewds)
	{
		return new ResourceLocation("cpc:textures/entity/pewds.png");
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getTexture((EntityPewds)par1Entity);
	}

}
