package netz.mods.cpc.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import netz.mods.cpc.common.entity.passive.EntityJane;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJane extends RenderLiving
{
	public RenderJane(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	protected ResourceLocation getTexture(EntityJane par1EntityJane)
	{
		return new ResourceLocation("cpc:textures/entity/jane.png");
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getTexture((EntityJane)par1Entity);
	}

}

