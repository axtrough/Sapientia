package net.raccoon.will.sapientia.client.event;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.raccoon.will.sapientia.api.client.gui.GuiManager;
import net.raccoon.will.sapientia.core.Sapientia;
import net.raccoon.will.sapientia.core.registry.SapGuiElements;

@EventBusSubscriber(modid = Sapientia.MODID, value = Dist.CLIENT)
public class GuiEvents {

    @SubscribeEvent
    public static void onRenderGenericGui(RenderGuiEvent.Pre event) {
        GuiGraphics guiGraphics = event.getGuiGraphics();
        Minecraft minecraft = Minecraft.getInstance();
        Window window = minecraft.getWindow();
        Player player = minecraft.player;

        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();
        var stack = player.getMainHandItem();

        SapGuiElements.itemHeld().setItem(stack);
        SapGuiElements.textHeld().setText(stack.getHoverName());

        if (player.isCrouching()) {
            SapGuiElements.textHeld().setColor(0x1e2bba);
        } else {
            SapGuiElements.textHeld().resetColor();
        }

        GuiManager.render(guiGraphics, screenWidth, screenHeight, event);
    }
}

