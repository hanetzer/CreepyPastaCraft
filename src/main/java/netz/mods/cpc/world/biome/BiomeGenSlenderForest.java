package netz.mods.cpc.world.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import netz.mods.cpc.entity.monster.EntitySlender;

public class BiomeGenSlenderForest extends BiomeGenBase {

	Random rand = new Random();

	@SuppressWarnings("unchecked")
	public BiomeGenSlenderForest(int par1)
	{
		super(par1);
		super.spawnableMonsterList.clear();
		super.spawnableCreatureList.clear();
		super.spawnableWaterCreatureList.clear();
		super.spawnableCaveCreatureList.clear();
		super.biomeName = "Slender Forest";
		super.theBiomeDecorator.generateLakes = false;
		super.worldGeneratorForest = new WorldGenForest(false);
		super.worldGeneratorTrees = new WorldGenTrees(false);
		super.color = 2;
		super.spawnableCreatureList.add(new SpawnListEntry(EntitySlender.class, 20, 1, 1));
	}

	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return (WorldGenerator)(par1Random.nextInt(2) == 0?super.worldGeneratorForest:(par1Random.nextInt(4) == 0?super.worldGeneratorBigTree:super.worldGeneratorTrees));
	}
}