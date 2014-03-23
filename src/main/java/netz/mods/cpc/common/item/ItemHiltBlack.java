package netz.mods.cpc.common.item;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import netz.mods.cpc.CPC;
import cpw.mods.fml.client.FMLClientHandler;

public class ItemHiltBlack extends ItemSword {
	Minecraft mc = Minecraft.getMinecraft();
	int previousRenderSetting;
	public int HOABHeartDelay;


	public ItemHiltBlack(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		this.previousRenderSetting = this.mc.gameSettings.renderDistance;
		this.HOABHeartDelay = 100;
		this.maxStackSize =1;
		this.setUnlocalizedName("cpc.hiltBlack");
		this.setTextureName("cpc:hiltBlack");
		this.setCreativeTab(CPC.tabCPC);
	}

	public boolean isFull3D() {
		return true;
	}

	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		Minecraft mc = FMLClientHandler.instance().getClient();
		EntityClientPlayerMP ep = mc.thePlayer;
		ItemStack par1 = this.mc.thePlayer.getHeldItem();
		if(ep != null && par1 != null && par1.itemID == super.itemID) {
		par2World.spawnParticle("reddust", par3Entity.posX, par3Entity.posY - 0.4D, par3Entity.posZ, 0.0D, 0.0D, 0.0D);
		if((new Random()).nextInt(100) < 25) {
			par2World.spawnParticle("smoke", par3Entity.posX, par3Entity.posY - 0.4D, par3Entity.posZ, 0.0D, 0.0D, 0.0D);
		}

		mc.gameSettings.renderDistance = 2;
		} else {
			mc.gameSettings.renderDistance = this.previousRenderSetting;
		}

		if(mc.thePlayer != null && par1 != null && Item.itemsList[par1.itemID] instanceof ItemHiltBlack && this.HOABHeartDelay > 0 && !ep.capabilities.isCreativeMode) {
			--this.HOABHeartDelay;
		}

		if(!ep.capabilities.isCreativeMode && this.HOABHeartDelay <= 10) {
			ep.addPotionEffect(new PotionEffect(Potion.wither.id, 50, 1));
			ep.setHealth(ep.getHealth() - 2);
			this.HOABHeartDelay = 200;
		}

		if(ep.getActivePotionEffect(Potion.wither) != null && ep.getActivePotionEffect(Potion.wither).getDuration() <= 1) {
			ep.removePotionEffect(Potion.wither.id);
		}

	}

}
