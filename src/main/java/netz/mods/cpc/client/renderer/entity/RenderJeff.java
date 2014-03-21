package netz.mods.cpc.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import netz.mods.cpc.entity.monster.EntityJeff;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJeff extends RenderLiving
{
	public RenderJeff(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	protected ResourceLocation getTexture(EntityJeff par1EntityJeff)
	{
		return new ResourceLocation("cpc:textures/entity/jeff.png");
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getTexture((EntityJeff)par1Entity);
	}

}
