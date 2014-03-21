package netz.mods.cpc.config;

import java.io.File;

public class CPCConfig
{
	public static File slenderCfgFile, enableCfgFile;
	
	public CPCConfig()
	{
		super();
	}
	
	public static void init(String configPath)
	{
		//slenderCfgFile	= (new File(configPath + "slender.cfg"));
		enableCfgFile	= (new File(configPath + "pasta.cfg"));
		//CPCConfigID.init(idCfgFile);
		//CPCConfigSlender.init(slenderCfgFile);
		CPCConfigEntityEnable.init(enableCfgFile);
	}
}
