package com.crimsonwarpedcraft.exampleplugin.techniques;

import com.crimsonwarpedcraft.exampleplugin.data.PlayerProfile;
import org.bukkit.entity.Player;

public class GojoKit {
    public static void castBlue(Player player, PlayerProfile profile) {
        if (profile.getCursedEnergy() < 25) {
            player.sendMessage("§cNot enough Cursed Energy (25 required)!");
            return;
        }
        profile.setCursedEnergy(profile.getCursedEnergy() - 25);
        player.sendMessage("§9§lLapse Blue! §7(Attracting vectors established...)");
    }

    public static void castRed(Player player, PlayerProfile profile) {
        if (profile.getCursedEnergy() < 50) {
            player.sendMessage("§cNot enough Cursed Energy (50 required)!");
            return;
        }
        profile.setCursedEnergy(profile.getCursedEnergy() - 50);
        player.sendMessage("§c§lReversal Red! §7(Repelling shockwave unleashed...)");
    }
}
