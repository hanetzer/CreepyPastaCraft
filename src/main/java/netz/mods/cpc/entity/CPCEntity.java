package netz.mods.cpc.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import netz.mods.cpc.CPC;
import netz.mods.cpc.config.CPCConfigEntityEnable;
import netz.mods.cpc.entity.monster.EntityJack;
import netz.mods.cpc.entity.monster.EntityJeff;
import netz.mods.cpc.entity.monster.EntityMothman;
import netz.mods.cpc.entity.monster.EntityRake;
import netz.mods.cpc.entity.monster.EntitySeed;
import netz.mods.cpc.entity.monster.EntitySlender;
import netz.mods.cpc.entity.monster.EntitySmile;
import netz.mods.cpc.entity.passive.EntityCry;
import netz.mods.cpc.entity.passive.EntityJane;
import netz.mods.cpc.entity.passive.EntityPewds;
import netz.mods.cpc.entity.passive.EntitySquidward;
import netz.mods.cpc.entity.passive.EntityStrider;
import netz.mods.cpc.entity.projectile.EntityStephano;
import netz.mods.cpc.world.biome.CPCBiomes;
import cpw.mods.fml.common.registry.EntityRegistry;

public class CPCEntity
{
	public static EntityStephano stephano;
	public CPCEntity()
	{
		super();
	}

	public static void init()
	{
		if(CPCConfigEntityEnable.enableJeff)
		{
			EntityRegistry.registerGlobalEntityID(EntityJeff.class, "jeff", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntityJeff.class,		"jeff",		2000,		CPC.instance,  80, 3, true);
			EntityRegistry.addSpawn(EntityJeff.class,		5, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills);
		}

		if(CPCConfigEntityEnable.enableJane)
		{
			EntityRegistry.registerGlobalEntityID(EntityJane.class, "jane", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntityJane.class,		"jane",		2001,		CPC.instance,  80, 3, true);
			EntityRegistry.addSpawn(EntityJane.class,		5, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills);
		}

		if(CPCConfigEntityEnable.enablePewds)
		{
			EntityRegistry.registerGlobalEntityID(EntityPewds.class, "pewds", EntityRegistry.findGlobalUniqueEntityId(), 0x35A5C8, 0xD87333);
			EntityRegistry.registerModEntity(EntityPewds.class,		"pewds",		2002,		CPC.instance,  80, 3, true);
			EntityRegistry.addSpawn(EntityPewds.class,		2, 1, 1, EnumCreatureType.creature, BiomeGenBase.desert, BiomeGenBase.plains);
			EntityRegistry.registerModEntity(EntityStephano.class,	"stephano",	2003,	CPC.instance,  64, 3, true);
		}

		if(CPCConfigEntityEnable.enableCry)
		{
			EntityRegistry.registerGlobalEntityID(EntityCry.class, "cry", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFFFF, 0x000000);
			EntityRegistry.registerModEntity(EntityCry.class,		"cry",		2004,		CPC.instance,  80, 3, true);
			EntityRegistry.addSpawn(EntityCry.class,		2, 1, 1, EnumCreatureType.creature, BiomeGenBase.desert, BiomeGenBase.plains);
		}

		if(CPCConfigEntityEnable.enableJack)
		{
			EntityRegistry.registerGlobalEntityID(EntityJack.class, "jack", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntityJack.class,		"jack",		2005,		CPC.instance,  80, 3, true);
			EntityRegistry.addSpawn(EntityJack.class,		4, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);
		}

		if(CPCConfigEntityEnable.enableRake)
		{
			EntityRegistry.registerGlobalEntityID(EntityRake.class, "rake", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntityRake.class,		"rake",		2006,		CPC.instance,  80, 3, true);
			EntityRegistry.addSpawn(EntityRake.class,		1, 1, 1, EnumCreatureType.monster, BiomeGenBase.forestHills);
		}

		if(CPCConfigEntityEnable.enableSmile)
		{
			EntityRegistry.registerGlobalEntityID(EntitySmile.class, "smile", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntitySmile.class,		"smile",		2007,		CPC.instance,  80, 3, true);
			EntityRegistry.addSpawn(EntitySmile.class,		4, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills);
		}

		if(CPCConfigEntityEnable.enableSlendy)
		{
			EntityRegistry.registerGlobalEntityID(EntitySlender.class, "slender", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntitySlender.class,	"slender",		2008,	CPC.instance, 160, 2, true);
			EntityRegistry.addSpawn(EntitySlender.class,	4, 1, 1, EnumCreatureType.monster, CPCBiomes.slenderWood);
		}

		if(CPCConfigEntityEnable.enableSeed)
		{
			EntityRegistry.registerGlobalEntityID(EntitySeed.class, "seed", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntitySeed.class,		"seed",		2009,		CPC.instance,  80, 3, true);
			EntityRegistry.addSpawn(EntitySeed.class,		3, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);
		}

		if(CPCConfigEntityEnable.enableMoth)
		{
			EntityRegistry.registerGlobalEntityID(EntityMothman.class, "moth", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntityMothman.class,	"moth",		2010,		CPC.instance,  80, 3, true);
			EntityRegistry.addSpawn(EntityMothman.class,		3, 1, 1, EnumCreatureType.monster);
		}

		if(CPCConfigEntityEnable.enableSquid)
		{
			EntityRegistry.registerGlobalEntityID(EntitySquidward.class, "squidward", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntitySquidward.class,	"squidward",		2012,		CPC.instance,  80, 3, true);
		}

		if(CPCConfigEntityEnable.enableStrider)
		{
			EntityRegistry.registerGlobalEntityID(EntityStrider.class, "strider", EntityRegistry.findGlobalUniqueEntityId());
			EntityRegistry.registerModEntity(EntityStrider.class,	"strider",	2013,	CPC.instance,  80, 3, true);
		}
	}
}
