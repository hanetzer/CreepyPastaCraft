package netz.mods.cpc.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import netz.mods.cpc.common.entity.monster.EntityRake;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRake extends RenderLiving
{
	public RenderRake(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	protected ResourceLocation getTexture(EntityRake par1EntityRake)
	{
		return new ResourceLocation("cpc:textures/entity/rake.png");
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getTexture((EntityRake)par1Entity);
	}

}
