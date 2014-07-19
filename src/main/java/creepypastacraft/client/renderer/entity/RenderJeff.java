package creepypastacraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import creepypastacraft.common.entity.monster.EntityJeff;

@SideOnly(Side.CLIENT)
public class RenderJeff extends RenderLiving {
    public RenderJeff(ModelBase model, float par2) {
        super(model, par2);
    }

    protected ResourceLocation getTexture(EntityJeff entityJeff) {
        return new ResourceLocation("cpc:textures/entity/jeff.png");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntityJeff) entity);
    }
}
