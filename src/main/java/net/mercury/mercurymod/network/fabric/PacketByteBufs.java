package net.mercury.mercurymod.network.fabric;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;


public class PacketByteBufs
{
    public static FriendlyByteBuf create()
    {
        return new FriendlyByteBuf(Unpooled.buffer());
    }
}
