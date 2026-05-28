package com.crimsonwarpedcraft.exampleplugin.techniques;

import com.crimsonwarpedcraft.exampleplugin.data.PlayerProfile;
import org.bukkit.entity.Player;

public class MegumiKit {
    public static void castDivineDog(Player player, PlayerProfile profile) {
        if (profile.getCursedEnergy() < 20) {
            player.sendMessage("§cNot enough Cursed Energy (20 required)!");
            return;
        }
        profile.setCursedEnergy(profile.getCursedEnergy() - 20);
        player.sendMessage("§8§lDivine Dog: Totality! §7(Shadow shikigami manifested...)");
    }

    public static void castMahoraga(Player player, PlayerProfile profile) {
        if (profile.getCursedEnergy() < 150) {
            player.sendMessage("§cNot enough Cursed Energy (150 required)!");
            return;
        }
        profile.setCursedEnergy(profile.getCursedEnergy() - 150);
        player.sendMessage("§5§lWith this treasure, I summon... §dEight-Handled Sword Divergent Sila Divine General Mahoraga!");
    }

    public static void openShadowInventory(Player player) {
        player.sendMessage("§8§lSinking into the shadow... §7(Shadow inventory opened!)");
        // Custom inventory hook UI logic can be expanded here later
    }
}
