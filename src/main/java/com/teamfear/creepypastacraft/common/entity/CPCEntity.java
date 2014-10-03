package com.teamfear.creepypastacraft.common.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import com.teamfear.creepypastacraft.CPC;
import com.teamfear.creepypastacraft.common.entity.monster.*;
import com.teamfear.creepypastacraft.common.entity.monster.EntitySmileDog;
import com.teamfear.creepypastacraft.common.entity.passive.*;
import com.teamfear.creepypastacraft.common.entity.projectile.EntityStephano;

import static cpw.mods.fml.common.registry.EntityRegistry.*;
import static net.minecraft.world.biome.BiomeGenBase.*;

public class CPCEntity {
    public static EntityStephano stephano;

    public static void init() {
        EntityRegistry.registerModEntity(EntityJeff.class,
				"com.teamfear.creepypastacraft.jeff", 2000, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityJeff.class, 5, 1, 1,
				EnumCreatureType.monster, forest, forestHills);

        EntityRegistry.registerModEntity(EntityJane.class,
				"com.teamfear.creepypastacraft.jane", 2001, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityJane.class, 5, 1, 1,
				EnumCreatureType.monster, forest, forestHills);

        EntityRegistry.registerGlobalEntityID(EntityPewds.class,
				"com.teamfear.creepypastacraft.pewds", findGlobalUniqueEntityId(), 0x35A5C8, 0xD87333);
        EntityRegistry.registerModEntity(EntityPewds.class,
				"com.teamfear.creepypastacraft.pewds", 2002, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityPewds.class, 2, 1, 1,
				EnumCreatureType.creature, desert, plains);

        EntityRegistry.registerModEntity(EntityStephano.class,
				"com.teamfear.creepypastacraft.stephano", 2003, CPC.instance, 64, 3, true);

        EntityRegistry.registerGlobalEntityID(EntityCry.class,
				"com.teamfear.creepypastacraft.cry", findGlobalUniqueEntityId(), 0xFFFFFF, 0x000000);
        EntityRegistry.registerModEntity(EntityCry.class,
				"com.teamfear.creepypastacraft.cry", 2004, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityCry.class, 2, 1, 1,
				EnumCreatureType.creature, desert, plains);

        EntityRegistry.registerModEntity(EntityJack.class,
				"com.teamfear.creepypastacraft.jack", 2005, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityJack.class, 4, 1, 1,
				EnumCreatureType.monster, forest);

        EntityRegistry.registerModEntity(EntityRake.class,
				"com.teamfear.creepypastacraft.rake", 2006, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityRake.class, 1, 1, 1,
				EnumCreatureType.monster, forestHills);

        EntityRegistry.registerModEntity(EntitySmileDog.class,
				"com.teamfear.creepypastacraft.smile", 2007, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntitySmileDog.class, 4, 1, 1,
				EnumCreatureType.monster, forest, forestHills);

        EntityRegistry.registerModEntity(EntitySeed.class,
				"com.teamfear.creepypastacraft.seed", 2009, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntitySeed.class, 3, 1, 1,
				EnumCreatureType.monster, forest);

        EntityRegistry.registerModEntity(EntityMothman.class,
				"com.teamfear.creepypastacraft.moth", 2010, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityMothman.class, 3, 1, 1,
				EnumCreatureType.monster);

        EntityRegistry.registerModEntity(EntitySquidward.class,
				"com.teamfear.creepypastacraft.squidward", 2012, CPC.instance, 80, 3, true);

        EntityRegistry.registerModEntity(EntityStrider.class,
				"com.teamfear.creepypastacraft.strider", 2013, CPC.instance, 80, 3, true);

		EntityRegistry.registerGlobalEntityID(EntityVampireBat.class,
				"com.teamfear.creepypastacraft.bat", findGlobalUniqueEntityId(), 0, 0);
		EntityRegistry.registerModEntity(EntityVampireBat.class,
				"com.teamfear.creepypastacraft.bat", 2014, CPC.instance, 80, 3, true);
    }
}
