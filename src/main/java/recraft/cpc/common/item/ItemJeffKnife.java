package recraft.cpc.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import recraft.cpc.CPC;

public class ItemJeffKnife extends ItemSword {
	private float weaponDamage;
	private final EnumToolMaterial toolMaterial;

	public ItemJeffKnife(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		this.toolMaterial = par2EnumToolMaterial;
		this.maxStackSize = 1;
		this.weaponDamage = this.toolMaterial.getDamageVsEntity();
		this.setTextureName("cpc:jeffKnife");
		this.setCreativeTab(CPC.tabCPC);
		setUnlocalizedName("cpc.jeffKnife");
	}

	public boolean isFull3D() {
		return true;
	}

	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return par1ItemStack.getItemDamage() == 0?EnumRarity.rare:EnumRarity.epic;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		list.add(StatCollector.translateToLocal(super.getUnlocalizedName()+".desc"));
	}

}
