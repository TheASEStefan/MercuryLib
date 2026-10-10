package net.mercury.mercurymod.forge;

import net.mercury.mercurymod.example.ExampleClientPacket;
import net.mercury.mercurymod.mod.Mercury;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mercury.mercurymod.event.ClientLifecycleEvents;

@Mod.EventBusSubscriber(modid = Mercury.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClientModEvents
{

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        new ExampleClientPacket().onInitialize(); // Example
        ClientLifecycleEvents.onClientStarted.forEach((action) -> action.onClientStarted(Minecraft.getInstance()));
    }
}
