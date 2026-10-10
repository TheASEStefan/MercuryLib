package net.mercury.mercurymod.chunk;

import net.mercury.mercurymod.mod.Mercury;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.ChunkPos;

import java.util.Comparator;

public class ChunkTickets
{
    public static final TicketType<ChunkPos> CHUNK_LOADER = TicketType.create(Mercury.MOD_ID + "chunk_loader", Comparator.comparingLong(ChunkPos::toLong));
}
