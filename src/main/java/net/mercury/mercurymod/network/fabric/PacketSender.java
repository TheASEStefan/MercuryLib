package net.mercury.mercurymod.network.fabric;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public interface PacketSender
{
    void sendPacket(ResourceLocation channel, FriendlyByteBuf buf);
}
