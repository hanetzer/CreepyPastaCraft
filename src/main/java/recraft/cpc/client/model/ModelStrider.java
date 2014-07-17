package recraft.cpc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelStrider extends ModelBase {

    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;


    public ModelStrider() {
        super();
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
        this.head.setRotationPoint(0.0F, -18.0F, -6.0F);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 28, 11);
        this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 7);
        this.body.setRotationPoint(0.0F, -20.0F, 3.0F);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 1.570796F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 3, 37, 4);
        this.leg1.setRotationPoint(-3.0F, -13.0F, 7.0F);
        this.leg1.setTextureSize(64, 64);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-1.0F, 0.0F, -2.0F, 3, 37, 4);
        this.leg2.setRotationPoint(3.0F, -13.0F, 7.0F);
        this.leg2.setTextureSize(64, 64);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 3, 37, 4);
        this.leg3.setRotationPoint(-3.0F, -13.0F, -5.0F);
        this.leg3.setTextureSize(64, 64);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-1.0F, 0.0F, -2.0F, 3, 37, 4);
        this.leg4.setRotationPoint(3.0F, -13.0F, -5.0F);
        this.leg4.setTextureSize(64, 64);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.head.rotateAngleX = par5 / 57.295776F;
        this.head.rotateAngleY = par4 / 57.295776F;
        this.body.rotateAngleX = 1.5707964F;
        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    }
}
