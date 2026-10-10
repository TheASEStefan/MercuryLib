package net.mercury.mercurymod.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mercury.mercurymod.mod.Mercury;
import net.mercury.mercurymod.network.ClientHelper;

@Mod.EventBusSubscriber(modid = Mercury.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents
{
    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event)
    {
        ClientHelper.onTooltip.forEach((action) -> action.getTooltip(event.getItemStack(), event.getFlags(), event.getToolTip()));
    }

    @SubscribeEvent
    public static void onRenderHud(RenderGuiEvent.Post event)
    {
        ClientHelper.onRenderHud.forEach((action) -> action.onHudRender(event.getGuiGraphics(), event.getPartialTick()));
    }

    @SubscribeEvent
    public static void onViewportCameraAngles(ViewportEvent.ComputeCameraAngles event)
    {
        ClientHelper.onViewportCamera.forEach((action) -> action.onComputingCameraAngles(event.getCamera(), event.getPitch(), event.getYaw(), event.getRoll()));
    }
}
