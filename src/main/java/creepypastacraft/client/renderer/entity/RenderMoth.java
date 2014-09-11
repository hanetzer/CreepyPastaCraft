package creepypastacraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import creepypastacraft.common.entity.monster.EntityMothman;

@SideOnly(Side.CLIENT)
public class RenderMoth extends RenderLiving {
    public RenderMoth(ModelBase model, float par2) {
        super(model, par2);
    }

    protected ResourceLocation getTexture(EntityMothman entityMothman) {
        return new ResourceLocation("creepypastacraft:textures/entity/mothman.png");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getTexture((EntityMothman) entity);
    }
}
