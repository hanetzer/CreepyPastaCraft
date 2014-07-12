package recraft.cpc.common.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;
import recraft.cpc.common.entity.monster.*;
import recraft.cpc.common.entity.passive.EntityJane;
import recraft.cpc.common.entity.passive.EntityStrider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by netz on 7/12/14.
 */
public class PastaRegistry {
	public static Map pastaListStringClass = new HashMap();
	public static Map pastaListClassString = new HashMap();
	public static Map pastaListIDClass     = new HashMap();
	public static Map pastaListClassID     = new HashMap();
	public static Map pastaListStringID    = new HashMap();

	@SuppressWarnings("unchecked")
	public static void registerPasta(Class pastaClass, String pastaName, int pastaID) {
		if (pastaListStringClass.containsKey(pastaName)) {
			throw new IllegalArgumentException("ID is already registered: " + pastaName);
		}
		else if (pastaListIDClass.containsKey(pastaID)) {
			throw new IllegalArgumentException("ID is already registered: " + pastaID);
		}
		else {
			pastaListStringClass.put(pastaClass, pastaName);
			pastaListClassString.put(pastaClass, pastaName);
			pastaListIDClass.put(pastaID, pastaClass);
			pastaListClassID.put(pastaClass, pastaID);
			pastaListStringID.put(pastaName, pastaID);
		}
	}

	public static Entity createEntityByName(String par1PastaName, World par2World) {
		Entity pasta = null;
		try {
			Class klass = (Class) pastaListStringClass.get(par1PastaName);

			if (klass != null) {
				pasta = (Entity)klass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par1PastaName});
			}
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		return pasta;
	}

	public static void init() {
		registerPasta(EntityJeff.class,    "jeff",    1);
		registerPasta(EntityJane.class,    "jane",    2);
		registerPasta(EntityJack.class,    "jack",    3);
		registerPasta(EntityRake.class,    "rake",    4);
		registerPasta(EntitySmile.class,   "smile",   5);
		registerPasta(EntitySeed.class,    "seed",    6);
		registerPasta(EntityMothman.class, "moth",    7);
		registerPasta(EntitySquid.class,   "squid",   8);
		registerPasta(EntityStrider.class, "strider", 9);
	}
}
