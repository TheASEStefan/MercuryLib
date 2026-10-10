package net.mercury.mercurymod.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;

public class MercuryBlockImpacts
{
    /** <a href="https://github.com/lender544/FromTheShadows-1.20.1/blob/master/src/main/java/net/sonmok14/fromtheshadows/server/entity/mob/NehemothEntity.java#L600">...</a> */
    public static void slamBlocksAround(Level level, int distance, LivingEntity entity, double pushX, double pushY, double pushZ)
    {
        double perpFacing = entity.yBodyRot * (Math.PI / 180);
        double facingAngle = perpFacing + Math.PI / 2;
        int hitY = Mth.floor(entity.getBoundingBox().minY - 0.5);
        double spread = Math.PI * 2;
        int arcLen = Mth.ceil(distance * spread);
        for (int i = 0; i < arcLen; i ++)
        {
            double theta = (i / (arcLen - 1.0) - 0.5) * spread + facingAngle;
            double vx = Math.cos(theta);
            double vz = Math.sin(theta);
            double px = entity.getX() + vx * distance;
            double pz = entity.getZ() + vz * distance;
            if (ForgeEventFactory.getMobGriefingEvent(level, entity))
            {
                int hitX = Mth.floor(px);
                int hitZ = Mth.floor(pz);
                RandomSource randomSource = level.getRandom();
                BlockPos pos = new BlockPos(hitX, hitY, hitZ);
                BlockState block = level.getBlockState(pos);

                if (level.getBlockState(pos) != Blocks.AIR.defaultBlockState() && block.getFluidState().isEmpty())
                {
                    FallingBlockEntity fallingBlockEntity = new FallingBlockEntity(level, hitX + 0.5D, hitY + 0.5D, hitZ + 0.5D, block);
                    level.setBlock(pos, block.getFluidState().createLegacyBlock(), 3);
                    fallingBlockEntity.push(randomSource.nextGaussian() * pushX, pushY + randomSource.nextGaussian() * pushY, randomSource.nextGaussian() * pushZ);
                    level.addFreshEntity(fallingBlockEntity);
                }
            }
        }
    }
}
