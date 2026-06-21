package me.demro.dlibs.api;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface DBansAPI {

    @Contract(pure = true)
    @NotNull PunishmentManager getPunishmentManager();

    @Contract(pure = true)
    @NotNull PlayerManager getPlayerManager();

    @Contract(pure = true)
    @NotNull EventManager getEventManager();

    @Contract(pure = true)
    boolean isEnabled();

    @Contract(pure = true)
    @NotNull String getVersion();

    @Contract(pure = true)
    @NotNull Plugin getPlugin();
}