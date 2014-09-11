package creepypastacraft.common.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import creepypastacraft.api.registry.PastaRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import creepypastacraft.CPC;
import creepypastacraft.common.entity.monster.*;
import creepypastacraft.common.entity.monster.EntitySmileDog;
import creepypastacraft.common.entity.passive.*;
import creepypastacraft.common.entity.projectile.EntityStephano;

import static cpw.mods.fml.common.registry.EntityRegistry.*;
import static net.minecraft.world.biome.BiomeGenBase.*;

public class CPCEntity {
    public static EntityStephano stephano;

    public static void init() {
        EntityRegistry.registerModEntity(EntityJeff.class,
				"creepypastacraft.jeff", 2000, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityJeff.class, 5, 1, 1,
				EnumCreatureType.monster, forest, forestHills);

        EntityRegistry.registerModEntity(EntityJane.class,
				"creepypastacraft.jane", 2001, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityJane.class, 5, 1, 1,
				EnumCreatureType.monster, forest, forestHills);

        EntityRegistry.registerGlobalEntityID(EntityPewds.class,
				"creepypastacraft.pewds", findGlobalUniqueEntityId(), 0x35A5C8, 0xD87333);
        EntityRegistry.registerModEntity(EntityPewds.class,
				"creepypastacraft.pewds", 2002, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityPewds.class, 2, 1, 1,
				EnumCreatureType.creature, desert, plains);

        EntityRegistry.registerModEntity(EntityStephano.class,
				"creepypastacraft.stephano", 2003, CPC.instance, 64, 3, true);

        EntityRegistry.registerGlobalEntityID(EntityCry.class,
				"creepypastacraft.cry", findGlobalUniqueEntityId(), 0xFFFFFF, 0x000000);
        EntityRegistry.registerModEntity(EntityCry.class,
				"creepypastacraft.cry", 2004, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityCry.class, 2, 1, 1,
				EnumCreatureType.creature, desert, plains);

        EntityRegistry.registerModEntity(EntityJack.class,
				"creepypastacraft.jack", 2005, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityJack.class, 4, 1, 1,
				EnumCreatureType.monster, forest);

        EntityRegistry.registerModEntity(EntityRake.class,
				"creepypastacraft.rake", 2006, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityRake.class, 1, 1, 1,
				EnumCreatureType.monster, forestHills);

        EntityRegistry.registerModEntity(EntitySmileDog.class,
				"creepypastacraft.smile", 2007, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntitySmileDog.class, 4, 1, 1,
				EnumCreatureType.monster, forest, forestHills);

        EntityRegistry.registerModEntity(EntitySeed.class,
				"creepypastacraft.seed", 2009, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntitySeed.class, 3, 1, 1,
				EnumCreatureType.monster, forest);

        EntityRegistry.registerModEntity(EntityMothman.class,
				"creepypastacraft.moth", 2010, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityMothman.class, 3, 1, 1,
				EnumCreatureType.monster);

        EntityRegistry.registerModEntity(EntitySquidward.class,
				"creepypastacraft.squidward", 2012, CPC.instance, 80, 3, true);

        EntityRegistry.registerModEntity(EntityStrider.class,
				"creepypastacraft.strider", 2013, CPC.instance, 80, 3, true);

		EntityRegistry.registerGlobalEntityID(EntityVampireBat.class,
				"creepypastacraft.bat", findGlobalUniqueEntityId(), 0, 0);
		EntityRegistry.registerModEntity(EntityVampireBat.class,
				"creepypastacraft.bat", 2014, CPC.instance, 80, 3, true);
    }
}
