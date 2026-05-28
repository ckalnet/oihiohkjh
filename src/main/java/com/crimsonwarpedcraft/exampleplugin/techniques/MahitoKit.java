package com.crimsonwarpedcraft.exampleplugin.techniques;

import com.crimsonwarpedcraft.exampleplugin.data.PlayerProfile;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Husk;

public class MahitoKit {
    public static void castSoulStrike(Player player, PlayerProfile profile, FileConfiguration config) {
        int ceCost = config.getInt("techniques.mahito.soulstrike-ce-cost", 35);
        double damage = config.getDouble("techniques.mahito.soulstrike-damage", 14.0);

        if (profile.getCursedEnergy() < ceCost) {
            player.sendMessage("§cNot enough Cursed Energy (" + ceCost + " required)!");
            return;
        }

        var rayTrace = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getLocation().getDirection(), 4.0);
        if (rayTrace == null || !(rayTrace.getHitEntity() instanceof LivingEntity target)) {
            player.sendMessage("§cNo soul within arm's reach!");
            return;
        }

        profile.setCursedEnergy(profile.getCursedEnergy() - ceCost);
        player.sendMessage("§d§lIdle Transfiguration!");

        target.damage(damage, player); 
        Location loc = target.getLocation().add(0,1,0);
        loc.getWorld().spawnParticle(Particle.WITCH, loc, 25, 0.3, 0.5, 0.3);
        loc.getWorld().playSound(loc, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1.0F, 0.7F);
    }

    public static void castPolymorphicIsomer(Player player, PlayerProfile profile, FileConfiguration config) {
        int ceCost = config.getInt("techniques.mahito.isomer-ce-cost", 70);

        if (profile.getCursedEnergy() < ceCost) {
            player.sendMessage("§cNot enough Cursed Energy (" + ceCost + " required)!");
            return;
        }
        profile.setCursedEnergy(profile.getCursedEnergy() - ceCost);
        player.sendMessage("§5§lPolymorphic Isomer!");

        Location spawnLoc = player.getLocation().add(player.getLocation().getDirection().multiply(2));
        Husk isomer = (Husk) player.getWorld().spawnEntity(spawnLoc, EntityType.HUSK);
        isomer.setCustomName("§5§lPolymorphic Isomer");
        isomer.setCustomNameVisible(true);
        isomer.setBaby(false);
        
        spawnLoc.getWorld().spawnParticle(Particle.ENTITY_EFFECT, spawnLoc, 30);
        spawnLoc.getWorld().playSound(spawnLoc, Sound.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED, 1.0F, 0.5F);
    }
}
