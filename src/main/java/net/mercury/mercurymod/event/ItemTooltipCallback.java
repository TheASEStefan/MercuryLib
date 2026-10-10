package net.mercury.mercurymod.event;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.mercury.mercurymod.network.ClientHelper;

import java.util.List;

public interface ItemTooltipCallback
{
    Event<ItemTooltipCallback> EVENT = new ClientHelper.TooltipEvent();
    void getTooltip(ItemStack stack, TooltipFlag context, List<Component> lines);
}
