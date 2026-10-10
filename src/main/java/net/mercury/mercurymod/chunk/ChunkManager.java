package net.mercury.mercurymod.chunk;

import com.google.common.collect.Maps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* Custom chunk manager based on the ForgeChunkManager.class */
public class ChunkManager
{
    public static final Map<String, ChunkManagerRequest> ACTIVE_REQUESTS = Maps.newHashMap();

    public static void addRequest(ChunkManagerRequest request)
    {
        ACTIVE_REQUESTS.put(request.getRequestID(), request);
        ServerLevel level = request.getDimension();
        ChunkLoadedData data = ChunkLoadedData.getChunkData(level);
        for (ChunkPos pos : request.getChunkPositionsToLoad())
        {
            ChunkManager.forceChunk(level, pos);
        }
        data.addRequest(request);
    }

    public static void removeRequest(String requestId)
    {
        ChunkManagerRequest request = ACTIVE_REQUESTS.remove(requestId);
        if (request != null)
        {
            ServerLevel level = request.getDimension();
            ChunkLoadedData data = ChunkLoadedData.getChunkData(level);
            for (ChunkPos pos : request.getChunkPositionsToLoad())
            {
                ChunkManager.unforceChunk(level, pos);
            }
            data.removeRequest(requestId);
        }
    }

    public static void tick()
    {
        List<String> toRemove = new ArrayList<>();
        for (Map.Entry<String, ChunkManagerRequest> entry : ACTIVE_REQUESTS.entrySet())
        {
            ChunkManagerRequest request = entry.getValue();
            request.decrementTicksUntilExpiration(1);
            if (request.isExpired() && !request.refreshIfOwnerStillPresent(request.getTickAmount()))
            {
                toRemove.add(request.getRequestID());
            }
        }

        for (String id : toRemove)
        {
            removeRequest(id);
        }
    }

    public static void forceChunk(ServerLevel level, ChunkPos pos)
    {
        level.getChunkSource().addRegionTicket(ChunkTickets.CHUNK_LOADER, pos, 2, pos);
    }

    public static void unforceChunk(ServerLevel level, ChunkPos pos)
    {
        level.getChunkSource().removeRegionTicket(ChunkTickets.CHUNK_LOADER, pos, 2, pos);
    }
}
