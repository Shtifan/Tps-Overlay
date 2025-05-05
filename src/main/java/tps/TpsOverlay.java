package tps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class TpsOverlay implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.options == null)
                return;

            String tpsText = String.format("TPS: %.2f", TpsCounter.getTps());
            context.drawText(client.textRenderer, tpsText, 5, 5, 0xFFFFFF, true);
        });
    }
}
