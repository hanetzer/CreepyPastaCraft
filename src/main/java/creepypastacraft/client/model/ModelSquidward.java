package creepypastacraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSquidward extends ModelBase {

    ModelRenderer head, body, rightArm, leftArm, rightLeg, leftLeg, arms, mouth;
    ModelRenderer nose, neck;

    public ModelSquidward() {
        super();
        super.textureWidth = 64;
        super.textureHeight = 64;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-5.0F, -15.0F, -4.0F, 10, 10, 8);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(this.head, 0.0F, 0.0F, 0.0F);
        body = new ModelRenderer(this, 16, 38);
        body.addBox(-4.0F, 0.0F, -3.0F, 8, 19, 6);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureSize(64, 64);
        body.mirror = true;
        setRotation(this.body, 0.0F, 0.0F, 0.0F);
        rightArm = new ModelRenderer(this, 40, 16);
        rightArm.addBox(-3.0F, -1.0F, -1.0F, 4, 8, 4);
        rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        rightArm.setTextureSize(64, 64);
        rightArm.mirror = true;
        setRotation(this.rightArm, -0.8203047F, 0.0F, 0.0F);
        leftArm = new ModelRenderer(this, 40, 16);
        leftArm.addBox(-1.0F, -1.0F, -1.0F, 4, 8, 4);
        leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        leftArm.setTextureSize(64, 64);
        leftArm.mirror = true;
        setRotation(this.leftArm, -0.8203047F, 0.0F, 0.0F);
        rightLeg = new ModelRenderer(this, 0, 19);
        rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4);
        rightLeg.setRotationPoint(-2.0F, 19.0F, 0.0F);
        rightLeg.setTextureSize(64, 64);
        rightLeg.mirror = true;
        setRotation(this.rightLeg, 0.0F, 0.0F, 0.0F);
        leftLeg = new ModelRenderer(this, 0, 19);
        leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4);
        leftLeg.setRotationPoint(2.0F, 19.0F, 0.0F);
        leftLeg.setTextureSize(64, 64);
        leftLeg.mirror = true;
        setRotation(this.leftLeg, 0.0F, 0.0F, 0.0F);
        arms = new ModelRenderer(this, 39, 29);
        arms.addBox(-4.0F, 4.5F, 0.0F, 8, 4, 4);
        arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        arms.setTextureSize(64, 64);
        arms.mirror = true;
        setRotation(this.arms, -0.7679449F, 0.0F, 0.0F);
        mouth = new ModelRenderer(this, 43, 9);
        mouth.addBox(-4.0F, -5.0F, -4.0F, 8, 4, 2);
        mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
        mouth.setTextureSize(64, 64);
        mouth.mirror = true;
        setRotation(this.mouth, 0.0F, 0.0F, 0.0F);
        nose = new ModelRenderer(this, 37, 1);
        nose.addBox(-1.0F, -7.0F, -5.0F, 2, 7, 1);
        nose.setRotationPoint(0.0F, 0.0F, 0.0F);
        nose.setTextureSize(64, 64);
        nose.mirror = true;
        setRotation(this.nose, 0.0F, 0.0F, 0.0F);
        neck = new ModelRenderer(this, 19, 20);
        neck.addBox(-1.0F, -5.0F, 0.0F, 2, 5, 2);
        neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        neck.setTextureSize(64, 64);
        neck.mirror = true;
        setRotation(this.neck, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        head.render(f5);
        body.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        arms.render(f5);
        mouth.render(f5);
        nose.render(f5);
        neck.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        head.rotateAngleY = par4 / 57.295776F;
        head.rotateAngleX = par5 / 57.295776F;
        nose.rotateAngleY = par4 / 57.295776F;
        nose.rotateAngleX = par5 / 57.295776F;
        mouth.rotateAngleY = par4 / 57.295776F;
        mouth.rotateAngleX = par5 / 57.295776F;
        rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        rightLeg.rotateAngleY = 0.0F;
        leftLeg.rotateAngleY = 0.0F;
    }
}
