package recraft.cpc.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import recraft.cpc.CPC;

public class ItemCPCSmileJPG extends Item {
    public ItemCPCSmileJPG() {
        super();
        this.setCreativeTab(CPC.tabCPC);
    }

    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("cpc:horror");
    }
}
