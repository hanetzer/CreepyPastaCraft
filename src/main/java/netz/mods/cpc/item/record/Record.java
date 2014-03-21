package netz.mods.cpc.item.record;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class Record {
	public static Item lavender;
	public Record() {
		super();
	}
	
	public static void init() {
		initRecord();
		regRecord();
	}
   
	public static void initRecord() {
		lavender = (new ItemRecordLavender(1500, "lavender").setUnlocalizedName("record"));
	}
	
	private static void regRecord() {
		GameRegistry.registerItem(lavender, "record.lavender");
	}

}
