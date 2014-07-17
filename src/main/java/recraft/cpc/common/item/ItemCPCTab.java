package recraft.cpc.common.item;

import net.minecraft.item.Item;

public class ItemCPCTab extends Item {
    public ItemCPCTab() {
        this.maxStackSize = 1;
        this.setMaxDamage(0);
        this.setTextureName("cpc:tabCPC");
        this.setUnlocalizedName("horror");
    }

    public boolean requiresMultipleRenderPasses() {
        return false;
    }

    public boolean isFull3D() {
        return false;
    }

}
