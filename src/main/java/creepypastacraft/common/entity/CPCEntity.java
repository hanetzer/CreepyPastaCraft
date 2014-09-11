package creepypastacraft.common.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import creepypastacraft.CPC;
import creepypastacraft.common.entity.monster.*;
import creepypastacraft.common.entity.monster.EntitySmileDog;
import creepypastacraft.common.entity.passive.*;
import creepypastacraft.common.entity.projectile.EntityStephano;

public class CPCEntity {
    public static EntityStephano stephano;

    public static void init() {
        EntityRegistry.registerGlobalEntityID(EntityJeff.class, "creepypastacraft:jeff", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityJeff.class, "creepypastacraft:jeff", 2000, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityJeff.class, 5, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills);

        EntityRegistry.registerGlobalEntityID(EntityJane.class, "creepypastacraft:jane", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityJane.class, "creepypastacraft:jane", 2001, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityJane.class, 5, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills);

        EntityRegistry.registerGlobalEntityID(EntityPewds.class, "creepypastacraft:pewds", EntityRegistry.findGlobalUniqueEntityId(), 0x35A5C8, 0xD87333);
        EntityRegistry.registerModEntity(EntityPewds.class, "creepypastacraft:pewds", 2002, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityPewds.class, 2, 1, 1, EnumCreatureType.creature, BiomeGenBase.desert, BiomeGenBase.plains);

        EntityRegistry.registerModEntity(EntityStephano.class, "creepypastacraft:stephano", 2003, CPC.instance, 64, 3, true);

        EntityRegistry.registerGlobalEntityID(EntityCry.class, "creepypastacraft:cry", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFFFF, 0x000000);
        EntityRegistry.registerModEntity(EntityCry.class, "creepypastacraft:cry", 2004, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityCry.class, 2, 1, 1, EnumCreatureType.creature, BiomeGenBase.desert, BiomeGenBase.plains);

        EntityRegistry.registerGlobalEntityID(EntityJack.class, "creepypastacraft:jack", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityJack.class, "creepypastacraft:jack", 2005, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityJack.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);

        EntityRegistry.registerGlobalEntityID(EntityRake.class, "creepypastacraft:rake", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityRake.class, "creepypastacraft:rake", 2006, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityRake.class, 1, 1, 1, EnumCreatureType.monster, BiomeGenBase.forestHills);

        EntityRegistry.registerGlobalEntityID(EntitySmileDog.class, "creepypastacraft:smile", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntitySmileDog.class, "creepypastacraft:smile", 2007, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntitySmileDog.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills);

        EntityRegistry.registerGlobalEntityID(EntitySeed.class, "creepypastacraft:seed", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntitySeed.class, "creepypastacraft:seed", 2009, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntitySeed.class, 3, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);

        EntityRegistry.registerGlobalEntityID(EntityMothman.class, "creepypastacraft:moth", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityMothman.class, "creepypastacraft:moth", 2010, CPC.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityMothman.class, 3, 1, 1, EnumCreatureType.monster);

        EntityRegistry.registerGlobalEntityID(creepypastacraft.common.entity.passive.EntitySquidward.class, "creepypastacraft:squidwardward", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(creepypastacraft.common.entity.passive.EntitySquidward.class, "creepypastacraft:squidward", 2012, CPC.instance, 80, 3, true);

        EntityRegistry.registerGlobalEntityID(EntityStrider.class, "creepypastacraft:strider", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityStrider.class, "creepypastacraft:strider", 2013, CPC.instance, 80, 3, true);
    }
}
