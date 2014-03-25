package recraft.cpc.config;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.Configuration;

public class CPCConfigEntityEnable
{
	public static Configuration config;
	public static boolean enableJeff,	enableJane, enableJack, enablePewds, enableCry,		enableRake, enableSmile;
	public static boolean enableSlendy,	enableSeed, enableMoth, enableSquid, enableStrider;
	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		boolean var5 = false;

		label71:
		{
			try {
				var5 = true;
				config.load();
				FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[CreepyPastaCraft] Determining enabled entities");
				enableJeff		= config.get("Enable Mobs", "Enable Jeff the Killer",	true).getBoolean(false);
				enableJane		= config.get("Enable Mobs", "Enable Jane the Killer",	true).getBoolean(false);
				enableJack		= config.get("Enable Mobs", "Enable Eyeless Jack",		true).getBoolean(false);
				enablePewds		= config.get("Enable Mobs", "Enable PewDiePie",			true).getBoolean(false);
				enableCry		= config.get("Enable Mobs", "Enable Cryotic",			true).getBoolean(false);
				enableSmile		= config.get("Enable Mobs", "Enable Smile.dog",			true).getBoolean(false);
				enableSlendy	= config.get("Enable Mobs", "Enable Slenderman",		true).getBoolean(false);
				enableSeed		= config.get("Enable Mobs", "Enable The Seed Eater",	true).getBoolean(false);
				enableMoth		= config.get("Enable Mobs", "Enable Mothman",			true).getBoolean(false);
				enableSquid		= config.get("Enable Mobs", "Enable Squidward",			true).getBoolean(false);
				enableStrider	= config.get("Enable Mobs", "Enable The Strider",		true).getBoolean(false);
				enableRake		= config.get("Enable Mobs", "Enable The Rake",			true).getBoolean(false);
				var5 = false;
				break label71;
			} catch (Exception var6) {
				FMLLog.log(Level.SEVERE, var6, "[CreepyPastaCraft] Error loading its configuration", new Object[0]);
				var5 = false;
			} finally {
				if(var5) {
					if(config.hasChanged()) {
						config.save();
					}

				}
			}
			if(config.hasChanged()) {
				config.save();
			}
			return;
		}
		if(config.hasChanged()) {
			config.save();
		}

	}
}
