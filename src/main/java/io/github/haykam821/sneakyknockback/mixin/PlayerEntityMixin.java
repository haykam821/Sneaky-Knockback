package io.github.haykam821.sneakyknockback.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {
	public PlayerEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "method_30263", at = @At("HEAD"), cancellable = true)
	private void simplifyServerConditions(CallbackInfoReturnable<Boolean> ci) {
		if (!this.getEntityWorld().isClient()) {
			ci.setReturnValue(this.onGround);
		}
	}
}
