package recraft.cpc.client.gui.achievement;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import recraft.cpc.init.CPCBlocks;
import recraft.cpc.init.CPCItems;

public class CPCAchievement {
	public static Achievement laptop, noSleep, suicide, rideStrider, mothSwat, creepyPasta, diaper;
	public static AchievementPage achPageCPC;
	static Achievement[] cpcAchList;

	public static void init() {
		laptop          = (new Achievement("achievement.laptop",      "laptop",  9, 9, CPCBlocks.laptop, null)).initIndependentStat().registerStat();
		noSleep         = (new Achievement("achievement.noSleep",     "noSleep", 9, 7, CPCItems.jeffKnife, laptop)).registerStat();
		suicide			= (new Achievement("achievement.suicide",     "suicide", 8, 7, (new ItemStack(CPCItems.archive, 1, 7)), laptop)).registerStat();
		rideStrider		= (new Achievement("achievement.rideStrider", "rideStrider",	10,	7, (new ItemStack(CPCItems.archive, 1, 8)), laptop)).registerStat();
		mothSwat		= (new Achievement("achievement.mothSwat",    "mothSwat",	11,	7, CPCItems.killKnife, laptop)).registerStat();
		creepyPasta		= (new Achievement("achievement.pasta",       "pasta",		8,	9, CPCItems.pasta, null)).initIndependentStat().registerStat();
		diaper			= (new Achievement("achievement.diaper",      "diaper",		10,	9, CPCItems.diaper, null)).initIndependentStat().registerStat();

		cpcAchList = new Achievement[]{laptop, noSleep, suicide, rideStrider, mothSwat, creepyPasta, diaper};
		achPageCPC = new AchievementPage("Creepy Pasta Craft", cpcAchList);
		AchievementPage.registerAchievementPage(achPageCPC);
	}
}
