package netz.mods.cpc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelJane extends ModelBase {

   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer rightarm;
   ModelRenderer leftarm;
   ModelRenderer rightleg;
   ModelRenderer leftleg;
   ModelRenderer waist;
   ModelRenderer boobs;
   public int heldItemLeft;
   public int heldItemRight;


   public ModelJane() {
      super();
      super.textureWidth = 64;
      super.textureHeight = 64;
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
      this.head.setRotationPoint(0.0F, -1.0F, 0.0F);
      this.head.setTextureSize(64, 64);
      this.head.mirror = true;
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      this.body = new ModelRenderer(this, 17, 17);
      this.body.addBox(-3.0F, 0.0F, -2.0F, 6, 13, 4);
      this.body.setRotationPoint(0.0F, -1.0F, 0.0F);
      this.body.setTextureSize(64, 64);
      this.body.mirror = true;
      this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
      this.rightarm = new ModelRenderer(this, 40, 16);
      this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 3, 12, 3);
      this.rightarm.setRotationPoint(-3.0F, 2.0F, 1.0F);
      this.rightarm.setTextureSize(64, 64);
      this.rightarm.mirror = true;
      this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
      this.leftarm = new ModelRenderer(this, 40, 16);
      this.leftarm.addBox(-1.0F, -2.0F, -1.0F, 3, 12, 3);
      this.leftarm.setRotationPoint(4.0F, 2.0F, 0.0F);
      this.leftarm.setTextureSize(64, 64);
      this.leftarm.mirror = true;
      this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
      this.rightleg = new ModelRenderer(this, 0, 16);
      this.rightleg.addBox(-1.0F, 0.0F, -2.0F, 3, 7, 4);
      this.rightleg.setRotationPoint(-2.0F, 17.0F, 0.0F);
      this.rightleg.setTextureSize(64, 64);
      this.rightleg.mirror = true;
      this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
      this.leftleg = new ModelRenderer(this, 0, 16);
      this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 3, 7, 4);
      this.leftleg.setRotationPoint(2.0F, 17.0F, 0.0F);
      this.leftleg.setTextureSize(64, 64);
      this.leftleg.mirror = true;
      this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
      this.waist = new ModelRenderer(this, 13, 35);
      this.waist.addBox(-4.0F, 13.0F, -3.0F, 8, 5, 6);
      this.waist.setRotationPoint(0.0F, -1.0F, 0.0F);
      this.waist.setTextureSize(64, 64);
      this.waist.mirror = true;
      this.setRotation(this.waist, 0.0F, 0.0F, 0.0F);
      this.boobs = new ModelRenderer(this, 49, 59);
      this.boobs.addBox(-3.0F, 3.0F, -3.0F, 6, 3, 1);
      this.boobs.setRotationPoint(0.0F, -1.0F, 0.0F);
      this.boobs.setTextureSize(64, 64);
      this.boobs.mirror = true;
      this.setRotation(this.boobs, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5);
      this.head.render(f5);
      this.body.render(f5);
      this.rightarm.render(f5);
      this.leftarm.render(f5);
      this.rightleg.render(f5);
      this.leftleg.render(f5);
      this.waist.render(f5);
      this.boobs.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
      this.head.rotateAngleY = par4 / 57.295776F;
      this.head.rotateAngleX = par5 / 57.295776F;
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
