package cn.bingosplash.events;

import cn.bingosplash.BingoSplashCN;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class EventHandler {
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT || BingoSplashCN.titleManager.getLastMessage() == null) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fontRenderer = mc.fontRendererObj;

        String message = BingoSplashCN.titleManager.getLastMessage();
        int x = (event.resolution.getScaledWidth() - fontRenderer.getStringWidth(message)) / 2;
        int y = 66;
        int color = 0xFFFFFF;

        fontRenderer.drawString(message, x, y, color);
        // 等待5秒消失字符串, 按照DragPrio的ctjs写法
        new Thread(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BingoSplashCN.titleManager.setLastMessage(null);
        }).start();
    }
}