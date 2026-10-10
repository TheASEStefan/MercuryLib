package net.mercury.mercurymod.event;

import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class ServerLifecycleEvents
{
    public static List<ServerStarted> onServerStarted = new ArrayList<>();
    public static List<ServerStopped> onServerStopped = new ArrayList<>();
    public static final Event<ServerStarted> SERVER_STARTED = new ServerStartedEvent();
    public static final Event<ServerStopped> SERVER_STOPPED = new ServerStoppedEvent();

    public static class ServerStartedEvent extends Event<ServerStarted>
    {
        public void register(ServerStarted listener)
        {
            onServerStarted.add(listener);
        }
    }

    public static class ServerStoppedEvent extends Event<ServerStopped>
    {
        public void register(ServerStopped listener)
        {
            onServerStopped.add(listener);
        }
    }

    public interface ServerStarted
    {
        void onServerStarted(MinecraftServer server);
    }

    public interface ServerStopped
    {
        void onServerStopped(MinecraftServer server);
    }
}
