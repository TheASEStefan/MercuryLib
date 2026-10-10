package net.mercury.mercurymod.screenshake;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;


public class ScreenShakeEvent
{
    @SubscribeEvent
    public void onCameraSetup(ViewportEvent.ComputeCameraAngles event)
    {
        Player player = Minecraft.getInstance().player;
        if (player != null)
        {
            float delta = Minecraft.getInstance().getFrameTime();
            float ticksExistedDelta = player.tickCount + delta;
            float shakeAmplitude = 0, maximumShakeAmplitude = 0;
            List<ScreenShakeUtils> screenShakerSpelunkers = player.level().getEntitiesOfClass(ScreenShakeUtils.class, player.getBoundingBox().inflate(20, 20, 20));
            for (ScreenShakeUtils ScreenShake : screenShakerSpelunkers)
            {
                if (ScreenShake.distanceTo(player) < ScreenShake.getRadius())
                {
                    float shakeAmount = ScreenShake.getShakeAmount(player, delta);
                    shakeAmplitude += shakeAmount;
                    maximumShakeAmplitude = Math.max(maximumShakeAmplitude, shakeAmount);
                }

                shakeAmplitude = Math.min(shakeAmplitude, maximumShakeAmplitude * maximumShakeAmplitude);
                event.setPitch((float) (event.getPitch() + shakeAmplitude * Math.cos(ticksExistedDelta * 3 + 2) * 25));
                event.setYaw((float) (event.getYaw() + shakeAmplitude * Math.cos(ticksExistedDelta * 5 + 1) * 25));
                event.setRoll((float) (event.getRoll() + shakeAmplitude * Math.cos(ticksExistedDelta * 4) * 25));
            }
        }
    }
}
