package recraft.cpc.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import recraft.cpc.CPC;

public class ItemCPCArmorBaby extends ItemArmor {
	public int textureID;

	public ItemCPCArmorBaby(ArmorMaterial par1EnumArmorMaterial, int par2RenderIndex, int par3ArmorType) {
		super(par1EnumArmorMaterial, par2RenderIndex, par3ArmorType);
		textureID = par3ArmorType;
		this.setCreativeTab(CPC.tabCPC);
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return false;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		String textureLoc = "cpc:textures/models/armor/baby_layer_";
		return this.armorType == 2 ? (textureLoc + "2.png") : (textureLoc + "1.png");
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		if (textureID == 0)      { itemIcon = iconRegister.registerIcon("cpc:bonnet"); }
		else if (textureID == 1) { itemIcon = iconRegister.registerIcon("cpc:bib"); }
		else if (textureID == 2) { itemIcon = iconRegister.registerIcon("cpc:diaper");}
		else if (textureID == 3) { itemIcon = iconRegister.registerIcon("cpc:booties");}
		else { itemIcon = iconRegister.registerIcon("cpc:horror"); }
	}
}