package net.mercury.mercurymod.utils;

import net.minecraft.util.RandomSource;

public class ParticleUtils
{
    public static double getDownwardsSmallSpread(RandomSource random)
    {
        return -(random.nextDouble() - 0.5D) * 0.05D;
    }

    public static double getDownwardsMediumSpread(RandomSource random)
    {
        return -(random.nextDouble() - 0.5D) * 0.1D;
    }

    public static double getDownwardsLargeSpread(RandomSource random)
    {
        return -(random.nextDouble() - 0.5D) * 0.15D;
    }

    public static double getUpwardsSmallSpread(RandomSource random)
    {
        return getDownwardsSmallSpread(random) * (-1);
    }

    public static double getUpwardsMediumSpread(RandomSource random)
    {
        return getDownwardsMediumSpread(random) * (-1);
    }

    public static double getUpwardsLargeSpread(RandomSource random)
    {
        return getDownwardsLargeSpread(random) * (-1);
    }

    public static double xSphereSpeed(double t)
    {
        return Math.sin((float) (t / 10 * Math.PI));
    }

    public static double ySphereSpeed(double t)
    {
        return Math.sin((float) ((t % 10) * Math.PI)) * Math.cos((float) (t / 10 * Math.PI));
    }

    public static double zSphereSpeed(double t)
    {
        return Math.cos((float) ((t % 10) * Math.PI)) * Math.cos((float) (t / 10 * Math.PI));
    }
}
