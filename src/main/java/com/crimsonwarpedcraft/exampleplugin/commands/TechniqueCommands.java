package com.crimsonwarpedcraft.exampleplugin.commands;

import com.crimsonwarpedcraft.exampleplugin.data.PlayerProfile;
import com.crimsonwarpedcraft.exampleplugin.data.ProfileManager;
import com.crimsonwarpedcraft.exampleplugin.data.TechniqueType;
import com.crimsonwarpedcraft.exampleplugin.techniques.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TechniqueCommands implements CommandExecutor {

    private final ProfileManager profileManager;

    // Inject the profile manager into our command engine constructor
    public TechniqueCommands(ProfileManager manager) {
        this.profileManager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // 1. Ensure a console block or remote terminal didn't run this command
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cOnly players can execute cursed techniques!");
            return true;
        }

        // 2. Enforce usage arguments
        if (args.length == 0) {
            player.sendMessage("§cUsage: /jjk <ability1|ability2|domain|shadowinv|setclass>");
            return true;
        }

        // 3. Fetch the player's live profile data from RAM
        PlayerProfile profile = profileManager.getProfile(player.getUniqueId());
        if (profile == null) return true;

        String action = args[0].toLowerCase();

        // 4. Admin Command: Set a player's character class for testing (/jjk setclass <type>)
        if (action.equals("setclass")) {
            if (args.length < 2) {
                player.sendMessage("§cSpecify a class: NONE, GOJO, SUKUNA, MEGUMI, YUJI, INUMAKI, MAHITO");
                return true;
            }
            try {
                TechniqueType type = TechniqueType.valueOf(args[1].toUpperCase());
                profile.setTechnique(type);
                player.sendMessage("§aYour Innate Technique has been set to: §e" + type.name());
            } catch (IllegalArgumentException e) {
                player.sendMessage("§cInvalid technique class template name!");
            }
            return true;
        }

        // 5. Anti-Cheat: Block casting if player is suffering from Domain Burnout
        if (profile.isBurnedOut()) {
            player.sendMessage("§cYour brain is short-circuited from Domain Burnout! Your technique is temporarily unusable.");
            return true;
        }

        // 6. Route structural combat subcommands down the execution tree
        switch (action) {
            case "ability1" -> routeAbilityOne(player, profile);
            case "ability2" -> routeAbilityTwo(player, profile);
            case "domain" -> routeDomainExpansion(player, profile);
            case "shadowinv" -> routeShadowInventory(player, profile);
            default -> player.sendMessage("§cUnknown jujutsu action. Use ability1, ability2, domain, shadowinv, or setclass.");
        }

        return true;
    }

    private void routeAbilityOne(Player player, PlayerProfile profile) {
        switch (profile.getTechnique()) {
            case GOJO -> GojoKit.castBlue(player, profile);
            case SUKUNA -> SukunaKit.castDismantle(player, profile);
            case MEGUMI -> MegumiKit.castDivineDog(player, profile);
            case YUJI -> YujiKit.toggleDivergentFist(player, profile);
            case INUMAKI -> InumakiKit.castDontMove(player, profile);
            case MAHITO -> MahitoKit.castSoulStrike(player, profile);
            case NONE -> player.sendMessage("§cYou do not possess an Innate Cursed Technique! Use /jjk setclass to choose one.");
        }
    }

    private void routeAbilityTwo(Player player, PlayerProfile profile) {
        switch (profile.getTechnique()) {
            case GOJO -> GojoKit.castRed(player, profile);
            case SUKUNA -> SukunaKit.castCleave(player, profile);
            case MEGUMI -> MegumiKit.castMahoraga(player, profile);
            case YUJI -> YujiKit.castManjiKick(player, profile);
            case INUMAKI -> InumakiKit.castPlunge(player, profile);
            case MAHITO -> MahitoKit.castPolymorphicIsomer(player, profile);
            case NONE -> player.sendMessage("§cYou do not possess an Innate Cursed Technique! Use /jjk setclass to choose one.");
        }
    }

    private void routeDomainExpansion(Player player, PlayerProfile profile) {
        if (profile.getTechnique() == TechniqueType.NONE) {
            player.sendMessage("§cSorcerers without techniques cannot construct an Innate Domain!");
            return;
        }
        if (profile.getCursedEnergy() < 500) {
            player.sendMessage("§cYou lack the 500 Cursed Energy required to expand your Domain!");
            return;
        }
        DomainEngine.launchDomain(player, profile);
    }

    private void routeShadowInventory(Player player, PlayerProfile profile) {
        if (profile.getTechnique() != TechniqueType.MEGUMI) {
            player.sendMessage("§cOnly Ten Shadows sorcerers can manifest items out of shadows!");
            return;
        }
        MegumiKit.openShadowInventory(player);
    }
}
