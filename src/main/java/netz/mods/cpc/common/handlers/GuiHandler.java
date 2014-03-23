package netz.mods.cpc.common.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import netz.mods.cpc.client.gui.inventory.GuiLaptop;
import netz.mods.cpc.common.inventory.ContainerLaptop;
import netz.mods.cpc.common.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	//returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityLaptop)
		{
			return new ContainerLaptop((TileEntityLaptop) tileEntity, player.inventory);
		}
		return null;
	}

	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityLaptop)
		{
			return new GuiLaptop((TileEntityLaptop) tileEntity, player.inventory);
		}
		return null;
	}
}