package me.qther.flow.mixin;

import me.qther.flow.Capabilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Inject(at = @At("HEAD"), method = "setHealth", cancellable = true)
	private void setHealth(float health, CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (Capabilities.INVINCIBLE.containsKey(entity)) {
			if (health <= entity.getHealth()) {
				ci.cancel();
			}
		}
	}

	@Inject(at = @At("HEAD"), method = "damage", cancellable = true)
	private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (Capabilities.INVINCIBLE.containsKey(entity)) {
			cir.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "jump", cancellable = true)
	private void jump(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (Capabilities.INVINCIBLE.containsKey(entity)) {
			ci.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "takeKnockback", cancellable = true)
	private void takeKnockback(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (Capabilities.INVINCIBLE.containsKey(entity)) {
			ci.cancel();
		}
	}
}
