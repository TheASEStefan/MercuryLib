package net.mercury.mercurymod.utils;


import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;

import java.util.function.Supplier;

public class ExtendedBlockSound
{
    public static ForgeSoundType register(float volume, float pitch, Supplier<SoundEvent> destroySound, Supplier<SoundEvent> stepSound, Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound)
    {
        return new ForgeSoundType(volume, pitch, destroySound, stepSound, placeSound, hitSound, fallSound);
    }
}
