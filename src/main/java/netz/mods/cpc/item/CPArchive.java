package netz.mods.cpc.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import netz.mods.cpc.item.archive.ArchiveJack;
import netz.mods.cpc.item.archive.ArchiveJane;
import netz.mods.cpc.item.archive.ArchiveJeff;
import netz.mods.cpc.item.archive.ArchiveMoth;
import netz.mods.cpc.item.archive.ArchiveRake;
import netz.mods.cpc.item.archive.ArchiveSeed;
import netz.mods.cpc.item.archive.ArchiveSlend;
import netz.mods.cpc.item.archive.ArchiveSmile;
import netz.mods.cpc.item.archive.ArchiveSquid;
import netz.mods.cpc.item.archive.ArchiveStrid;

public class CPArchive {

	public static Item jack, jane, jeff, moth, rake, slender, smile, squid, strider, seed;

	public CPArchive() {
		super();
	}

	public static void init() {
		initArchives();
		regArchives();
	}
	public static void initArchives() {
		jack	= (new ArchiveJack(6000)).setUnlocalizedName("archive");
		jane	= (new ArchiveJane(6001)).setUnlocalizedName("archive");
		jeff	= (new ArchiveJeff(6002)).setUnlocalizedName("archive");
		moth	= (new ArchiveMoth(6003)).setUnlocalizedName("archive");
		rake	= (new ArchiveRake(6004)).setUnlocalizedName("archive");
		slender	= (new ArchiveSlend(6005)).setUnlocalizedName("archive");
		smile	= (new ArchiveSmile(6006)).setUnlocalizedName("archive");
		squid	= (new ArchiveSquid(6007)).setUnlocalizedName("archive");
		strider	= (new ArchiveStrid(6008)).setUnlocalizedName("archive");
		seed	= (new ArchiveSeed(6009)).setUnlocalizedName("archive");
	}
	public static void regArchives() {
		GameRegistry.registerItem(jack,		"jack");
		GameRegistry.registerItem(jane,		"jane");
		GameRegistry.registerItem(jeff,		"jeff");
		GameRegistry.registerItem(moth,		"moth");
		GameRegistry.registerItem(rake,		"rake");
		GameRegistry.registerItem(slender,	"slender");
		GameRegistry.registerItem(smile,	"smile");
		GameRegistry.registerItem(squid,	"squid");
		GameRegistry.registerItem(strider,	"strider");
		GameRegistry.registerItem(seed,		"seed");
	}
}