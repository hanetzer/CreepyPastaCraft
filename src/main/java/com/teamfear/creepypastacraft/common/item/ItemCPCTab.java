package com.teamfear.creepypastacraft.common.item;

import net.minecraft.item.Item;

public class ItemCPCTab extends Item {
    public ItemCPCTab() {
        this.setMaxStackSize(1);
        this.setMaxDurability(0);
        this.setTextureName("creepypastacraft:tabCPC");
        this.setUnlocalizedName("horror");
    }

    public boolean requiresMultipleRenderPasses() {
        return false;
    }

    public boolean isFull3D() {
        return false;
    }

}
