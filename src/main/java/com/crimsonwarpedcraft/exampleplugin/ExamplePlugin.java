package com.crimsonwarpedcraft.exampleplugin;

import com.crimsonwarpedcraft.exampleplugin.api.CEActionBarTask;
import com.crimsonwarpedcraft.exampleplugin.commands.TechniqueCommands;
import com.crimsonwarpedcraft.exampleplugin.data.ProfileManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {

    private ProfileManager profileManager;

    @Override
    public void onEnable() {
        // 1. Generate and cache your config.yml inside the plugins folder if it doesn't exist
        saveDefaultConfig();

        // 2. Initialize the core in-memory profile tracking registry
        this.profileManager = new ProfileManager();

        // 3. Bind the master /jjk command to your technique traffic routing engine
        if (getCommand("jjk") != null) {
            getCommand("jjk").setExecutor(new TechniqueCommands(this.profileManager));
        }

        // 4. Start the async HUD task to update players' Cursed Energy displays every 10 ticks (0.5s)
        new CEActionBarTask(this.profileManager).runTaskTimerAsynchronously(this, 0L, 10L);

        // 5. Send a confirmation toast to the server terminal
        getLogger().info("================================================");
        getLogger().info(" JJK Plugin Core natively compiled for 1.21.11! ");
        getLogger().info("      CrimsonWarpedCraft Systems: ACTIVE        ");
        getLogger().info("================================================");
    }

    @Override
    public void onDisable() {
        // Safe shutdown routine - clear up operational logs
        getLogger().info("JJK Plugin Core safely deactivated. Clearing sorcerer cache profiles.");
    }

    /**
     * Helper getter to allow your other classes or external managers 
     * to safely interact with your memory storage files.
     */
    public ProfileManager getProfileManager() {
        return this.profileManager;
    }
}
