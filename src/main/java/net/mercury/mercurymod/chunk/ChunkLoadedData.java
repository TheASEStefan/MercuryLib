package net.mercury.mercurymod.chunk;

import com.google.common.collect.Maps;
import net.mercury.mercurymod.mod.Mercury;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;

public class ChunkLoadedData extends SavedData
{
    private static ChunkLoadedData serverInstance = null;
    public static final Map<String, ChunkManagerRequest> activeRequests = Maps.newHashMap();

    public void addRequest(ChunkManagerRequest request)
    {
        activeRequests.put(request.getRequestID(), request);
        setDirty();
    }

    public void removeRequest(String id)
    {
        activeRequests.remove(id);
        setDirty();
    }

    public Collection<ChunkManagerRequest> getRequests()
    {
        return activeRequests.values();
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag nbt)
    {
        var listTag = new ListTag();
        for (ChunkManagerRequest request : activeRequests.values())
        {
            listTag.add(request.serializeNBT());
        }
        nbt.put("MercuryChunkRequests", listTag);

        return nbt;
    }

    public static ChunkLoadedData fromNBT(CompoundTag nbt)
    {
        ChunkLoadedData worldDataRegistry = new ChunkLoadedData();
        if (nbt.contains("MercuryChunkRequests", 9))
        {
            var list = nbt.getList("MercuryChunkRequests", 10);
            for (int i = 0; i < list.size(); i++)
            {
                CompoundTag entry = list.getCompound(i);
                ChunkManagerRequest request = ChunkManagerRequest.deserializeNBT(entry);
                activeRequests.put(request.getRequestID(), request);
            }
        }
        return worldDataRegistry;
    }

    public static ChunkLoadedData getChunkData(ServerLevel world)
    {
        if (serverInstance != null)
        {
            return serverInstance;
        }

        serverInstance = world.getDataStorage().computeIfAbsent(ChunkLoadedData::fromNBT, ChunkLoadedData::new, Mercury.MOD_ID);
        return serverInstance;
    }

    public static void clearData()
    {
        serverInstance = null;
    }
}
