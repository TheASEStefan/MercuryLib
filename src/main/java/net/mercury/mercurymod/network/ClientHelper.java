package net.mercury.mercurymod.network;

import net.mercury.mercurymod.event.Event;
import net.mercury.mercurymod.event.HudRenderCallback;
import net.mercury.mercurymod.event.ItemTooltipCallback;
import net.mercury.mercurymod.event.ViewportCameraCallback;

import java.util.ArrayList;
import java.util.List;

public class ClientHelper
{
    public static List<ItemTooltipCallback> onTooltip = new ArrayList<>();
    public static List<HudRenderCallback> onRenderHud = new ArrayList<>();
    public static List<ViewportCameraCallback> onViewportCamera = new ArrayList<>();

    public static class TooltipEvent extends Event<ItemTooltipCallback>
    {
        public void register(ItemTooltipCallback listener)
        {
            onTooltip.add(listener);
        }
    }

    public static class HudRenderEvent extends Event<HudRenderCallback>
    {
        public void register(HudRenderCallback listener)
        {
            onRenderHud.add(listener);
        }
    }

    public static class ViewportCameraEvent extends Event<ViewportCameraCallback>
    {
        public void register(ViewportCameraCallback listener)
        {
            onViewportCamera.add(listener);
        }
    }
}
