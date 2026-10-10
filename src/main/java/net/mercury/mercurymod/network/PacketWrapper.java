package net.mercury.mercurymod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketWrapper
{
    boolean isClientBound;
    public final ResourceLocation packetType;
    public final ByteBuf data;
    public PacketWrapper(boolean isClientBound, ResourceLocation packetType, ByteBuf data)
    {
        this.isClientBound = isClientBound;
        this.packetType = packetType;
        this.data = data;
    }

    public static void encode(PacketWrapper msg, FriendlyByteBuf packetByteBuf)
    {
        packetByteBuf.writeBoolean(msg.isClientBound);
        packetByteBuf.writeResourceLocation(msg.packetType);
        packetByteBuf.writeBytes(msg.data);
    }

    public static PacketWrapper decode(FriendlyByteBuf packetByteBuf)
    {
        return new PacketWrapper(packetByteBuf.readBoolean(), packetByteBuf.readResourceLocation(), packetByteBuf.readBytes(packetByteBuf.readableBytes()));
    }

    public static void handle(PacketWrapper msg, Supplier<NetworkEvent.Context> contextSupplier)
    {
        if (msg.isClientBound) ClientPlayNetworking.handle(msg);
        else ServerPlayNetworking.handle(msg, contextSupplier.get().getSender());
        contextSupplier.get().setPacketHandled(true);
    }

}
