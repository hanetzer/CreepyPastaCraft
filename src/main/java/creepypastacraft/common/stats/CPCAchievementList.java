package creepypastacraft.common.stats;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import creepypastacraft.init.CPCBlocks;
import creepypastacraft.init.CPCItems;

public class CPCAchievementList {
    public static Achievement laptop, noSleep, suicide, rideStrider, creepyPasta, diaper;
    public static AchievementPage achPageCPC;
    static Achievement[] cpcAchList;

    public static void init() {
        laptop = (new Achievement("achievement.cpc:laptop", "cpc:laptop", 0, 0, CPCBlocks.laptop, null)).initIndependentStat().registerStat();
        noSleep = (new Achievement("achievement.cpc:noSleep", "cpc:noSleep", 0, -2, CPCItems.jeffKnife, laptop)).registerStat();
        suicide = (new Achievement("achievement.cpc:suicide", "cpc:suicide", -1, -2, CPCItems.archive, laptop)).registerStat();
        rideStrider = (new Achievement("achievement.cpc:rideStrider", "cpc:rideStrider", 1, -2, CPCItems.archive, laptop)).registerStat();
        creepyPasta = (new Achievement("achievement.cpc:pasta", "cpc:pasta", -1, 0, CPCItems.pasta, null)).initIndependentStat().registerStat();
        diaper = (new Achievement("achievement.cpc:diaper", "cpc:diaper", 1, 0, CPCItems.diaper, null)).initIndependentStat().registerStat();

        cpcAchList = new Achievement[]{laptop, noSleep, suicide, rideStrider, creepyPasta, diaper};
        achPageCPC = new AchievementPage("Creepy Pasta Craft", cpcAchList);
        AchievementPage.registerAchievementPage(achPageCPC);
    }
}
