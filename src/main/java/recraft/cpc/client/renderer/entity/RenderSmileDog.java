package recraft.cpc.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import recraft.cpc.common.entity.monster.EntitySmile;

public class RenderSmileDog extends RenderLiving {
	private static final ResourceLocation resourceIdle = new ResourceLocation("cpc:textures/entity/smiledog/idle.png");
	private static final ResourceLocation resourceAngry = new ResourceLocation("cpc:textures/entity/smiledog/angry.png");

	public RenderSmileDog(ModelBase model, float f) {
		super(model, f);
	}
	
	public ResourceLocation getTexture(EntitySmile entitySmile) {
		return entitySmile.isAngry() ? resourceAngry : resourceIdle;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getTexture((EntitySmile)entity);
	}
}
