package com.teamfear.creepypastacraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import com.teamfear.creepypastacraft.common.entity.passive.EntityMutahar;

@SideOnly(Side.CLIENT)
public class RenderMutahar extends RenderLiving {
    public RenderMutahar(ModelBase model, float par2) {
        super(model, par2);
    }

    protected ResourceLocation getTexture(EntityMutahar entityMutahar) {
        return new ResourceLocation("creepypastacraft:textures/entity/mutahar.png");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntityMutahar) entity);
    }
}
