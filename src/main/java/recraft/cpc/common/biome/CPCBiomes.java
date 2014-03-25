package recraft.cpc.common.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class CPCBiomes
{
	public static BiomeGenBase slenderWood;
	public CPCBiomes()
	{
		super();
	}

	public static void init()
	{
		initBiomes();
		addToBiomeDictionary();
		addSpawnBiomes();
	}

	public static void initBiomes()
	{
		slenderWood = (new BiomeGenSlenderForest(50));
	}

	public static void addToBiomeDictionary()
	{
		BiomeDictionary.registerBiomeType((BiomeGenBase)slenderWood, new Type[]{Type.FOREST});
	}

	public static void addSpawnBiomes()
	{
		addSpawnBiome(slenderWood);
	}

	private static void addSpawnBiome(BiomeGenBase biome)
	{
		BiomeManager.addSpawnBiome((BiomeGenBase)biome);
	}

}
