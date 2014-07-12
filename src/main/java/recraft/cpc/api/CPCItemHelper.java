package recraft.cpc.api;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

/**
 * Created by netz on 7/11/14.
 */
public class CPCItemHelper {
	public static Item.ToolMaterial toolMaterialJeffKnife;
	public static Item.ToolMaterial toolMaterialHiltBlack;
	public static Item.ToolMaterial toolMaterialKillKnife;
	public static ItemArmor.ArmorMaterial armorMaterialBaby;

	public static String getUniqueName(Item item) {
		return GameData.getItemRegistry().getNameForObject(item);
	}
}
