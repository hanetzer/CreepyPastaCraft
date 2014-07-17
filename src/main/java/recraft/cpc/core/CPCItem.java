package recraft.cpc.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import recraft.cpc.CPC;
import recraft.cpc.api.CPCItemHelper;
import recraft.cpc.common.item.*;

import java.util.Random;

import static recraft.cpc.init.CPCItems.*;

public class CPCItem {

    public static void init() {
        initMaterials();
        regItems();
        addRecipes();
        genHooks();
    }

    private static void initMaterials() {
        CPCItemHelper.toolMaterialJeffKnife = EnumHelper.addToolMaterial("JEFF", 0, -1, 0.0F, 11.0F, 0);
        CPCItemHelper.toolMaterialHiltBlack = EnumHelper.addToolMaterial("HILT", 0, -1, 0.0F, 11.0F, 0);
        CPCItemHelper.armorMaterialBaby = EnumHelper.addArmorMaterial("BABY", 5, new int[]{1, 3, 2, 1}, 0);
    }

    private static void regItems() {
        horror = registerItem(new ItemCPCTab().setUnlocalizedName("cpc:horror"));
        jeffKnife = registerItem(new ItemCPCKnife(CPCItemHelper.toolMaterialJeffKnife, 0).setUnlocalizedName("cpc:jeffKnife"));
        hiltBlack = registerItem(new ItemCPCKnife(CPCItemHelper.toolMaterialHiltBlack, 1).setUnlocalizedName("cpc:hiltBlack"));
        diaper = registerItem(new ItemCPCArmorBaby(CPCItemHelper.armorMaterialBaby, CPC.proxy.addArmor("baby"), 2).setUnlocalizedName("cpc:diaper"));
        stephano = registerItem(new ItemCPCStephano().setUnlocalizedName("cpc:stephano"));
        pasta = registerItem(new ItemCPCFood(5, 5.0F, false, 0).setUnlocalizedName("cpc:pasta"));
        record_lavender = registerItem(new ItemCPCRecord("lavender").setUnlocalizedName("cpc:record_lavender"));
        archive = registerItem(new ItemCPCArchive().setUnlocalizedName("cpc:archive"));
        smileJpg = registerItem(new ItemCPCSmileJPG().setUnlocalizedName("cpc:smile.jpg"));
    }

    private static void addRecipes() {
        GameRegistry.addRecipe((new ItemStack(diaper, 1)), "p p", "ppp", 'p', (new ItemStack(Items.paper, 1)));
        GameRegistry.addRecipe((new ItemStack(archive, 1, 4)), "a", 'a', (new ItemStack(smileJpg, 1)));
        GameRegistry.addRecipe((new ItemStack(pasta, 1)), "w", "b", 'w', (new ItemStack(Items.wheat, 1)), 'b', (new ItemStack(Items.bowl, 1)));
    }

    private static void genHooks() {
        ChestGenHooks.addItem("villageBlacksmith", new WeightedRandomChestContent(new ItemStack(hiltBlack, 1), 0, 1, 2));
        EntityVillager.func_146091_a(new MerchantRecipeList(), hiltBlack, new Random(), 2.0F);
    }

    public static Item registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.cpc:", ""));
        return item;
    }
}
