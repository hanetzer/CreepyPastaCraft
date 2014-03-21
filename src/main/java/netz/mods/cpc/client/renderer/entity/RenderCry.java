package netz.mods.cpc.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import netz.mods.cpc.entity.passive.EntityCry;

@SideOnly(Side.CLIENT)
public class RenderCry extends RenderLiving
{
	public RenderCry(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	protected ResourceLocation getTexture(EntityCry par1EntityCry)
	{
		return new ResourceLocation("cpc:textures/entity/cry.png");
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getTexture((EntityCry)par1Entity);
	}

}

