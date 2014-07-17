package recraft.cpc.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import recraft.cpc.common.entity.passive.EntityJane;

@SideOnly(Side.CLIENT)
public class RenderJane extends RenderLiving {
	public RenderJane(ModelBase model, float par2) {
		super(model, par2);
	}

	protected ResourceLocation getTexture(EntityJane entityJane) {
		return new ResourceLocation("cpc:textures/entity/jane.png");
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getTexture((EntityJane)entity);
	}
}
