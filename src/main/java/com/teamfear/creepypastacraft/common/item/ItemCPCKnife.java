package com.teamfear.creepypastacraft.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import com.teamfear.creepypastacraft.CPC;

import java.util.List;

public class ItemCPCKnife extends ItemSword {
    public int textureID;

    public ItemCPCKnife(ToolMaterial material, int type) {
        super(material);
        this.textureID = type;
        this.setCreativeTab(CPC.tabCPC);
    }

    public boolean getIsRepairable(ItemStack itemToRepair,
                                   ItemStack itemToRepairWith) {
        return false;
    }

    public void registerIcons(IIconRegister iconRegister) {
        switch (textureID) {
            case 0:
                itemIcon = iconRegister.registerIcon("creepypastacraft:jeffKnife");
                break;
            case 1:
                itemIcon = iconRegister.registerIcon("creepypastacraft:hiltBlack");
                break;
            default:
                itemIcon = iconRegister.registerIcon("creepypastacraft:horror");
                break;
        }
    }

    public boolean isFull3D() {
        return true;
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add(I18n.format(super.getUnlocalizedName() + ".desc"));
    }

}
