package net.mercury.mercurymod.utils;

public class MercuryTime
{
    /**
     * Returns the conversion from seconds to ticks.
     * @param pSeconds
     * @return
     */

    public static int toTicksFromSeconds(int pSeconds)
    {
        return pSeconds * 20;
    }

    /**
     * Returns the conversion from minutes to ticks.
     * @param pMinutes
     * @return
     */

    public static int toTicksFromMinutes(int pMinutes)
    {
        return toTicksFromSeconds(pMinutes * 60);
    }

    /**
     * Returns the conversion from ticks to seconds.
     * @param pTicks
     * @return
     */

    public static int toSeconds(int pTicks)
    {
        return pTicks / 20;
    }
}
