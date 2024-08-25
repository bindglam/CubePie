package io.github.bindglam.cubepie.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.bindglam.cubepie.CubePie;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.*;

@OnlyIn(value = Dist.CLIENT)
public class CustomTitleScreen extends Screen {
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private static final ResourceLocation WEIRDHUB_ICON = new ResourceLocation(CubePie.MODID, "textures/gui/title/weirdhub.png");

    private final PanoramaRenderer panorama = new PanoramaRenderer(TitleScreen.CUBE_MAP);

    public CustomTitleScreen() {
        super(Component.translatable("narrator.screen.title"));
    }

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(Component.translatable("cubepie.title.tutorial"), button -> {
            CubePie.mcClient.setScreen(new SelectWorldScreen(this));
        }).bounds(2, this.height / 2 - 22, 100, 20).build());

        this.addRenderableWidget(Button.builder(Component.translatable("cubepie.title.play"), button -> {
            ConnectScreen.startConnecting(this, CubePie.mcClient, ServerAddress.parseString("play.wrd.kr:25737"), new ServerData("WeirdServer", "play.wrd.kr:25737", ServerData.Type.OTHER), false);
        }).bounds(2, this.height / 2, 100, 20).build());

        this.addRenderableWidget(Button.builder(Component.translatable("menu.options"), button ->{
            CubePie.mcClient.setScreen(new OptionsScreen(this, CubePie.mcClient.options));
        }).bounds(2, this.height / 2 + 22, 100, 20).build());

        this.addRenderableWidget(Button.builder(Component.translatable("menu.quit"), button ->{
            CubePie.mcClient.stop();
        }).bounds(2, this.height / 2 + 44, 100, 20).build());
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        float f = Util.getMillis() / 1000.0F;
        this.panorama.render(pPartialTick, Mth.clamp(f, 0.0F, 1.0F));
        RenderSystem.enableBlend();
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, (float)Mth.ceil(Mth.clamp(f, 0.0F, 1.0F)));
        pGuiGraphics.blit(PANORAMA_OVERLAY, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        pGuiGraphics.fill(0, 0, 104, this.height, FastColor.ARGB32.color(70, 0, 0, 0));

        PoseStack pose = pGuiGraphics.pose();
        pose.pushPose();
        pose.translate(-15f, -15f, 0f);
        pose.scale(0.5f, 0.45f, 0.5f);
        pGuiGraphics.blit(WEIRDHUB_ICON, 0, 0, 0, 0, 251, 240);
        pose.popPose();
    }
}
