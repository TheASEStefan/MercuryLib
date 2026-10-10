package net.mercury.mercurymod.event;

import net.minecraft.client.gui.GuiGraphics;
import net.mercury.mercurymod.network.ClientHelper;

public interface HudRenderCallback
{
    Event<HudRenderCallback> EVENT = new ClientHelper.HudRenderEvent();
    void onHudRender(GuiGraphics context, float tickDelta);
}
