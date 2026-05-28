package com.crimsonwarpedcraft.exampleplugin.techniques;

import com.crimsonwarpedcraft.exampleplugin.data.PlayerProfile;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SukunaKit {
    public static void castDismantle(Player player, PlayerProfile profile, FileConfiguration config) {
        int ceCost = config.getInt("techniques.sukuna.dismantle-ce-cost", 15);
        double damage = config.getDouble("techniques.sukuna.dismantle-damage", 7.0);

        if (profile.getCursedEnergy() < ceCost) {
            player.sendMessage("§cNot enough Cursed Energy (" + ceCost + " required)!");
            return;
        }
        profile.setCursedEnergy(profile.getCursedEnergy() - ceCost);
        player.sendMessage("§7§lDismantle!");

        Location eyeLoc = player.getEyeLocation();
        Vector direction = eyeLoc.getDirection().normalize();

        for (int i = 1; i <= 16; i++) {
            Location point = eyeLoc.clone().add(direction.clone().multiply(i));
            point.getWorld().spawnParticle(Particle.DUST, point, 2, 0.05, 0.05, 0.05, new Particle.DustOptions(Color.GRAY, 0.8F));

            for (Entity entity : point.getWorld().getNearbyEntities(point, 0.8, 0.8, 0.8)) {
                if (entity instanceof LivingEntity target && !entity.equals(player)) {
                    target.damage(damage, player); 
                    target.getWorld().spawnParticle(Particle.SWEEP_ATTACK, target.getLocation().add(0,1,0), 1);
                    target.getWorld().playSound(target.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0F, 1.2F);
                    return;
                }
            }
        }
    }

    public static void castCleave(Player player, PlayerProfile profile, FileConfiguration config) {
        int ceCost = config.getInt("techniques.sukuna.cleave-ce-cost", 30);
        double damage = config.getDouble("techniques.sukuna.cleave-damage", 15.0);

        if (profile.getCursedEnergy() < ceCost) {
            player.sendMessage("§cNot enough Cursed Energy (" + ceCost + " required)!");
            return;
        }

        var rayTrace = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getLocation().getDirection(), 3.5);
        if (rayTrace == null || !(rayTrace.getHitEntity() instanceof LivingEntity target)) {
            player.sendMessage("§cNo target close enough to Cleave!");
            return;
        }

        profile.setCursedEnergy(profile.getCursedEnergy() - ceCost);
        player.sendMessage("§4§lCleave!");

        target.damage(damage, player); 
        Location targetLoc = target.getLocation().add(0, 1, 0);
        targetLoc.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, targetLoc, 12, 0.2, 0.4, 0.2);
        targetLoc.getWorld().playSound(targetLoc, Sound.ENTITY_WITHER_BREAK_BLOCK, 0.8F, 1.5F);
    }
}
