package recraft.cpc.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import recraft.cpc.CPC;

import java.util.List;

public class ItemCPCKnife extends ItemSword {
    public int textureID;

    public ItemCPCKnife(ToolMaterial par1EnumToolMaterial, int par2Texture) {
        super(par1EnumToolMaterial);
        this.textureID = par2Texture;
        this.setCreativeTab(CPC.tabCPC);
    }

    public boolean getIsRepairable(ItemStack itemToRepair,
                                   ItemStack itemToRepairWith) {
        return false;
    }

    public void registerIcons(IIconRegister iconRegister) {
        switch (textureID) {
            case 0:
                itemIcon = iconRegister.registerIcon("cpc:jeffKnife");
                break;
            case 1:
                itemIcon = iconRegister.registerIcon("cpc:hiltBlack");
                break;
            default:
                itemIcon = iconRegister.registerIcon("cpc:horror");
                break;
        }
    }

    public boolean isFull3D() {
        return true;
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void addInformation(ItemStack itemStack, EntityPlayer player,
                               List list, boolean par4) {
        list.add(I18n.format(super.getUnlocalizedName() + ".desc"));
    }

}
