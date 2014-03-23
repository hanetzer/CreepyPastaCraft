package netz.mods.cpc.common.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import netz.mods.cpc.CPC;

public class ItemKillerKnife extends ItemSword
{
	private float weaponDamage;
	private final EnumToolMaterial toolMaterial;

	public ItemKillerKnife(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
		this.toolMaterial = par2EnumToolMaterial;
		this.maxStackSize =1;
		this.weaponDamage = this.toolMaterial.getDamageVsEntity();
		this.setCreativeTab(CPC.tabCPC);
		this.setUnlocalizedName("cpc.killKnife");
		this.setTextureName("cpc:killKnife");
	}

	public boolean isFull3D() {
		return true;
	}

	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return par1ItemStack.getItemDamage() == 0?EnumRarity.rare:EnumRarity.epic;
	}

}
