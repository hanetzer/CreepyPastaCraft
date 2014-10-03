package com.teamfear.creepypastacraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import com.teamfear.creepypastacraft.common.entity.passive.EntitySquidward;

public class RenderSquidward extends RenderLiving {
    public RenderSquidward(ModelBase model, float f) {
        super(model, f);
    }

    public ResourceLocation getTexture(EntitySquidward squidward) {
        switch (squidward.getMood()) {
            case 1:
                return new ResourceLocation("com.teamfear.creepypastacraft:textures/entity/squidward/suicide.png");
            case 2:
                return new ResourceLocation("com.teamfear.creepypastacraft:textures/entity/squidward/mad.png");
            default:
                return new ResourceLocation("com.teamfear.creepypastacraft:textures/entity/squidward/happy.png");
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntitySquidward) entity);
    }
}
