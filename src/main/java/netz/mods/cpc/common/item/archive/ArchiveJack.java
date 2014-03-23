package netz.mods.cpc.common.item.archive;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import netz.mods.cpc.common.entity.monster.CPEntity;
import netz.mods.cpc.common.entity.monster.EntityJack;


public class ArchiveJack extends Archive {

	public ArchiveJack(int i)
	{
		super(i);
		this.CPName = "jack";
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4x, int par5y, int par6z, int par7, float par8, float par9, float par10)
	{
		if(par3World.isRemote)
		{
			return true;
		}
		else
		{
			int var11 = par3World.getBlockId(par4x, par5y, par6z);
			par4x += Facing.offsetsXForSide[par7];
			par5y += Facing.offsetsYForSide[par7];
			par6z += Facing.offsetsZForSide[par7];
			double var12 = 0.0D;
			if(par7 == 1 && var11 == Block.fence.blockID || var11 == Block.netherFence.blockID)
			{
				var12 = 0.5D;
			}

			if(spawnCreature(par3World, par1ItemStack.getItemDamage(), (double)par4x + 0.5D, (double)par5y + var12, (double)par6z + 0.5D) && !par2EntityPlayer.capabilities.isCreativeMode)
			{
				--par1ItemStack.stackSize;
			}

			MinecraftServer.getServer().worldServers[0].setWorldTime(17000L);
			Minecraft mc = Minecraft.getMinecraft();
			mc.gameSettings.renderDistance = 2;
			return true;
		}
	}

	public static boolean spawnCreature(World par0World, int par1, double par2, double par4, double par6)
	{
		CPEntity var8 = new EntityJack(par0World);
		var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
		par0World.spawnEntityInWorld(var8);
		var8.playLivingSound();
		return var8 != null;
	}

}