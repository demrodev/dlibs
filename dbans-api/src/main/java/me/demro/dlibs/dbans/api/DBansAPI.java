package me.demro.dlibs.dbans.api;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Main entry point for interacting with DBans.
 *
 * <p>Obtain an instance via {@link DBansProvider#get()} after DBans has fully initialised.</p>
 *
 * @since 1.0.0
 */
public interface DBansAPI {

    /**
     * Returns manager for punishments.
     *
     * @return punishment manager
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull PunishmentManager getPunishmentManager();

    /**
     * Returns manager for player data.
     *
     * @return player manager
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull PlayerManager getPlayerManager();

    /**
     * Returns manager for event registration.
     *
     * @return event manager
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull EventManager getEventManager();

    /**
     * Checks whether DBans is enabled and available.
     *
     * @return {@code true} if DBans is enabled
     * @since 1.0.0
     */
    @Contract(pure = true)
    boolean isEnabled();

    /**
     * Returns version of DBans.
     *
     * @return version string
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull String getVersion();

    /**
     * Returns DBans plugin instance.
     *
     * @return plugin instance
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull Plugin getPlugin();
}