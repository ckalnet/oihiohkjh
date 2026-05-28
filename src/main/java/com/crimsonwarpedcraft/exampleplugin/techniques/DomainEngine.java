package com.crimsonwarpedcraft.exampleplugin.techniques;

import com.crimsonwarpedcraft.exampleplugin.data.PlayerProfile;
import org.bukkit.entity.Player;

public class DomainEngine {
    public static void launchDomain(Player player, PlayerProfile profile) {
        // Spend the required 500 Cursed Energy to initiate expansion barriers
        profile.setCursedEnergy(profile.getCursedEnergy() - 500);
        
        String domainName = switch (profile.getTechnique()) {
            case GOJO -> "§d§lInfinite Void";
            case SUKUNA -> "§4§lMalevolent Shrine";
            case MEGUMI -> "§8§lChimera Shadow Garden";
            case MAHITO -> "§5§lSelf-Embodiment of Perfection";
            default -> "§f§lInnate Domain";
        };

        player.sendMessage("§0§k||§r " + domainName + " §r§0§k||§r §d§lEXPANSION!");
        
        // Put the player on Domain Burnout loop cycle
        profile.setBurnedOut(true);
        player.sendMessage("§cYour brain has burned out! Your technique is temporarily offline.");
    }
}
