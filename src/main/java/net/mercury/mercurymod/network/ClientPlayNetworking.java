package net.mercury.mercurymod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.mercury.mercurymod.network.fabric.PacketSender;

import java.util.HashMap;
import java.util.Map;

public class ClientPlayNetworking
{
    public static Map<ResourceLocation, PlayChannelHandler> HANDLERS = new HashMap<>();

    public static boolean registerGlobalReceiver(ResourceLocation id, PlayChannelHandler handler)
    {
        HANDLERS.put(id, handler);
        return true;
    }

    public static void send(ResourceLocation id, FriendlyByteBuf forwardBuffer)
    {
        NetworkHandler.INSTANCE.sendToServer(new PacketWrapper(false, id, forwardBuffer));
    }

    public static void handle(PacketWrapper msg)
    {
        HANDLERS.get(msg.packetType).receive(Minecraft.getInstance(), Minecraft.getInstance().getConnection(), new FriendlyByteBuf(msg.data), null);
    }

    public interface PlayChannelHandler
    {
        void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender);
    }
}
