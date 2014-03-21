package netz.mods.cpc.item.crafting;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@SuppressWarnings({ "rawtypes", "unchecked" } )
public class LaptopRecipes {

	private static final LaptopRecipes smeltingBase = new LaptopRecipes();
	private Map smeltingList = new HashMap();
	private Map experienceList = new HashMap();

	public static final LaptopRecipes smelting() {
		return smeltingBase;
	}

	private LaptopRecipes() {
		super();
		this.addSmelting(Item.paper.itemID, new ItemStack(Item.ingotIron), 0.7F);
	}

	public void addSmelting(int par1, ItemStack par2ItemStack, float par3) {
		this.smeltingList.put(Integer.valueOf(par1), par2ItemStack);
		this.experienceList.put(Integer.valueOf(par2ItemStack.itemID), Float.valueOf(par3));
	}

	public ItemStack getSmeltingResult(int par1) {
		return (ItemStack)this.smeltingList.get(Integer.valueOf(par1));
	}

	public Map getSmeltingList() {
		return this.smeltingList;
	}

	public float getExperience(int par1) {
		return this.experienceList.containsKey(Integer.valueOf(par1))?((Float)this.experienceList.get(Integer.valueOf(par1))).floatValue():0.0F;
	}

}
