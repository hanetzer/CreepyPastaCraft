package com.teamfear.creepypastacraft.common;

import cpw.mods.fml.common.network.IGuiHandler;
import com.teamfear.creepypastacraft.client.gui.inventory.GuiLaptop;
import com.teamfear.creepypastacraft.common.inventory.ContainerLaptop;
import com.teamfear.creepypastacraft.common.tileentity.TileEntityLaptop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler{

    public void init() {}

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player,
                                      World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityLaptop) {
            return new ContainerLaptop(player.inventory, (TileEntityLaptop) tile);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player,
                                      World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityLaptop) {
            return new GuiLaptop(player.inventory, (TileEntityLaptop) tile);
        }
        return null;
    }

    public int addArmor(String armor) {
        return 0;
    }
}
