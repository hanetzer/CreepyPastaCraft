package netz.mods.cpc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLaptop extends ModelBase
{
	public ModelRenderer lid = new ModelRenderer(this, 0, 19).setTextureSize(64, 32);
	public ModelRenderer base = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);

	public ModelLaptop()
	{
		this.lid.addBox(-7.5F, 0F, -12F, 15, 1, 12);
		this.base.rotationPointX = 0.0F;
		this.base.rotationPointY = 23.0F;
		this.base.rotationPointZ = 6.0F;
		
		this.base.addBox(-7.5F, -1F, -12F, 15, 1, 12);
		this.lid.rotationPointX = 0.0F;
		this.lid.rotationPointY = 23.0F;
		this.lid.rotationPointZ = 6.0F;
	}

	public void renderAll()
	{
		this.base.render(0.0625F);
		this.lid.render(0.0625F);
	}
}