package io.github.bindglam.cubepie.mixin;

import io.github.bindglam.cubepie.CubePie;
import io.github.bindglam.cubepie.gui.CustomTitleScreen;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(value = Dist.CLIENT)
@Mixin(value = PauseScreen.class)
public class PauseScreenMixin {
    @Inject(method = "onDisconnect", at = @At("HEAD"), cancellable = true)
    private void onDisconnect(CallbackInfo info){
        boolean flag = CubePie.mcClient.isLocalServer();
        assert CubePie.mcClient.level != null;
        CubePie.mcClient.level.disconnect();
        if (flag) {
            CubePie.mcClient.disconnect(new GenericDirtMessageScreen(Component.translatable("menu.savingLevel")));
        } else {
            CubePie.mcClient.disconnect();
        }

        CubePie.mcClient.setScreen(new CustomTitleScreen());
        info.cancel();
    }
}
