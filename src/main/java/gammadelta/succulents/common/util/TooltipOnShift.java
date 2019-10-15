package gammadelta.succulents.common.util;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.util.List;

public final class TooltipOnShift {
    public static void Do(List<String> tooltip, String prefix) {
        if (!GuiScreen.isShiftKeyDown())
            tooltip.add(I18n.format("util.shift_for_tooltip"));
        else
            tooltip.add(I18n.format(prefix + ".tooltip"));
    }
}
