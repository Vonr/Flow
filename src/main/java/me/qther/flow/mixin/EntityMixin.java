package me.qther.flow.mixin;

import me.qther.flow.events.Capabilities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
	@Environment(EnvType.SERVER)
	@Inject(at = @At("HEAD"), method = "setVelocity(Lnet/minecraft/util/math/Vec3d;)V", cancellable = true)
	private void setVelocity(Vec3d velocity, CallbackInfo ci) {
		Entity entity = (Entity) (Object) this;
		if (Capabilities.DASHING.containsKey(entity)) {
			ci.cancel();
		}
	}
}
