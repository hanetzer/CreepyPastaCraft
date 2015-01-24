package com.teamfear.creepypastacraft.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.CommandEvent;

import java.util.Arrays;

public class CPCSmileyChatHandler
{
	public String[] idiftl = { "I", "did", "it", "for", "the", "lulz"};
	@SubscribeEvent
	public void iDidItForTheLulz(CommandEvent event) {
		if (event.command.getCommandName().equals("tell")) {
			if (event.parameters.length == 7) {
				String[] args = Arrays.copyOfRange(event.parameters, 1, 7);
				if (Arrays.equals(idiftl, args)) {
					System.out.println("Smiley is coming to get you!");
				}
			}
		}
	}
}
