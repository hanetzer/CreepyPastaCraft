package recraft.cpc.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import recraft.cpc.common.entity.passive.EntitySquidward;

public class RenderSquidward extends RenderLiving {
    public RenderSquidward(ModelBase model, float f) {
        super(model, f);
    }

    public ResourceLocation getTexture(EntitySquidward squidward) {
        if (squidward.getMood() == 1) {
            return new ResourceLocation("cpc:textures/entity/squidward/suicide.png");
        }
        else if (squidward.getMood() == 2) {
            return new ResourceLocation("cpc:textures/entity/squidward/mad.png");
        }
        return new ResourceLocation("cpc:textures/entity/squidward/happy.png");
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntitySquidward) entity);
    }
}
