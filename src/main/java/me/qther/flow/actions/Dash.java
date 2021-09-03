package me.qther.flow.actions;

import me.qther.flow.Capabilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Dash {
    public static void dash(PlayerEntity player) {
        if (player == null || !player.isOnGround()) return;

//        Vec3d from = player.getPos();
        Vec3d rot = player.getRotationVector().normalize();
        Vec3d straightRot = rot.subtract(0.0, rot.y, 0.0).normalize();
//        Vec3d to = from.add(straightRot.multiply(3.0));
//        HitResult hitResult = player.world.raycast(new RaycastContext(from, to, ShapeType.COLLIDER, FluidHandling.ANY, player));
//        Flow.LOGGER.info(hitResult.getType());
//        if (hitResult.getType() == Type.BLOCK) {
//            player.updatePosition(hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
//        } else {
//            player.updatePosition(to.x, to.y, to.z);
//        }
        // TODO: Config
        player.setVelocity(straightRot.multiply(0.7));
        player.setInvulnerable(true);
        player.setNoGravity(true);
        player.setJumping(false);
        player.addExhaustion(2F);

        // Make player invincible for 10 ticks and adds 30 ticks of cooldown
        Capabilities.INVINCIBLE.put(player, 10);
        Capabilities.DASH_COOLDOWN.put(player, 30);
    }
}
