package io.github.bindglam.cubepie;

import io.github.bindglam.cubepie.gui.CustomTitleScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.TitleScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.event.TickEvent;

@OnlyIn(value = Dist.CLIENT)
public class CubePieClient {
    @Mod.EventBusSubscriber(modid = CubePie.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onScreenInit(ScreenEvent.Init.Pre event) {
            if (event.getScreen() instanceof TitleScreen) {
                event.setCanceled(true);

                CubePie.mcClient.setScreen(new CustomTitleScreen());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = CubePie.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModEvents {
        @SubscribeEvent
        public static void onClientSetup(final FMLClientSetupEvent event) {
            CubePie.LOGGER.info("Client Core Starting...");

            CubePie.mcClient = Minecraft.getInstance();
        }
    }
}
