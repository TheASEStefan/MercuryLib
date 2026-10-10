package net.mercury.mercurymod.chunk;

import net.mercury.mercurymod.event.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class ChunkDataRegistry
{
    public static void registerChunkData()
    {
        ServerLifecycleEvents.SERVER_STARTED.register(new ChunkStartedData());
        ServerLifecycleEvents.SERVER_STOPPED.register(new ChunkStoppedData());
    }

    public static class ChunkStartedData implements ServerLifecycleEvents.ServerStarted
    {
        @Override
        public void onServerStarted(MinecraftServer server)
        {
            ChunkLoadedData.clearData();
        }
    }

    public static class ChunkStoppedData implements ServerLifecycleEvents.ServerStopped
    {
        @Override
        public void onServerStopped(MinecraftServer server)
        {
            ChunkLoadedData.clearData();
        }
    }
}
