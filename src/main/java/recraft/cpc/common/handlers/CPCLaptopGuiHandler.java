package recraft.cpc.common.handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import recraft.cpc.client.gui.inventory.GuiLaptop;
import recraft.cpc.common.inventory.ContainerLaptop;
import recraft.cpc.common.tileentity.TileEntityLaptop;

public class CPCLaptopGuiHandler implements IGuiHandler {
	@Override
    public Object getServerGuiElement(int id, EntityPlayer player,
                                      World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityLaptop) {
			return new ContainerLaptop(player.inventory, (TileEntityLaptop) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player,
                                      World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityLaptop) {
			return new GuiLaptop(player.inventory, (TileEntityLaptop) tileEntity);
		}
		return null;
	}
}
