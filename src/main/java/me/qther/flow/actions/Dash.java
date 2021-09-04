package me.qther.flow.actions;

import me.qther.flow.events.Capabilities;
import me.qther.flow.init.Flow;
import me.qther.flow.network.PacketIdentifiers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Dash {
    @Environment(EnvType.CLIENT)
    public static void sendDash(PlayerEntity player) {
        ClientPlayNetworking.send(PacketIdentifiers.DASH_PACKET_ID, PacketByteBufs.empty());
    }

    public static void dash(ServerPlayerEntity player) {
        if (player == null || (Flow.CONFIG.dashing.onGround && !player.isOnGround()) || !Flow.CONFIG.dashing.enabled) return;

        Vec3d rot = player.getRotationVector().normalize();
        Vec3d straightRot = rot.subtract(0.0, rot.y - (Flow.CONFIG.dashing.resetFall ? 0.00001 : 0.0), 0.0).normalize();
        player.setVelocity(straightRot.multiply(Flow.CONFIG.dashing.multiplier));
        player.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(player));
        player.setNoGravity(true);
        player.setJumping(false);
        player.addExhaustion(Flow.CONFIG.dashing.exhaustion);

        Capabilities.DASHING.put(player, Flow.CONFIG.dashing.ticks);
        Capabilities.DASH_COOLDOWN.put(player, Flow.CONFIG.dashing.ticks + Flow.CONFIG.dashing.cooldown);
    }
}
