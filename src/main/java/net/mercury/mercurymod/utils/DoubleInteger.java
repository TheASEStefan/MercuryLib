package net.mercury.mercurymod.utils;

import java.io.Serializable;

public record DoubleInteger(int minValue, int maxValue) implements Serializable
{

    public static DoubleInteger create(int minValue, int maxValue)
    {
        return new DoubleInteger(minValue, maxValue);
    }

    public boolean isConvergent(int pValue)
    {
        return MercuryMath.betweenInclusive(pValue, this.minValue(), this.maxValue());
    }

    public static DoubleInteger readFromStringInterval(String string)
    {
        string = string.substring(1, string.length() - 1);
        String[] parts = string.split(",");
        int lower = Integer.parseInt(parts[0]);
        int upper = Integer.parseInt(parts[1]);
        return DoubleInteger.create(lower, upper);
    }
}
