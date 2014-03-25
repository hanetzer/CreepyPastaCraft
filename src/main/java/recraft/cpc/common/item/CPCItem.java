package recraft.cpc.common.item;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.EnumHelper;
import recraft.cpc.CPC;
import cpw.mods.fml.common.registry.GameRegistry;

public class CPCItem
{
	public static Item horror, jeffKnife, hiltBlack, pagePlacer, stephano, killKnife, diaper, pasta;
	public static EnumToolMaterial ENUM_TOOL_JEFF, ENUM_TOOL_HILT, ENUM_TOOL_KILL;
	public static EnumArmorMaterial ENUM_ARMOR_BABY;

	public CPCItem()
	{
		super();
	}

	public static void init()
	{
		ENUM_ARMOR_BABY		= EnumHelper.addArmorMaterial("ENUM_ARMOR_BABY", 2, new int[]{110, 160, 150, 130}, 5);
		ENUM_TOOL_JEFF		= EnumHelper.addToolMaterial("ENUM_TOOL_JEFF", 0, -1, 0.0F, 11.0F, 0);
		ENUM_TOOL_HILT		= EnumHelper.addToolMaterial("ENUM_TOOL_HILT", 0, -1, 0.0F, 11.0F, 0);
		ENUM_TOOL_KILL		= EnumHelper.addToolMaterial("ENUM_TOOL_KILL", 0, -1, 0.0F, 0, 0);
		initItems();
		regItems();
		addRecipes();
		genHooks();
	}

	private static void initItems()
	{
		horror		= (new ItemHorror(5000));
		jeffKnife	= (new ItemJeffKnife(5001, ENUM_TOOL_JEFF).setUnlocalizedName("cpc.jeffKnife"));
		hiltBlack	= (new ItemHiltBlack(5002, ENUM_TOOL_HILT));
		stephano	= (new ItemStephano(5005).setTextureName("cpc:stephano"));
		killKnife	= (new ItemKillerKnife(5006, ENUM_TOOL_KILL));
		pasta		= (new ItemPasta(5007, 5, 5.0F, false));
		diaper		= (new ItemArmorBaby(5008, ENUM_ARMOR_BABY, CPC.proxy.addArmor("baby"), 2).setUnlocalizedName("cpc.diaper").setTextureName("cpc:diaper"));
	}

	private static void regItems()
	{
		GameRegistry.registerItem(horror,		"horror");
		GameRegistry.registerItem(jeffKnife,	"jeffKnife");
		GameRegistry.registerItem(hiltBlack,	"hiltBlack");
		GameRegistry.registerItem(stephano,		"stephano");
		GameRegistry.registerItem(killKnife,	"killKnife");
		GameRegistry.registerItem(pasta,		"pasta");
		GameRegistry.registerItem(diaper,		"diaper");
	}
	
	private static void addRecipes()
	{
		GameRegistry.addRecipe((new ItemStack(diaper, 1)), "p p", "ppp", 'p', (new ItemStack(Item.paper, 1)));
		GameRegistry.addRecipe((new ItemStack(pasta, 2)), "w", "b", 'w', (new ItemStack(Item.wheat, 1)), 'b', (new ItemStack(Item.bowlEmpty, 1)));
	}
	
	private static void genHooks()
	{
		ChestGenHooks.addItem("villageBlacksmith", new WeightedRandomChestContent(new ItemStack(hiltBlack, 1), 0, 1, 2));
		EntityVillager.addBlacksmithItem(new MerchantRecipeList(), hiltBlack.itemID, new Random(), 2.0F);
	}
}
