package net.mercury.mercurymod.screenshake;

import net.mercury.mercurymod.mod.Mercury;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ScreenShakeRegistry
{

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Mercury.MOD_ID);

    public static final RegistryObject<EntityType<ScreenShakeUtils>> SCREEN_SHAKE = ENTITY_TYPES.register("screen_shake", () -> EntityType.Builder.<ScreenShakeUtils>of(ScreenShakeUtils::new, MobCategory.MISC)
            .noSummon()
            .sized(1.0F, 1.0F)
            .setUpdateInterval(Integer.MAX_VALUE)
            .build(Mercury.MOD_ID + ":screen_shake"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
