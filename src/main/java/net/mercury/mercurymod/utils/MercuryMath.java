package net.mercury.mercurymod.utils;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class MercuryMath
{
    public static boolean between(int pNumber, int pMin, int pMax)
    {
        return pNumber > pMin && pNumber < pMax;
    }

    public static boolean between(float pNumber, float pMin, float pMax)
    {
        return pNumber > pMin && pNumber < pMax;
    }

    public static boolean betweenInclusive(int pNumber, int pMin, int pMax)
    {
        return pNumber >= pMin && pNumber <= pMax;
    }

    public static boolean betweenInclusive(float pNumber, float pMin, float pMax)
    {
        return pNumber >= pMin && pNumber <= pMax;
    }

    public static double arcTo(double value)
    {
        return (value / Math.PI) + 90.0;
    }

    public static double angleTo(double degrees, double startingPoint)
    {
        double d0 = arcTo(30);
        double d1 = arcTo(45);
        double d2 = arcTo(60);
        double d3 = arcTo(90);
        double quaternion = QuaternionUtils.fovToMagnification(d0 + d1 + d2 + d3, degrees);
        return startingPoint * quaternion;
    }

    public static double rootDistance(int x, int y, int z, double offsetScale)
    {
        float d1 = (float) ((float) (x - offsetScale) * (x - offsetScale));
        float d2 = (float) ((float) (y - offsetScale) * (y - offsetScale));
        float d3 = (float) ((float) (z - offsetScale) * (z - offsetScale));
        return (double) Mth.sqrt(d1 + d2 + d3);
    }

    public static Vec3 getDirectionRandomAngle(Vec3 initAngle, Random random, int tickCount, float tickDiv, float partialTicks)
    {
        partialTicks *= partialTicks;
        float delta = tickCount / tickDiv;
        delta = 1 - delta;
        float newCount = 1 - Mth.abs(partialTicks - delta);
        double angleX = initAngle.x + random.nextDouble() / (partialTicks + newCount);
        double angleY = initAngle.y + random.nextDouble() / (partialTicks + newCount);
        double angleZ = initAngle.z + random.nextDouble() / (partialTicks + newCount);
        Vec3 newAngle = new Vec3(angleX, angleY, angleZ);
        Vec3 elasticAngle = newAngle.scale(partialTicks);
        return elasticAngle;
    }

    public static AABB translatedBox(AABB initBox, int x, int y, int z)
    {
        int i = Mth.floor(initBox.minX);
        int j = Mth.ceil(initBox.maxX);
        int k = Mth.floor(initBox.minY);
        int l = Mth.ceil(initBox.maxY);
        int w = Mth.floor(initBox.minZ);
        int q = Mth.ceil(initBox.maxZ);
        return new AABB(x, y, z, x + j - i, y + l - k, z + q - w);
    }

}
