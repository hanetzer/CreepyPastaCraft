package com.teamfear.creepypastacraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.teamfear.creepypastacraft.CPC;
import com.teamfear.creepypastacraft.common.entity.projectile.EntityStephano;

public class ItemCPCStephano extends Item {

    public ItemCPCStephano() {
        setMaxStackSize(8);
        setMaxDurability(0);
        setCreativeTab(CPC.tabCPC);
        setUnlocalizedName("com.teamfear.creepypastacraft.stephano");
        setTextureName("com.teamfear.creepypastacraft:stephano");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world,
                                      EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }

        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityStephano(world, player, 1));
        }
        return stack;
    }

}