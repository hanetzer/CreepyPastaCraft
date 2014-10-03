package com.teamfear.creepypastacraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSeedEater extends ModelBase {

    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer back;
    ModelRenderer jaw;


    public ModelSeedEater() {
        super();
        super.textureWidth = 64;
        super.textureHeight = 32;
        this.head = new ModelRenderer(this, 43, 0);
        this.head.addBox(-3.0F, -7.0F, -4.0F, 6, 5, 4);
        this.head.setRotationPoint(0.0F, 4.0F, -2.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 28, 17);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 3);
        this.body.setRotationPoint(0.0F, 1.0F, -1.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.4014257F, 0.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 51, 11);
        this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 3, 18, 3);
        this.rightarm.setRotationPoint(-4.0F, 4.0F, -1.0F);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 51, 11);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 3, 18, 3);
        this.leftarm.setRotationPoint(5.0F, 4.0F, -1.0F);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
        this.rightleg = new ModelRenderer(this, 0, 17);
        this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 3, 12, 3);
        this.rightleg.setRotationPoint(-2.0F, 12.0F, 3.0F);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        this.leftleg = new ModelRenderer(this, 0, 17);
        this.leftleg.addBox(-1.0F, 0.0F, -2.0F, 3, 12, 3);
        this.leftleg.setRotationPoint(2.0F, 12.0F, 3.0F);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        this.back = new ModelRenderer(this, 1, 4);
        this.back.addBox(-7.0F, -4.0F, -1.0F, 14, 7, 3);
        this.back.setRotationPoint(0.0F, 1.0F, -1.0F);
        this.back.setTextureSize(64, 32);
        this.back.mirror = true;
        this.setRotation(this.back, 0.4014257F, 0.0F, 0.0F);
        this.jaw = new ModelRenderer(this, 12, 18);
        this.jaw.addBox(-2.0F, -2.0F, -4.0F, 4, 3, 4);
        this.jaw.setRotationPoint(0.0F, 4.0F, -2.0F);
        this.jaw.setTextureSize(64, 32);
        this.jaw.mirror = true;
        this.setRotation(this.jaw, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.back.render(f5);
        this.jaw.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.head.rotateAngleY = par4 / 57.295776F;
        this.head.rotateAngleX = par5 / 57.295776F;
        this.jaw.rotateAngleY = par4 / 57.295776F;
        this.jaw.rotateAngleX = par5 / 57.295776F;
        this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
        this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
        this.rightarm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.leftarm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.rightarm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.leftarm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
    }
}
