package recraft.cpc.core;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import recraft.cpc.CPC;
import recraft.cpc.common.entity.monster.*;
import recraft.cpc.common.entity.passive.*;
import recraft.cpc.common.entity.projectile.EntityStephano;

public class CPCEntity {
	public static EntityStephano stephano;
	public static void init() {
		EntityRegistry.registerGlobalEntityID(EntityJeff.class, "cpc:jeff", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityJeff.class,		"cpc:jeff",		2000,		CPC.instance,  80, 3, true);
		EntityRegistry.addSpawn(EntityJeff.class,		5, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills);

		EntityRegistry.registerGlobalEntityID(EntityJane.class, "cpc:jane", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityJane.class,		"cpc:jane",		2001,		CPC.instance,  80, 3, true);
		EntityRegistry.addSpawn(EntityJane.class, 5, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills);

		EntityRegistry.registerGlobalEntityID(EntityPewds.class, "cpc:pewds", EntityRegistry.findGlobalUniqueEntityId(), 0x35A5C8, 0xD87333);
		EntityRegistry.registerModEntity(EntityPewds.class, "cpc:pewds", 2002, CPC.instance, 80, 3, true);
		EntityRegistry.addSpawn(EntityPewds.class, 2, 1, 1, EnumCreatureType.creature, BiomeGenBase.desert, BiomeGenBase.plains);

		EntityRegistry.registerModEntity(EntityStephano.class, "cpc:stephano", 2003, CPC.instance, 64, 3, true);

		EntityRegistry.registerGlobalEntityID(EntityCry.class, "cpc:cry", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFFFF, 0x000000);
		EntityRegistry.registerModEntity(EntityCry.class, "cpc:cry", 2004, CPC.instance, 80, 3, true);
		EntityRegistry.addSpawn(EntityCry.class, 2, 1, 1, EnumCreatureType.creature, BiomeGenBase.desert, BiomeGenBase.plains);

		EntityRegistry.registerGlobalEntityID(EntityJack.class, "cpc:jack", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityJack.class, "cpc:jack", 2005, CPC.instance, 80, 3, true);
		EntityRegistry.addSpawn(EntityJack.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);

		EntityRegistry.registerGlobalEntityID(EntityRake.class, "cpc:rake", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityRake.class, "cpc:rake", 2006, CPC.instance, 80, 3, true);
		EntityRegistry.addSpawn(EntityRake.class, 1, 1, 1, EnumCreatureType.monster, BiomeGenBase.forestHills);

		EntityRegistry.registerGlobalEntityID(EntitySmileDog.class, "cpc:smile", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntitySmileDog.class, "cpc:smile", 2007, CPC.instance, 80, 3, true);
		EntityRegistry.addSpawn(EntitySmileDog.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills);

		EntityRegistry.registerGlobalEntityID(EntitySeed.class, "cpc:seed", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntitySeed.class, "cpc:seed", 2009, CPC.instance, 80, 3, true);
		EntityRegistry.addSpawn(EntitySeed.class, 3, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);

		EntityRegistry.registerGlobalEntityID(EntityMothman.class, "cpc:moth", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityMothman.class, "cpc:moth", 2010, CPC.instance, 80, 3, true);
		EntityRegistry.addSpawn(EntityMothman.class, 3, 1, 1, EnumCreatureType.monster);

		EntityRegistry.registerGlobalEntityID(EntitySquidward.class, "cpc:squidwardward", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntitySquidward.class, "cpc:squidward", 2012, CPC.instance, 80, 3, true);

		EntityRegistry.registerGlobalEntityID(EntityStrider.class, "cpc:strider", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityStrider.class, "cpc:strider", 2013, CPC.instance, 80, 3, true);
	}
}
