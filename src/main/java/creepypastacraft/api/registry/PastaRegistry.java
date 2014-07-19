package creepypastacraft.api.registry;

import creepypastacraft.common.entity.passive.EntitySquidward;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import creepypastacraft.common.entity.monster.*;
import creepypastacraft.common.entity.passive.EntityJane;
import creepypastacraft.common.entity.passive.EntityStrider;
import creepypastacraft.init.CPCItems;

import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class PastaRegistry {
    private static final Logger logger = LogManager.getLogger();
    public static Map stringToClass = new HashMap();
    public static Map classToString = new HashMap();
    public static Map idToClass = new HashMap();
    public static Map classToId = new HashMap();
    public static Map stringToID = new HashMap();
    public static HashMap pastaList = new LinkedHashMap();

    public static void registerPasta(Class klazz, String name, int ID) {
        if (stringToClass.containsKey(name)) {
            throw new IllegalArgumentException("ID already registered: " + name);
        } else if (idToClass.containsKey(ID)) {
            throw new IllegalArgumentException("ID already registered: " + ID);
        } else {
            stringToClass.put(name, klazz);
            classToString.put(klazz, name);
            idToClass.put(ID, klazz);
            classToId.put(klazz, ID);
            stringToID.put(name, ID);
            pastaList.put(ID, new PastaInfo(ID));
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static Entity createEntityByName(String name, World world) {
        Entity pasta = null;
        try {
            Class klazz = (Class) stringToClass.get(name);

            if (klazz != null) {
                pasta = (Entity) klazz.getConstructor(new Class[]{
                        World.class
                }).newInstance(name);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return pasta;
    }

    public static Entity createEntityByID(int ID, World world) {
        Entity entity = null;

        try {
            Class klazz = getClassFromID(ID);

            if (klazz != null) {
                entity = (Entity) klazz.getConstructor(new Class[]{
                        World.class
                }).newInstance(world);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (entity == null) {
            logger.warn("Skipping Entity with id " + ID);
        }
        return entity;
    }

    /**
     * gets the entityID of a specific entity
     */
    public static int getEntityID(Entity entity) {
        Class klazz = entity.getClass();
        if (classToId.containsKey(klazz)) {
            return ((Integer) classToId.get(klazz));
        } else {
            return 0;
        }
    }

    /**
     * Return the class assigned to this entity ID.
     */
    public static Class getClassFromID(int ID) {
        return (Class) idToClass.get(ID);
    }

    /**
     * Gets the string representation of a specific entity.
     */
    public static String getEntityString(Entity entity) {
        return (String) classToString.get(entity.getClass());
    }

    /**
     * Finds the class using IDtoClassMapping and classToStringMapping
     */
    public static String getStringFromID(int entityID) {
        Class klazz = getClassFromID(entityID);
        if (klazz != null) {
            return (String) classToString.get(klazz);
        } else {
            return null;
        }
    }

    public static void func_151514_a() {
    }

    public static Set func_151515_b() {
        return Collections.unmodifiableSet(stringToID.keySet());
    }

    public static void init() {
        registerPasta(EntityJeff.class, "cpc:jeff", 1);
        registerPasta(EntityJane.class, "cpc:jane", 2);
        registerPasta(EntityJack.class, "cpc:jack", 3);
        registerPasta(EntityRake.class, "cpc:rake", 4);
        registerPasta(EntitySmileDog.class, "cpc:smile", 5);
        registerPasta(EntitySeed.class, "cpc:seed", 6);
        registerPasta(EntityMothman.class, "cpc:moth", 7);
        registerPasta(EntitySquidward.class, "cpc:squidward", 8);
        registerPasta(EntityStrider.class, "cpc:strider", 9);
    }

    public static class PastaInfo {
        public final int spawnedID;

        public PastaInfo(int ID) {
            this.spawnedID = ID;
        }
    }

    public static ItemStack getPrinting(ItemStack stack) {
        ItemStack result = null;
        int rand = randInt(0, stringToID.size());
        if (stack.getItem() == Items.paper) {
            switch (rand) {
                case 0:
                    result = new ItemStack(CPCItems.smileJpg);
                    break;
                default:
                    result = new ItemStack(CPCItems.archive, 1, rand);
                    break;
            }
        }
        return result;
    }
}
