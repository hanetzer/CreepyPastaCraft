package com.teamfear.creepypastacraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import com.teamfear.creepypastacraft.common.entity.monster.EntitySmileDog;

public class RenderSmileDog extends RenderLiving {
    public RenderSmileDog(ModelBase model, float f) {
        super(model, f);
    }

    public ResourceLocation getTexture(EntitySmileDog entitySmile) {
        if (entitySmile.isTransformed()) {
            return new ResourceLocation("creepypastacraft:textures/entity/smiledog/angry.png");
        }
        else {
            return new ResourceLocation("creepypastacraft:textures/entity/smiledog/idle.png");
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntitySmileDog) entity);
    }
}
