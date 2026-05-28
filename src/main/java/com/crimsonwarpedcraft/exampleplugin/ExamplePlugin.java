package com.crimsonwarpedcraft.exampleplugin;

import com.crimsonwarpedcraft.exampleplugin.commands.TechniqueCommands;
import com.crimsonwarpedcraft.exampleplugin.data.ProfileManager;
import com.crimsonwarpedcraft.exampleplugin.listeners.CombatListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    private ProfileManager profileManager;

    @Override
    public void onEnable() {
        this.profileManager = new ProfileManager();
        
        saveDefaultConfig();

        if (getCommand("jjk") != null) {
            getCommand("jjk").setExecutor(new TechniqueCommands(this));
        }

        getServer().getPluginManager().registerEvents(new CombatListener(this), this);
        getLogger().info("JJK Plugin Core fully initialized!");
    }

    @Override
    public void onDisable() {
        getLogger().info("JJK Plugin Core shutting down smoothly.");
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }
}
