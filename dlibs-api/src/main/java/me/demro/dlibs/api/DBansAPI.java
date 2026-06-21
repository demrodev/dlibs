package me.demro.dlibs.api;

import org.bukkit.plugin.Plugin;

public interface DBansAPI {
    PunishmentManager getPunishmentManager();
    PlayerManager getPlayerManager();
    EventManager getEventManager();
    boolean isEnabled();
    String getVersion();
    Plugin getPlugin();
}