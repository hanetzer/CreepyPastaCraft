package recraft.cpc.common.handlers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.logging.Level;
import net.minecraftforge.client.event.sound.PlayStreamingEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler
{
	static String[] recordSoundFiles = new String[]
	{
		"cpc:lavender.ogg"
	};
	static String[] soundFiles = new String[]
	{
		"cpc:mob/pewds/death1.ogg",	"cpc:mob/pewds/death2.ogg",	"cpc:mob/pewds/hurt1.ogg",
		"cpc:mob/pewds/hurt2.ogg",	"cpc:mob/pewds/say1.ogg",	"cpc:mob/pewds/say2.ogg",
		"cpc:mob/pewds/say3.ogg",	"cpc:mob/pewds/scare.ogg", "cpc:mob/jeff/kill1.ogg"
	};


	public SoundHandler() {
		super();
	}

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) {
		String[] arr$ = soundFiles;
		int len$ = arr$.length;

		int i$;
		String recordSoundFile;
		for(i$ = 0; i$ < len$; ++i$) {
			recordSoundFile = arr$[i$];

			try {
				event.manager.soundPoolSounds.addSound(recordSoundFile);
			} catch (Exception var8) {
				FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[CreepyPastaCraft] Failed loading sound file: " + recordSoundFile);
			}
		}

		arr$ = recordSoundFiles;
		len$ = arr$.length;

		for(i$ = 0; i$ < len$; ++i$) {
			recordSoundFile = arr$[i$];

			try {
				event.manager.soundPoolStreaming.addSound(recordSoundFile);
			} catch (Exception var7) {
				FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[CreepyPastaCraft] Failed loading sound file: " + recordSoundFile);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onPlayStreaming(PlayStreamingEvent event)
	{
		float var10003;
		float var10002;
		if(event.name == "lavender")
		{
			var10002 = event.x + 0.5F;
			var10003 = event.y + 0.5F;
			FMLClientHandler.instance().getClient().sndManager.playStreaming("cpc:lavender", var10002, var10003, event.z + 0.5F);
		}
	}
}
