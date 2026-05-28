package com.crimsonwarpedcraft.exampleplugin.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerProfile {
    private final UUID uuid;
    private int cursedEnergy = 100;
    private int maxCursedEnergy = 250; 
    private TechniqueType technique = TechniqueType.NONE; 
    private boolean isBurnedOut = false;

    // Tracks cooldowns using the action key name and a system millisecond timestamp
    private final Map<String, Long> cooldowns = new HashMap<>();

    public PlayerProfile(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Sets a cooldown for a specific action key in seconds.
     */
    public void setCooldown(String actionKey, int seconds) {
        if (seconds <= 0) return;
        this.cooldowns.put(actionKey, System.currentTimeMillis() + (seconds * 1000L));
    }

    /**
     * Checks if a cooldown is still active. If it is active, returns the remaining seconds.
     * If it's expired or doesn't exist, returns 0.
     */
    public int getRemainingCooldown(String actionKey) {
        if (!cooldowns.containsKey(actionKey)) return 0;
        long expireTime = cooldowns.get(actionKey);
        long timeLeft = expireTime - System.currentTimeMillis();
        
        if (timeLeft <= 0) {
            cooldowns.remove(actionKey);
            return 0;
        }
        return (int) Math.ceil(timeLeft / 1000.0);
    }

    public UUID getUuid() { return uuid; }
    public int getCursedEnergy() { return cursedEnergy; }
    public void setCursedEnergy(int ce) { this.cursedEnergy = Math.max(0, Math.min(ce, this.maxCursedEnergy)); }
    public int getMaxCursedEnergy() { return maxCursedEnergy; }
    public void setMaxCursedEnergy(int max) { this.maxCursedEnergy = max; }
    public TechniqueType getTechnique() { return technique; }
    public void setTechnique(TechniqueType technique) { this.technique = technique; }
    public boolean isBurnedOut() { return isBurnedOut; }
    public void setBurnedOut(boolean b) { this.isBurnedOut = b; }
}
