package creepypastacraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMothman extends ModelBase {

    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer wingleft;
    ModelRenderer wingleft2;
    ModelRenderer wingleft0;
    ModelRenderer wingleft01;
    ModelRenderer wingright;
    ModelRenderer wingright2;
    ModelRenderer wingright0;
    ModelRenderer wingright01;


    public ModelMothman() {
        super();
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -6.0F, -3.0F, 8, 6, 5);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 3);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 40, 13);
        this.rightarm.addBox(-2.0F, -2.0F, -2.0F, 3, 14, 3);
        this.rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightarm.setTextureSize(64, 64);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 40, 13);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 3, 14, 3);
        this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftarm.setTextureSize(64, 64);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
        this.rightleg = new ModelRenderer(this, 0, 16);
        this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 3, 12, 3);
        this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rightleg.setTextureSize(64, 64);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        this.leftleg = new ModelRenderer(this, 0, 16);
        this.leftleg.addBox(-1.0F, 0.0F, -2.0F, 3, 12, 3);
        this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leftleg.setTextureSize(64, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        this.wingleft = new ModelRenderer(this, 27, 1);
        this.wingleft.addBox(-15.0F, 2.0F, -2.0F, 12, 3, 3);
        this.wingleft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingleft.setTextureSize(64, 64);
        this.wingleft.mirror = true;
        this.setRotation(this.wingleft, 0.0F, 0.0F, 0.8028515F);
        this.wingleft2 = new ModelRenderer(this, 51, 30);
        this.wingleft2.addBox(-15.5F, -3.0F, -2.0F, 3, 30, 3);
        this.wingleft2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingleft2.setTextureSize(64, 64);
        this.wingleft2.mirror = true;
        this.setRotation(this.wingleft2, 0.0F, 0.0F, 0.3141593F);
        this.wingleft0 = new ModelRenderer(this, 36, 35);
        this.wingleft0.addBox(-12.5F, -1.0F, -1.0F, 6, 27, 1);
        this.wingleft0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingleft0.setTextureSize(64, 64);
        this.wingleft0.mirror = true;
        this.setRotation(this.wingleft0, 0.0F, 0.0F, 0.3141593F);
        this.wingleft01 = new ModelRenderer(this, 20, 39);
        this.wingleft01.addBox(-12.5F, 0.5F, -1.0F, 6, 23, 1);
        this.wingleft01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingleft01.setTextureSize(64, 64);
        this.wingleft01.mirror = true;
        this.setRotation(this.wingleft01, 0.0F, 0.0F, 0.0698132F);
        this.wingright = new ModelRenderer(this, 27, 1);
        this.wingright.addBox(3.0F, 3.0F, -2.0F, 12, 3, 3);
        this.wingright.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingright.setTextureSize(64, 64);
        this.wingright.mirror = true;
        this.setRotation(this.wingright, 0.0F, 0.0F, -0.8028515F);
        this.wingright2 = new ModelRenderer(this, 51, 30);
        this.wingright2.addBox(13.0F, -2.0F, -2.0F, 3, 30, 3);
        this.wingright2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingright2.setTextureSize(64, 64);
        this.wingright2.mirror = true;
        this.setRotation(this.wingright2, 0.0F, 0.0F, -0.3141593F);
        this.wingright0 = new ModelRenderer(this, 36, 35);
        this.wingright0.addBox(7.0F, 0.0F, -1.0F, 6, 27, 1);
        this.wingright0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingright0.setTextureSize(64, 64);
        this.wingright0.mirror = true;
        this.setRotation(this.wingright0, 0.0F, 0.0F, -0.3141593F);
        this.wingright01 = new ModelRenderer(this, 1, 37);
        this.wingright01.addBox(6.0F, -0.5F, -1.0F, 8, 25, 1);
        this.wingright01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingright01.setTextureSize(64, 64);
        this.wingright01.mirror = true;
        this.setRotation(this.wingright01, 0.0F, 0.0F, -0.0698132F);
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
        this.wingleft.render(f5);
        this.wingleft2.render(f5);
        this.wingleft0.render(f5);
        this.wingleft01.render(f5);
        this.wingright.render(f5);
        this.wingright2.render(f5);
        this.wingright0.render(f5);
        this.wingright01.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.head.rotateAngleY = par4 / 57.295776F;
        this.head.rotateAngleX = par5 / 57.295776F;
        this.wingright.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.wingleft.rotateAngleY = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.wingright.rotateAngleZ = 0.0F;
        this.wingleft.rotateAngleZ = 0.0F;
        this.wingright0.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.wingleft0.rotateAngleY = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.wingright0.rotateAngleZ = 0.0F;
        this.wingleft0.rotateAngleZ = 0.0F;
        this.wingright01.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.wingleft01.rotateAngleY = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.wingright01.rotateAngleZ = 0.0F;
        this.wingleft01.rotateAngleZ = 0.0F;
        this.wingright2.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.wingleft2.rotateAngleY = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.wingright2.rotateAngleZ = 0.0F;
        this.wingleft2.rotateAngleZ = 0.0F;
    }
}
