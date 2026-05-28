package com.crimsonwarpedcraft.exampleplugin.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {
    
    // Tracks online player UUIDs mapped to their JJK data profiles
    private final Map<UUID, PlayerProfile> profiles = new HashMap<>();

    /**
     * Gets a player's profile from server RAM. 
     * If they don't have one cached yet, it instantly builds a fresh one.
     */
    public PlayerProfile getProfile(UUID uuid) {
        return profiles.computeIfAbsent(uuid, PlayerProfile::new);
    }

    /**
     * Removes a profile completely from memory when a player logs out.
     * Essential for preventing memory leaks on production servers.
     */
    public void removeProfile(UUID uuid) {
        profiles.remove(uuid);
    }
}
