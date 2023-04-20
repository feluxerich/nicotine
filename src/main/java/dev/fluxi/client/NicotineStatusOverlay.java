package dev.fluxi.client;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.fluxi.NicotineMod;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class NicotineStatusOverlay extends DrawableHelper implements HudRenderCallback {
    private static final Identifier NICOTINE_STATUS = new Identifier(NicotineMod.MOD_ID, "textures/hud/nicotine.png");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        int width = client.getWindow().getScaledWidth() / 2 - 8;
        int height = client.getWindow().getScaledHeight() - 46;

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, NICOTINE_STATUS);

        drawTexture(matrixStack, width, height, 0, 0, 16, 16);
    }
}
