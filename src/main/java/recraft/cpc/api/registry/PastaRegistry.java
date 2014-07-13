package recraft.cpc.api.registry;

import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recraft.cpc.common.entity.monster.*;
import recraft.cpc.common.entity.passive.EntityJane;
import recraft.cpc.common.entity.passive.EntitySquidward;
import recraft.cpc.common.entity.passive.EntityStrider;
import recraft.cpc.init.CPCItems;

import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class PastaRegistry {
	private static final Logger logger = LogManager.getLogger();
	public static Map pastaListStringClass = new HashMap();
	public static Map pastaListClassString = new HashMap();
	public static Map pastaListIDClass     = new HashMap();
	public static Map pastaListClassID     = new HashMap();
	public static Map pastaListStringID    = new HashMap();
	public static HashMap pastaList        = new LinkedHashMap();

	public static void registerPasta(Class pastaClass, String pastaName, int pastaID) {
		if (pastaListStringClass.containsKey(pastaName)) {
			throw new IllegalArgumentException("ID is already registered: " + pastaName);
		}
		else if (pastaListIDClass.containsKey(pastaID)) {
			throw new IllegalArgumentException("ID is already registered: " + pastaID);
		}
		else {
			pastaListStringClass.put(pastaName, pastaClass);
			pastaListClassString.put(pastaClass, pastaName);
			pastaListIDClass.put(pastaID, pastaClass);
			pastaListClassID.put(pastaClass, pastaID);
			pastaListStringID.put(pastaName, pastaID);
			pastaList.put(pastaID, new PastaInfo(pastaID));
		}
	}

	public static Entity createEntityByName(String par1PastaName, World par2World) {
		Entity pasta = null;
		try {
			Class klazz = (Class) pastaListStringClass.get(par1PastaName);

			if (klazz != null) {
				pasta = (Entity)klazz.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par1PastaName});
			}
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		return pasta;
	}
	public static Entity createEntityByID(int par1PastaID, World par2World) {
		Entity entity = null;

		try {
			Class klazz = getClassFromID(par1PastaID);

			if (klazz != null) {
				entity = (Entity)klazz.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par2World});
			}
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		if (entity == null) {
			logger.warn("Skipping Entity with id " + par1PastaID);
		}
		return entity;
	}

	/**
	 * gets the entityID of a specific entity
	 */
	public static int getEntityID(Entity entity) {
		Class klazz = entity.getClass();
		return pastaListClassID.containsKey(klazz) ? ((Integer)pastaListClassID.get(klazz)) : 0;
	}

	/**
	 * Return the class assigned to this entity ID.
	 */
	public static Class getClassFromID(int entityID)
	{
		return (Class)pastaListIDClass.get(entityID);
	}

	/**
	 * Gets the string representation of a specific entity.
	 */
	public static String getEntityString(Entity entity) {
		return (String)pastaListClassString.get(entity.getClass());
	}

	/**
	 * Finds the class using IDtoClassMapping and classToStringMapping
	 */
	public static String getStringFromID(int entityID) {
		Class oclass = getClassFromID(entityID);
		return oclass != null ? (String)pastaListClassString.get(oclass) : null;
	}

	public static void func_151514_a() {}

	public static Set func_151515_b()
	{
		return Collections.unmodifiableSet(pastaListStringID.keySet());
	}

	public static void init() {
		registerPasta(EntityJeff.class,      "cpc:jeff",     0);
		registerPasta(EntityJane.class,      "cpc:jane",     1);
		registerPasta(EntityJack.class,      "cpc:jack",     2);
		registerPasta(EntityRake.class,      "cpc:rake",     3);
		registerPasta(EntitySmile.class,     "cpc:smile",    4);
		registerPasta(EntitySeed.class,      "cpc:seed",     5);
		registerPasta(EntityMothman.class,   "cpc:moth",     6);
		registerPasta(EntitySquidward.class, "cpc:squidward",7);
		registerPasta(EntityStrider.class,   "cpc:strider",  8);
	}

	public static class PastaInfo {
		public final int spawnedID;
		public PastaInfo(int par1int) {
			this.spawnedID = par1int;
		}
	}

	public static class ArchiveRecipes {
		public static ArchiveRecipes printingBase = new ArchiveRecipes();
		public Map archiveList = new HashMap();
		public static ArchiveRecipes printing() {
			return printingBase;
		}
		public ArchiveRecipes() {
			Map myMap = PastaRegistry.pastaListStringID;
			Collection en = myMap.values();
			Object[] array = en.toArray();

			for (int i = 0; i < en.size(); i++) {
				this.archiveList.put(new ItemStack(Items.paper), new ItemStack(CPCItems.archive, 1, i));
			}
		}

		public ItemStack getPrintingResult(ItemStack itemStack) {
			Random rand = new Random();
			int damage = rand.nextInt(0 - pastaListStringID.values().size());
			return new ItemStack(CPCItems.archive, 1, damage);
		}
	}
}
