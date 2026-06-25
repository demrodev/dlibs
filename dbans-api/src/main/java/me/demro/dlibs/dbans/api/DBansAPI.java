package me.demro.dlibs.dbans.api;

import me.demro.dlibs.dbans.api.alt.AltService;
import me.demro.dlibs.dbans.api.permission.PermissionService;
import me.demro.dlibs.dbans.api.player.PlayerService;
import me.demro.dlibs.dbans.api.punishment.PunishmentService;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Main entry point for interacting with dBans.
 *
 * @since 2.0.0
 */
public interface DBansAPI {

    /**
     * Returns the punishment service.
     *
     * @return punishment service
     * @since 2.0.0
     */
    @NotNull PunishmentService punishments();

    /**
     * Returns the player service.
     *
     * @return player service
     * @since 2.0.0
     */
    @NotNull PlayerService players();

    /**
     * Returns the permission and priority service.
     *
     * @return permission service
     * @since 2.0.0
     */
    @NotNull PermissionService permissions();

    /**
     * Returns the alternative account detection service.
     *
     * @return alternative account service
     * @since 2.0.0
     */
    @NotNull AltService alts();

    /**
     * Checks if dBans is currently available for API calls.
     *
     * @return {@code true} if the API is available
     * @since 2.0.0
     */
    boolean isAvailable();

    /**
     * Returns the dBans version.
     *
     * @return version string
     * @since 2.0.0
     */
    @NotNull String version();

    /**
     * Returns the plugin instance that provides this API.
     *
     * @return plugin instance
     * @since 2.0.0
     */
    @NotNull Plugin plugin();
}