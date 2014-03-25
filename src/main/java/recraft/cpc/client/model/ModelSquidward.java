package recraft.cpc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSquidward extends ModelBase {

   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer rightarm;
   ModelRenderer leftarm;
   ModelRenderer rightleg;
   ModelRenderer leftleg;
   ModelRenderer Thing;
   ModelRenderer mouth;
   ModelRenderer Nose;
   ModelRenderer neck;


   public ModelSquidward() {
	  super();
	  super.textureWidth = 64;
	  super.textureHeight = 64;
	  this.head = new ModelRenderer(this, 0, 0);
	  this.head.addBox(-5.0F, -15.0F, -4.0F, 10, 10, 8);
	  this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
	  this.head.setTextureSize(64, 64);
	  this.head.mirror = true;
	  this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
	  this.body = new ModelRenderer(this, 16, 38);
	  this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 19, 6);
	  this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
	  this.body.setTextureSize(64, 64);
	  this.body.mirror = true;
	  this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
	  this.rightarm = new ModelRenderer(this, 40, 16);
	  this.rightarm.addBox(-3.0F, -1.0F, -1.0F, 4, 8, 4);
	  this.rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
	  this.rightarm.setTextureSize(64, 64);
	  this.rightarm.mirror = true;
	  this.setRotation(this.rightarm, -0.8203047F, 0.0F, 0.0F);
	  this.leftarm = new ModelRenderer(this, 40, 16);
	  this.leftarm.addBox(-1.0F, -1.0F, -1.0F, 4, 8, 4);
	  this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
	  this.leftarm.setTextureSize(64, 64);
	  this.leftarm.mirror = true;
	  this.setRotation(this.leftarm, -0.8203047F, 0.0F, 0.0F);
	  this.rightleg = new ModelRenderer(this, 0, 19);
	  this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4);
	  this.rightleg.setRotationPoint(-2.0F, 19.0F, 0.0F);
	  this.rightleg.setTextureSize(64, 64);
	  this.rightleg.mirror = true;
	  this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
	  this.leftleg = new ModelRenderer(this, 0, 19);
	  this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4);
	  this.leftleg.setRotationPoint(2.0F, 19.0F, 0.0F);
	  this.leftleg.setTextureSize(64, 64);
	  this.leftleg.mirror = true;
	  this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
	  this.Thing = new ModelRenderer(this, 39, 29);
	  this.Thing.addBox(-4.0F, 4.5F, 0.0F, 8, 4, 4);
	  this.Thing.setRotationPoint(0.0F, 0.0F, 0.0F);
	  this.Thing.setTextureSize(64, 64);
	  this.Thing.mirror = true;
	  this.setRotation(this.Thing, -0.7679449F, 0.0F, 0.0F);
	  this.mouth = new ModelRenderer(this, 43, 9);
	  this.mouth.addBox(-4.0F, -5.0F, -4.0F, 8, 4, 2);
	  this.mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
	  this.mouth.setTextureSize(64, 64);
	  this.mouth.mirror = true;
	  this.setRotation(this.mouth, 0.0F, 0.0F, 0.0F);
	  this.Nose = new ModelRenderer(this, 37, 1);
	  this.Nose.addBox(-1.0F, -7.0F, -5.0F, 2, 7, 1);
	  this.Nose.setRotationPoint(0.0F, 0.0F, 0.0F);
	  this.Nose.setTextureSize(64, 64);
	  this.Nose.mirror = true;
	  this.setRotation(this.Nose, 0.0F, 0.0F, 0.0F);
	  this.neck = new ModelRenderer(this, 19, 20);
	  this.neck.addBox(-1.0F, -5.0F, 0.0F, 2, 5, 2);
	  this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
	  this.neck.setTextureSize(64, 64);
	  this.neck.mirror = true;
	  this.setRotation(this.neck, 0.0F, 0.0F, 0.0F);
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
	  this.Thing.render(f5);
	  this.mouth.render(f5);
	  this.Nose.render(f5);
	  this.neck.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
	  model.rotateAngleX = x;
	  model.rotateAngleY = y;
	  model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
	  this.head.rotateAngleY = par4 / 57.295776F;
	  this.head.rotateAngleX = par5 / 57.295776F;
	  this.Nose.rotateAngleY = par4 / 57.295776F;
	  this.Nose.rotateAngleX = par5 / 57.295776F;
	  this.mouth.rotateAngleY = par4 / 57.295776F;
	  this.mouth.rotateAngleX = par5 / 57.295776F;
	  this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	  this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
	  this.rightleg.rotateAngleY = 0.0F;
	  this.leftleg.rotateAngleY = 0.0F;
   }
}
