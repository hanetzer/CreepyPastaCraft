package creepypastacraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSmileDog extends ModelBase {

    ModelRenderer head, body, mane, leg1, leg2, leg3, leg4, tail, ear1, ear2, nose;

    public ModelSmileDog() {
        super();
        super.textureWidth = 64;
        super.textureHeight = 32;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4);
        head.setRotationPoint(-1.0F, 13.5F, -7.0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(this.head, 0.0F, 0.0F, 0.0F);
        body = new ModelRenderer(this, 18, 14);
        body.addBox(-4.0F, -2.0F, -3.0F, 6, 9, 6);
        body.setRotationPoint(0.0F, 14.0F, 2.0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(this.body, 1.570796F, 0.0F, 0.0F);
        mane = new ModelRenderer(this, 21, 0);
        mane.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 7);
        mane.setRotationPoint(-1.0F, 14.0F, -3.0F);
        mane.setTextureSize(64, 32);
        mane.mirror = true;
        setRotation(this.mane, 1.570796F, 0.0F, 0.0F);
        leg1 = new ModelRenderer(this, 0, 18);
        leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
        leg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
        leg1.setTextureSize(64, 32);
        leg1.mirror = true;
        setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
        leg2 = new ModelRenderer(this, 0, 18);
        leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
        leg2.setRotationPoint(0.5F, 16.0F, 7.0F);
        leg2.setTextureSize(64, 32);
        leg2.mirror = true;
        setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
        leg3 = new ModelRenderer(this, 0, 18);
        leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
        leg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
        leg3.setTextureSize(64, 32);
        leg3.mirror = true;
        setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
        leg4 = new ModelRenderer(this, 0, 18);
        leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
        leg4.setRotationPoint(0.5F, 16.0F, -4.0F);
        leg4.setTextureSize(64, 32);
        leg4.mirror = true;
        setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
        tail = new ModelRenderer(this, 9, 18);
        tail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
        tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        tail.setTextureSize(64, 32);
        tail.mirror = true;
        setRotation(this.tail, 1.130069F, 0.0F, 0.0F);
        ear1 = new ModelRenderer(this, 16, 14);
        ear1.addBox(-3.0F, -5.0F, 0.0F, 2, 2, 1);
        ear1.setRotationPoint(-1.0F, 13.5F, -7.0F);
        ear1.setTextureSize(64, 32);
        ear1.mirror = true;
        setRotation(this.ear1, 0.0F, 0.0F, 0.0F);
        ear2 = new ModelRenderer(this, 16, 14);
        ear2.addBox(1.0F, -5.0F, 0.0F, 2, 2, 1);
        ear2.setRotationPoint(-1.0F, 13.5F, -7.0F);
        ear2.setTextureSize(64, 32);
        ear2.mirror = true;
        setRotation(this.ear2, 0.0F, 0.0F, 0.0F);
        nose = new ModelRenderer(this, 0, 10);
        nose.addBox(-2.0F, 0.0F, -5.0F, 3, 3, 4);
        nose.setRotationPoint(-0.5F, 13.5F, -7.0F);
        nose.setTextureSize(64, 32);
        nose.mirror = true;
        setRotation(this.nose, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        head.render(f5);
        body.render(f5);
        mane.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        tail.render(f5);
        ear1.render(f5);
        ear2.render(f5);
        nose.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        head.rotateAngleX = par5 / 57.295776F;
        head.rotateAngleY = par4 / 57.295776F;
        ear1.rotateAngleX = par5 / 57.295776F;
        ear1.rotateAngleY = par4 / 57.295776F;
        ear2.rotateAngleX = par5 / 57.295776F;
        ear2.rotateAngleY = par4 / 57.295776F;
        nose.rotateAngleX = par5 / 57.295776F;
        nose.rotateAngleY = par4 / 57.295776F;
        body.rotateAngleX = 1.5707964F;
        leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    }
}
