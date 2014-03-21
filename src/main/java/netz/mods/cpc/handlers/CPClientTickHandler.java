package netz.mods.cpc.handlers;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CPClientTickHandler implements ITickHandler {

	Minecraft mc = FMLClientHandler.instance().getClient();
	EntityPlayer player;
	private boolean[] keyStates;


	public CPClientTickHandler() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public void tickStart(EnumSet type, Object ... tickData) {
		new Random();
		if(type.equals(EnumSet.of(TickType.RENDER))) {
			float ticks = ((Float)tickData[0]).floatValue();
			this.onRenderTickStart(ticks);
		}

	}

	@SuppressWarnings("rawtypes")
	public void tickEnd(EnumSet type, Object ... tickData) {
		if(type.equals(EnumSet.of(TickType.PLAYER))) {
			this.player = (EntityPlayer)tickData[0];
		} else if(type.equals(EnumSet.of(TickType.RENDER))) {
			float ticks = ((Float)tickData[0]).floatValue();
			this.onRenderTickEnd(ticks);
			GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
			if(guiscreen != null && Minecraft.getMinecraft().thePlayer != null) {
				this.onTickInGUI(guiscreen);
			}
		} else if(type.equals(EnumSet.of(TickType.CLIENT)) && Minecraft.getMinecraft().thePlayer != null) {
			this.onTickInGame();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EnumSet ticks() {
		return EnumSet.of(TickType.RENDER, TickType.CLIENT, TickType.PLAYER); //removed , TickType.GUI
	}

	public String getLabel() {
		return null;
	}

	public boolean checkKey(int i) {
		this.keyStates = new boolean[256];
		return Minecraft.getMinecraft().currentScreen != null?false:(Keyboard.isKeyDown(i) != this.keyStates[i]?(this.keyStates[i] = !this.keyStates[i]):false);
	}

	public void onRenderTickStart(float ticks) {}

	public void onRenderTickEnd(float ticks) {
		if(this.mc.currentScreen == null) {
			;
		}

	}

	public void onTickInGUI(GuiScreen gui) {}

	public void onTickInGame() {}
}
