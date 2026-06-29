package me.demro.dlibs.dbans.api.spi;

import me.demro.dlibs.dbans.api.DBansAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Internal helper for registering and unregistering the dBans API service.
 *
 * <p>This type is intended for the dBans plugin implementation, not for addons.</p>
 *
 * @since 2.0.0
 */
public final class DBansServiceRegistrar {

    @Contract(value = " -> fail", pure = true)
    private DBansServiceRegistrar() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Registers the API in Bukkit's service manager.
     *
     * @param plugin owning plugin
     * @param api    API instance
     * @since 2.0.0
     */
    public static void register(@NotNull Plugin plugin, @NotNull DBansAPI api) {
        Bukkit.getServicesManager().register(
                DBansAPI.class,
                Objects.requireNonNull(api, "api"),
                Objects.requireNonNull(plugin, "plugin"),
                ServicePriority.Normal
        );
    }

    /**
     * Unregisters the API from Bukkit's service manager.
     *
     * @param api API instance
     * @since 2.0.0
     */
    public static void unregister(@NotNull DBansAPI api) {
        Bukkit.getServicesManager().unregister(DBansAPI.class, Objects.requireNonNull(api, "api"));
    }
}