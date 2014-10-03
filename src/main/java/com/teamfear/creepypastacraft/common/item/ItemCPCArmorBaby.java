package com.teamfear.creepypastacraft.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import com.teamfear.creepypastacraft.CPC;

public class ItemCPCArmorBaby extends ItemArmor {
    public int textureID;

    public ItemCPCArmorBaby(ArmorMaterial material, int index, int type) {
        super(material, index, type);
        textureID = type;
        this.setCreativeTab(CPC.tabCPC);
    }

    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        return false;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String textureLoc = "com.teamfear.creepypastacraft:textures/models/armor/baby_layer_";
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
                itemIcon = iconRegister.registerIcon("com.teamfear.creepypastacraft:bonnet");
                break;
            case 1:
                itemIcon = iconRegister.registerIcon("com.teamfear.creepypastacraft:bib");
                break;
            case 2:
                itemIcon = iconRegister.registerIcon("com.teamfear.creepypastacraft:diaper");
                break;
            case 3:
                itemIcon = iconRegister.registerIcon("com.teamfear.creepypastacraft:booties");
                break;
            default:
                itemIcon = iconRegister.registerIcon("com.teamfear.creepypastacraft:horror");
                break;
        }
    }
}