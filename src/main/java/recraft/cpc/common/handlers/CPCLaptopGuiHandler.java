package recraft.cpc.common.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import recraft.cpc.client.gui.inventory.GuiLaptop;
import recraft.cpc.common.inventory.ContainerLaptop;
import recraft.cpc.common.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.network.IGuiHandler;

public class CPCLaptopGuiHandler implements IGuiHandler
{
	//returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityLaptop) {
			return new ContainerLaptop(player.inventory, (TileEntityLaptop) tileEntity);
		}
		return null;
	}

	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityLaptop) {
			return new GuiLaptop(player.inventory, (TileEntityLaptop) tileEntity);
		}
		return null;
	}
}