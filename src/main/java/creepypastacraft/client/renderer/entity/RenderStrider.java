package creepypastacraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import creepypastacraft.common.entity.passive.EntityStrider;

@SideOnly(Side.CLIENT)
public class RenderStrider extends RenderLiving {
    public RenderStrider(ModelBase model, float par2) {
        super(model, par2);
    }

    protected ResourceLocation getTexture(EntityStrider entityStrider) {
        return new ResourceLocation("creepypastacraft:textures/entity/strider.png");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntityStrider) entity);
    }
}
