package creepypastacraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import creepypastacraft.common.entity.monster.EntityRake;

@SideOnly(Side.CLIENT)
public class RenderRake extends RenderLiving {
    public RenderRake(ModelBase model, float par2) {
        super(model, par2);
    }

    protected ResourceLocation getTexture(EntityRake entityRake) {
        return new ResourceLocation("creepypastacraft:textures/entity/rake.png");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntityRake) entity);
    }
}
