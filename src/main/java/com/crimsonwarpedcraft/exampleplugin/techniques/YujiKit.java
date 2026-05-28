package com.crimsonwarpedcraft.exampleplugin.techniques;

import com.crimsonwarpedcraft.exampleplugin.data.PlayerProfile;
import org.bukkit.entity.Player;

public class YujiKit {
    public static void toggleDivergentFist(Player player, PlayerProfile profile) {
        if (profile.getCursedEnergy() < 10) {
            player.sendMessage("§cNot enough Cursed Energy (10 required)!");
            return;
        }
        profile.setCursedEnergy(profile.getCursedEnergy() - 10);
        player.sendMessage("§e§lDivergent Fist! §7(Cursed energy lagging behind mechanical impact...)");
    }

    public static void castManjiKick(Player player, PlayerProfile profile) {
        if (profile.getCursedEnergy() < 35) {
            player.sendMessage("§cNot enough Cursed Energy (35 required)!");
            return;
        }
        profile.setCursedEnergy(profile.getCursedEnergy() - 35);
        player.sendMessage("§6§lManji Kick! §7(Rotational trajectory momentum triggered...)");
    }
}
