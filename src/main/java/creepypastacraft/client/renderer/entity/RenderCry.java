package creepypastacraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import creepypastacraft.common.entity.passive.EntityCry;

@SideOnly(Side.CLIENT)
public class RenderCry extends RenderLiving {
    public RenderCry(ModelBase modelBase, float par2) {
        super(modelBase, par2);
    }

    protected ResourceLocation getTexture(EntityCry entityCry) {
        return new ResourceLocation("creepypastacraft:textures/entity/cry.png");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntityCry) entity);
    }
}
