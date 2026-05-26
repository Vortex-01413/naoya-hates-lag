
package com.naoya.lag.mixin.particle;

import com.naoya.lag.core.particle.ParticleLimiter;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class ParticleMixin {

    @Inject(method = "addParticle(Lnet/minecraft/client/particle/Particle;)V", at = @At("HEAD"), cancellable = true)
    private void naoya$particleLimit(Particle particle, CallbackInfo ci) {
        if (!ParticleLimiter.shouldSpawnParticle()) {
            ci.cancel();
        }
    }
}
