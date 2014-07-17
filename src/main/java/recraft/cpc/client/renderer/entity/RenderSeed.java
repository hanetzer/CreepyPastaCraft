package recraft.cpc.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import recraft.cpc.common.entity.monster.EntitySeed;

@SideOnly(Side.CLIENT)
public class RenderSeed extends RenderLiving {
	public RenderSeed(ModelBase model, float par2) {
		super(model, par2);
	}

	protected ResourceLocation getTexture(EntitySeed entitySeed) {
		return new ResourceLocation("cpc:textures/entity/seed.png");
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getTexture((EntitySeed)entity);
	}
}
