package netz.mods.cpc.common.item.archive;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import netz.mods.cpc.CPC;
import netz.mods.cpc.common.entity.monster.CPEntity;
import netz.mods.cpc.common.entity.monster.EntityJack;

public class Archive extends Item {
	public String CPName;
	public Archive(int i) {
		super(i);
		super.maxStackSize = 1;
		this.setMaxDamage(1);
		this.setCreativeTab(CPC.tabCPC);
		this.setTextureName("cpc:archive");
		this.setUnlocalizedName("archive");
	}

	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	public boolean isFull3D() {
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		list.add(StatCollector.translateToLocal("entity.cpc."+this.CPName+".name"));
	}

	public static boolean spawnCreature(World par0World, int par1, double par2, double par4, double par6) {
		CPEntity var8 = new EntityJack(par0World);
		var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
		par0World.spawnEntityInWorld(var8);
		var8.playLivingSound();
		return var8 != null;
	}

}
