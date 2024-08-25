package io.github.bindglam.cubepie.mixin;

import com.mojang.blaze3d.platform.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Window.class)
public class WindowMixin {
    @Shadow @Final private long window;

    @Inject(method = "setTitle", at = @At("HEAD"), cancellable = true)
    public void setTitle(String pTitle, CallbackInfo info){
        GLFW.glfwSetWindowTitle(this.window, "CubePie");
        info.cancel();
    }
}
