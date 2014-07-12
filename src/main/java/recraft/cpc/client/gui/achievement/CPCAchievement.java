package recraft.cpc.client.gui.achievement;

import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import recraft.cpc.common.block.CPCBlock;
import recraft.cpc.common.item.CPArchive;
import recraft.cpc.core.CPCItem;
import recraft.cpc.init.CPCBlocks;
import recraft.cpc.init.CPCItems;

public class CPCAchievement
{
	public static Achievement computer, noSleep, suicide, rideStrider, mothSwat, creepyPasta, diaper;
	public static AchievementPage achPageCPC;
	static Achievement[] cpcAchList;

	public CPCAchievement()
	{
		super();
	}

	public static void init()
	{
		computer		= (new Achievement(1000, "computer",	9,	9, Item.getItemFromBlock(CPCBlocks.laptop), (Achievement)null)).setIndependent().registerAchievement();
		noSleep			= (new Achievement(1001, "noSleep",		9,	7, CPCItems.jeffKnife, computer)).registerAchievement();
		suicide			= (new Achievement(1002, "suicide",		8,	7, CPArchive.squid, computer)).registerAchievement();
		rideStrider		= (new Achievement(1003, "rideStrider",	10,	7, CPArchive.strider, computer)).registerAchievement();
		mothSwat		= (new Achievement(1004, "mothSwat",	11,	7, CPCItems.killKnife, computer)).registerAchievement();
		creepyPasta		= (new Achievement(1005, "pasta",		8,	9, CPCItems.pasta, (Achievement)null)).setIndependent().registerAchievement();
		diaper			= (new Achievement(1006, "diaper",		10,	9, CPCItems.diaper, (Achievement)null)).setIndependent().registerAchievement();

		cpcAchList = new Achievement[]{computer, noSleep, suicide, rideStrider, mothSwat, creepyPasta, diaper};
		achPageCPC = new AchievementPage("Creepy Pasta Craft", cpcAchList);
		AchievementPage.registerAchievementPage(achPageCPC);
	}
}
