package net.mercury.mercurymod.event;

import net.mercury.mercurymod.network.ClientHelper;
import net.minecraft.client.Camera;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public interface ViewportCameraCallback
{
    Event<ViewportCameraCallback> EVENT = new ClientHelper.ViewportCameraEvent();
    void onComputingCameraAngles(Camera camera, float pitch, float yaw, float roll);
}
