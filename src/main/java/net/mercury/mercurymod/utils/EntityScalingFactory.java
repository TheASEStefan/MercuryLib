package net.mercury.mercurymod.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;

/* Taken from Dawn of the Flood & improved. */
public class EntityScalingFactory
{
    private float entityScaleFactor = 0.0f;
    private boolean hasAppliedScaling = false;

    public EntityScalingFactory()
    {
    }

    public static EntityScalingFactory create()
    {
        return new EntityScalingFactory();
    }

    public float getEntityScaleFactor()
    {
        return entityScaleFactor;
    }

    public void setEntityScaleFactor(float entityScaleFactor)
    {
        this.entityScaleFactor = entityScaleFactor;
    }

    public boolean hasAppliedScaling()
    {
        return this.hasAppliedScaling;
    }

    public void applyScaling()
    {
        this.hasAppliedScaling = true;
    }

    public void writeToNbt(CompoundTag compoundTag)
    {
        compoundTag.putFloat("entityScaleFactor", this.getEntityScaleFactor());
    }

    public void readFromNbt(CompoundTag compoundTag)
    {
        this.setEntityScaleFactor(compoundTag.getFloat("entityScaleFactor"));
    }

    // To be called when spawning the entity.
    public void randomizeScale(RandomSource randomSource, float pFactor)
    {
        float scalingMul = randomSource.nextFloat() / pFactor;
        this.setEntityScaleFactor(this.getEntityScaleFactor() + scalingMul);
        this.applyScaling();
    }

    // Get a newly created scaled entity hitbox
    public EntityDimensions getScaledEntityHitbox(Entity entity)
    {
        EntityDimensions originalDimensions = EntityDimensions.fixed(entity.getBbWidth(), entity.getBbHeight());
        if (!this.hasAppliedScaling())
            return originalDimensions;

        float newWidth = originalDimensions.width + this.getEntityScaleFactor();
        float newHeight = originalDimensions.height + this.getEntityScaleFactor();
        return EntityDimensions.scalable(newWidth, newHeight);
    }

    // Applies the scaling to the posestack as well, should be called in the entity's renderer.
    public void applyToStack(PoseStack poseStack, float beginScale)
    {
        float uniformScale = beginScale + this.getEntityScaleFactor();
        poseStack.scale(uniformScale, uniformScale, uniformScale);
    }
}
