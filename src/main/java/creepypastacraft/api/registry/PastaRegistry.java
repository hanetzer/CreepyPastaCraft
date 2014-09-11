package creepypastacraft.api.registry;

import creepypastacraft.common.entity.monster.*;
import creepypastacraft.common.entity.passive.EntityJane;
import creepypastacraft.common.entity.passive.EntitySquidward;
import creepypastacraft.common.entity.passive.EntityStrider;
import creepypastacraft.init.CPCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class PastaRegistry {
	public static List<ArchiveInfo> pastaList = new ArrayList<>();
	public static void registerPasta(Class pasta, String name)
	{
		pastaList.add(new ArchiveInfo(pasta, name));
	}

	public static void init()
	{
		registerPasta(EntityJeff.class, "cpc.jeff");
		registerPasta(EntityJane.class, "cpc.jane");
		registerPasta(EntityJack.class, "cpc.jack");
		registerPasta(EntityRake.class, "cpc.rake");
		registerPasta(EntitySmileDog.class, "cpc.smile");
		registerPasta(EntitySeed.class, "cpc.seed");
		registerPasta(EntityMothman.class, "cpc.moth");
		registerPasta(EntitySquidward.class, "cpc.squidward");
		registerPasta(EntityStrider.class, "cpc.strider");
	}

	public static class ArchiveInfo
	{
		public final Class pasta;
		public final String name;
		public ArchiveInfo(Class pasta, String name)
		{
			this.pasta = pasta;
			this.name = name;
		}
	}

	public static ItemStack getPrinting(ItemStack stack) {
		ItemStack result = null;
		int rand = new Random().nextInt(pastaList.size()+1);
		if (stack.getItem() == Items.paper) {
			switch (rand) {
				case 0:
					result = new ItemStack(CPCItems.smileJpg);
					break;
				default:
					result = new ItemStack(CPCItems.archive, 1, rand -1);
					break;
			}
		}
		return result;
	}
}
