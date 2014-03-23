
package netz.mods.cpc.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import netz.mods.cpc.common.entity.monster.EntitySmile;

public class RenderSmileDog extends RenderLiving
{
	private static final ResourceLocation resourceIdle = new ResourceLocation("cpc:textures/entity/smiledog/idle.png");
	private static final ResourceLocation resourceAngry = new ResourceLocation("cpc:textures/entity/smiledog/angry.png");

	public RenderSmileDog(ModelBase modelbase, float f)
	{
		super(modelbase, f);
	}
	
	public ResourceLocation getTexture(EntitySmile par1EntitySmile)
	{
		return par1EntitySmile.isAngry() ? resourceAngry : resourceIdle;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getTexture((EntitySmile)par1Entity);
	}

}
