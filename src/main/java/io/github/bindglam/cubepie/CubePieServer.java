package io.github.bindglam.cubepie;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

@OnlyIn(value = Dist.DEDICATED_SERVER)
public class CubePieServer {
    @Mod.EventBusSubscriber(modid = CubePie.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
    public static class ForgeEvents {
    }

    @Mod.EventBusSubscriber(modid = CubePie.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public static class ModEvents {
        @SubscribeEvent
        public static void onServerSetup(final FMLDedicatedServerSetupEvent event) {
            CubePie.LOGGER.info("Server Core Starting...");

            CubePie.mcServer = ServerLifecycleHooks.getCurrentServer();
        }
    }
}
