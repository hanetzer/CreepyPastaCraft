package com.teamfear.creepypastacraft.common.entity.monster;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

public class EntitySmiley extends EntityMob implements IMob
{
	public EntitySmiley(World w)
	{
		super(w);
		setSize(0.6F, 1.8F);
		isImmuneToFire = true;
		tasks.addTask(0, new EntityAISwimming(this));
	}
}
