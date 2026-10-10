package net.mercury.mercurymod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraftforge.network.PacketDistributor;
import net.mercury.mercurymod.network.fabric.PacketSender;

import java.util.HashMap;
import java.util.Map;

public class ServerPlayNetworking
{
    public static Map<ResourceLocation, PlayChannelHandler> HANDLERS = new HashMap<>();

    public static boolean registerGlobalReceiver(ResourceLocation id, PlayChannelHandler handler)
    {
        HANDLERS.put(id, handler);
        return true;
    }

    public static boolean canSend(ServerPlayer serverPlayer, ResourceLocation id)
    {
        return serverPlayer != null;
    }

    public static void send(ServerPlayer serverPlayer, ResourceLocation id, FriendlyByteBuf forwardBuffer)
    {
        NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) serverPlayer), new PacketWrapper(true, id, new FriendlyByteBuf(forwardBuffer.copy())));
    }

    public static void handle(PacketWrapper msg, ServerPlayer sender)
    {
        HANDLERS.get(msg.packetType).receive(sender.getServer(), sender, sender.connection, new FriendlyByteBuf(msg.data), null);
    }

    public interface PlayChannelHandler
    {
        void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender);
    }
}
