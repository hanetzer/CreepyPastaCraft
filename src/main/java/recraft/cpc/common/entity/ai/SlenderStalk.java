package recraft.cpc.common.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SlenderStalk
{
	public static MovingObjectPosition rayTrace(Vec3 vec3d, Vec3 vec3d1, boolean flag, boolean flag1, World worldObj) {
		if(worldObj == null) {
			return null;
		} else if(!Double.isNaN(vec3d.xCoord) && !Double.isNaN(vec3d.yCoord) && !Double.isNaN(vec3d.zCoord)) {
			if(!Double.isNaN(vec3d1.xCoord) && !Double.isNaN(vec3d1.yCoord) && !Double.isNaN(vec3d1.zCoord)) {
				int i = MathHelper.floor_double(vec3d1.xCoord);
				int j = MathHelper.floor_double(vec3d1.yCoord);
				int k = MathHelper.floor_double(vec3d1.zCoord);
				int l = MathHelper.floor_double(vec3d.xCoord);
				int i1 = MathHelper.floor_double(vec3d.yCoord);
				int j1 = MathHelper.floor_double(vec3d.zCoord);
				int k1 = worldObj.getBlockId(l, i1, j1);
				int i2 = worldObj.getBlockMetadata(l, i1, j1);
				Block block = Block.blocksList[k1];
				if((!flag1 || block == null || block.getCollisionBoundingBoxFromPool(worldObj, l, i1, j1) != null) && k1 > 0 && block.canCollideCheck(i2, flag)) {
					MovingObjectPosition l1 = block.collisionRayTrace(worldObj, l, i1, j1, vec3d, vec3d1);
					if(l1 != null) {
						return l1;
					}
				}

				int var42 = 200;

				while(var42-- >= 0) {
					if(Double.isNaN(vec3d.xCoord) || Double.isNaN(vec3d.yCoord) || Double.isNaN(vec3d.zCoord)) {
						return null;
					}

					if(l == i && i1 == j && j1 == k) {
						return null;
					}

					boolean flag2 = true;
					boolean flag3 = true;
					boolean flag4 = true;
					double d = 999.0D;
					double d1 = 999.0D;
					double d2 = 999.0D;
					if(i > l) {
						d = (double)l + 1.0D;
					} else if(i < l) {
						d = (double)l + 0.0D;
					} else {
						flag2 = false;
					}

					if(j > i1) {
						d1 = (double)i1 + 1.0D;
					} else if(j < i1) {
						d1 = (double)i1 + 0.0D;
					} else {
						flag3 = false;
					}

					if(k > j1) {
						d2 = (double)j1 + 1.0D;
					} else if(k < j1) {
						d2 = (double)j1 + 0.0D;
					} else {
						flag4 = false;
					}

					double d3 = 999.0D;
					double d4 = 999.0D;
					double d5 = 999.0D;
					double d6 = vec3d1.xCoord - vec3d.xCoord;
					double d7 = vec3d1.yCoord - vec3d.yCoord;
					double d8 = vec3d1.zCoord - vec3d.zCoord;
					if(flag2) {
						d3 = (d - vec3d.xCoord) / d6;
					}

					if(flag3) {
						d4 = (d1 - vec3d.yCoord) / d7;
					}

					if(flag4) {
						d5 = (d2 - vec3d.zCoord) / d8;
					}

					boolean byte0 = false;
					byte var43;
					if(d3 < d4 && d3 < d5) {
						if(i > l) {
							var43 = 4;
						} else {
							var43 = 5;
						}

						vec3d.xCoord = d;
						vec3d.yCoord += d7 * d3;
						vec3d.zCoord += d8 * d3;
					} else if(d4 < d5) {
						if(j > i1) {
							var43 = 0;
						} else {
							var43 = 1;
						}

						vec3d.xCoord += d6 * d4;
						vec3d.yCoord = d1;
						vec3d.zCoord += d8 * d4;
					} else {
						if(k > j1) {
							var43 = 2;
						} else {
							var43 = 3;
						}

						vec3d.xCoord += d6 * d5;
						vec3d.yCoord += d7 * d5;
						vec3d.zCoord = d2;
					}

					Vec3 vec3d2 = worldObj.getWorldVec3Pool().getVecFromPool(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
					l = (int)(vec3d2.xCoord = (double)MathHelper.floor_double(vec3d.xCoord));
					if(var43 == 5) {
						--l;
						++vec3d2.xCoord;
					}

					i1 = (int)(vec3d2.yCoord = (double)MathHelper.floor_double(vec3d.yCoord));
					if(var43 == 1) {
						--i1;
						++vec3d2.yCoord;
					}

					j1 = (int)(vec3d2.zCoord = (double)MathHelper.floor_double(vec3d.zCoord));
					if(var43 == 3) {
						--j1;
						++vec3d2.zCoord;
					}

					int j2 = worldObj.getBlockId(l, i1, j1);
					if(j2 != Block.glass.blockID && j2 != Block.thinGlass.blockID && j2 != Block.fenceIron.blockID && !isTransparent(j2)) {
						int k2 = worldObj.getBlockMetadata(l, i1, j1);
						Block block1 = Block.blocksList[j2];
						if((!flag1 || block1 == null || block1.getCollisionBoundingBoxFromPool(worldObj, l, i1, j1) != null) && j2 > 0 && block1.canCollideCheck(k2, flag)) {
							MovingObjectPosition movingobjectposition1 = block1.collisionRayTrace(worldObj, l, i1, j1, vec3d, vec3d1);
							if(movingobjectposition1 != null) {
								return movingobjectposition1;
							}
						}
					}
				}

				return null;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static void faceEntity(Entity facer, Entity faced, float maxYaw, float maxPitch) {
		double d0 = faced.posX - facer.posX;
		double d1 = faced.posZ - facer.posZ;
		double d2;
		if(faced instanceof EntityLivingBase) {
			EntityLivingBase d3 = (EntityLivingBase)faced;
			d2 = d3.posY + (double)d3.getEyeHeight() - (facer.posY + (double)facer.getEyeHeight());
		} else {
			d2 = (faced.boundingBox.minY + faced.boundingBox.maxY) / 2.0D - (facer.posY + (double)facer.getEyeHeight());
		}

		double d31 = (double)MathHelper.sqrt_double(d0 * d0 + d1 * d1);
		float f2 = (float)(Math.atan2(d1, d0) * 180.0D / 3.141592653589793D) - 90.0F;
		float f3 = (float)(-(Math.atan2(d2, d31) * 180.0D / 3.141592653589793D));
		facer.rotationPitch = updateRotation(facer.rotationPitch, f3, maxPitch);
		facer.rotationYaw = updateRotation(facer.rotationYaw, f2, maxYaw);
	}

	public static float updateRotation(float oriRot, float intendedRot, float maxChange) {
		float var4 = MathHelper.wrapAngleTo180_float(intendedRot - oriRot);
		if(var4 > maxChange) {
			var4 = maxChange;
		}

		if(var4 < -maxChange) {
			var4 = -maxChange;
		}

		return oriRot + var4;
	}

	public static boolean isTransparent(int i)
	{
		return Block.lightOpacity[i] != 255;
	}

}
