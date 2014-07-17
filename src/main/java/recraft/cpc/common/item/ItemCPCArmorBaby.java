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
        switch (this.armorType) {
            case 2:
                return (textureLoc + "2.png");
            default:
                return (textureLoc + "1.png");
        }
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        switch (textureID) {
            case 0:
                itemIcon = iconRegister.registerIcon("cpc:bonnet");
                break;
            case 1:
                itemIcon = iconRegister.registerIcon("cpc:bib");
                break;
            case 2:
                itemIcon = iconRegister.registerIcon("cpc:diaper");
                break;
            case 3:
                itemIcon = iconRegister.registerIcon("cpc:booties");
                break;
            default:
                itemIcon = iconRegister.registerIcon("cpc:horror");
                break;
        }
    }
}