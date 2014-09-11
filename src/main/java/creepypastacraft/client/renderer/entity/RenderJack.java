package creepypastacraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import creepypastacraft.common.entity.monster.EntityJack;

@SideOnly(Side.CLIENT)
public class RenderJack extends RenderLiving {
    public RenderJack(ModelBase model, float par2) {
        super(model, par2);
    }

    protected ResourceLocation getTexture(EntityJack entityJack) {
        return new ResourceLocation("creepypastacraft:textures/entity/jack.png");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntityJack) entity);
    }
}
