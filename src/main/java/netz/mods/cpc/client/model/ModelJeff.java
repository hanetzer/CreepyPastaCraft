package netz.mods.cpc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelJeff extends ModelBase {

   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer rightarm;
   ModelRenderer leftarm;
   ModelRenderer rightleg;
   ModelRenderer leftleg;
   ModelRenderer knifehandle;
   ModelRenderer knife;
   ModelRenderer knife1;
   ModelRenderer knife2;
   ModelRenderer knife3;


   public ModelJeff() {
	  super();
	  super.textureWidth = 64;
	  super.textureHeight = 64;
	  this.head = new ModelRenderer(this, 0, 0);
	  this.head.addBox(-5.0F, -10.0F, -4.0F, 10, 10, 8);
	  this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
	  this.head.setTextureSize(64, 64);
	  this.head.mirror = true;
	  this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
	  this.body = new ModelRenderer(this, 16, 36);
	  this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 14, 4);
	  this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
	  this.body.setTextureSize(64, 64);
	  this.body.mirror = true;
	  this.setRotation(this.body, 0.2443461F, 0.0F, 0.0F);
	  this.rightarm = new ModelRenderer(this, 43, 19);
	  this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 3, 12, 3);
	  this.rightarm.setRotationPoint(-4.0F, 2.0F, 0.0F);
	  this.rightarm.setTextureSize(64, 64);
	  this.rightarm.mirror = true;
	  this.setRotation(this.rightarm, -1.308997F, 0.0F, 0.0F);
	  this.leftarm = new ModelRenderer(this, 43, 19);
	  this.leftarm.addBox(0.0F, -2.0F, -2.0F, 3, 12, 3);
	  this.leftarm.setRotationPoint(4.0F, 2.0F, 0.0F);
	  this.leftarm.setTextureSize(64, 64);
	  this.leftarm.mirror = true;
	  this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
	  this.rightleg = new ModelRenderer(this, 0, 19);
	  this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4);
	  this.rightleg.setRotationPoint(-2.0F, 14.0F, 3.0F);
	  this.rightleg.setTextureSize(64, 64);
	  this.rightleg.mirror = true;
	  this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
	  this.leftleg = new ModelRenderer(this, 0, 19);
	  this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4);
	  this.leftleg.setRotationPoint(2.0F, 14.0F, 3.0F);
	  this.leftleg.setTextureSize(64, 64);
	  this.leftleg.mirror = true;
	  this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
	  this.knifehandle = new ModelRenderer(this, 37, 47);
	  this.knifehandle.addBox(-2.0F, 8.0F, -9.0F, 1, 2, 12);
	  this.knifehandle.setRotationPoint(-4.0F, 2.0F, 0.0F);
	  this.knifehandle.setTextureSize(64, 64);
	  this.knifehandle.mirror = true;
	  this.setRotation(this.knifehandle, -1.308997F, 0.0F, 0.0F);
	  this.knife = new ModelRenderer(this, 43, 11);
	  this.knife.addBox(-2.0F, 10.0F, -8.0F, 1, 2, 5);
	  this.knife.setRotationPoint(-4.0F, 2.0F, 0.0F);
	  this.knife.setTextureSize(64, 64);
	  this.knife.mirror = true;
	  this.setRotation(this.knife, -1.308997F, 0.0F, 0.0F);
	  this.knife1 = new ModelRenderer(this, 43, 6);
	  this.knife1.addBox(-2.0F, 12.0F, -7.0F, 1, 1, 3);
	  this.knife1.setRotationPoint(-4.0F, 2.0F, 0.0F);
	  this.knife1.setTextureSize(64, 64);
	  this.knife1.mirror = true;
	  this.setRotation(this.knife1, -1.308997F, 0.0F, 0.0F);
	  this.knife2 = new ModelRenderer(this, 56, 15);
	  this.knife2.addBox(-2.0F, 10.0F, -3.0F, 1, 1, 1);
	  this.knife2.setRotationPoint(-4.0F, 2.0F, 0.0F);
	  this.knife2.setTextureSize(64, 64);
	  this.knife2.mirror = true;
	  this.setRotation(this.knife2, -1.308997F, 0.0F, 0.0F);
	  this.knife3 = new ModelRenderer(this, 52, 8);
	  this.knife3.addBox(-2.0F, 10.0F, -9.0F, 1, 1, 1);
	  this.knife3.setRotationPoint(-4.0F, 2.0F, 0.0F);
	  this.knife3.setTextureSize(64, 64);
	  this.knife3.mirror = true;
	  this.setRotation(this.knife3, -1.308997F, 0.0F, 0.0F);
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
	  this.knifehandle.render(f5);
	  this.knife.render(f5);
	  this.knife1.render(f5);
	  this.knife2.render(f5);
	  this.knife3.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
	  model.rotateAngleX = x;
	  model.rotateAngleY = y;
	  model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
	  this.head.rotateAngleY = par4 / 57.295776F;
	  this.head.rotateAngleX = par5 / 57.295776F;
	  this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
	  this.leftarm.rotateAngleZ = 0.0F;
	  this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	  this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
	  this.rightleg.rotateAngleY = 0.0F;
	  this.leftleg.rotateAngleY = 0.0F;
   }
}
