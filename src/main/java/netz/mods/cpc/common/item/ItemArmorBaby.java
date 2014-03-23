package netz.mods.cpc.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import netz.mods.cpc.CPC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArmorBaby extends ItemArmor {
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	public int armorType;

	public ItemArmorBaby(EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par2EnumArmorMaterial, par3, par4);
		this.armorType = par4;
		this.setCreativeTab(CPC.tabCPC);
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		String textureLoc = "cpc:textures/models/armor/baby_layer_";
		return this.armorType == 2 ? (textureLoc + "2.png") : (textureLoc + "1.png");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		icons = new Icon[4];
		icons[0] = iconRegister.registerIcon("cpc:bonnet");
		icons[1] = iconRegister.registerIcon("cpc:bib");
		icons[2] = iconRegister.registerIcon("cpc:diaper");
		icons[3] = iconRegister.registerIcon("cpc:booties");
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return armorType <= 3 ? icons[armorType] : icons[0];
	}

}