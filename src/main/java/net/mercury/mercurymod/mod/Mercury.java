package net.mercury.mercurymod.mod;

import com.mojang.logging.LogUtils;
import net.mercury.mercurymod.chunk.ChunkDataRegistry;
import net.mercury.mercurymod.example.ExamplePacket;
import net.mercury.mercurymod.screenshake.ScreenShakeEvent;
import net.mercury.mercurymod.screenshake.ScreenShakeRegistry;
import net.mercury.mercurymod.screenshake.ScreenShakeRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mercury.mercurymod.network.NetworkHandler;
import org.slf4j.Logger;


@Mod(Mercury.MOD_ID)
public class Mercury
{
    public static final String MOD_ID = "mercury";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Mercury()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();
        MinecraftForge.EVENT_BUS.register(this);

        new ExamplePacket().onInitialize(); // Example
        NetworkHandler.registerMessages();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::commonSetup);

        ScreenShakeRegistry.register(modEventBus);
        ChunkDataRegistry.registerChunkData();

        LOGGER.info("Mercury has loaded successfully!");
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Remember to call this as well
            MinecraftForge.EVENT_BUS.register(new ScreenShakeEvent());
            EntityRenderers.register(ScreenShakeRegistry.SCREEN_SHAKE.get(), ScreenShakeRenderer::new);
        }
    }

}