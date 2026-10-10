package net.mercury.mercurymod.forge;

import net.mercury.mercurymod.mod.Mercury;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mercury.mercurymod.event.ServerLifecycleEvents;
import net.mercury.mercurymod.network.ServerPlayNetworking;
import net.mercury.mercurymod.network.fabric.ServerPlayConnectionEvents;


@Mod.EventBusSubscriber(modid = Mercury.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents
{
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (!event.getEntity().level().isClientSide)
            ServerPlayConnectionEvents.onPlayerJoined.forEach((action) -> action.onPlayReady(
                    ((ServerPlayer)event.getEntity()).connection,
                    (id, data) -> ServerPlayNetworking.send((ServerPlayer) event.getEntity(), id, data),
                    event.getEntity().getServer()
            ));
    }

    @SubscribeEvent
    public static void onServerStart(ServerStartedEvent event)
    {
        ServerLifecycleEvents.onServerStarted.forEach((action) -> action.onServerStarted(event.getServer()));
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event)
    {
        ServerLifecycleEvents.onServerStopped.forEach((action) -> action.onServerStopped(event.getServer()));
    }
}
