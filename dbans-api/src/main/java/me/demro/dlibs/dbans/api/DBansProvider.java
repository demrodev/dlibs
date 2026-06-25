package me.demro.dlibs.dbans.api;

import me.demro.dlibs.dbans.api.exception.DBansUnavailableException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Static accessor for the dBans API service registered in Bukkit's service manager.
 *
 * @since 2.0.0
 */
public final class DBansProvider {

    @Contract(value = " -> fail", pure = true)
    private DBansProvider() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns registered dBans API if it is currently available.
     *
     * @return API instance, or an empty optional if dBans is unavailable
     * @since 2.0.0
     */
    public static @NotNull Optional<DBansAPI> get() {
        RegisteredServiceProvider<DBansAPI> registration = Bukkit.getServicesManager()
                                                                 .getRegistration(DBansAPI.class);

        if (registration == null) {
            return Optional.empty();
        }

        DBansAPI api = registration.getProvider();
        return api.isAvailable() ? Optional.of(api) : Optional.empty();
    }

    /**
     * Returns registered dBans API, or throws if it is not available.
     *
     * @return API instance
     * @throws DBansUnavailableException if dBans is not registered or not available
     * @since 2.0.0
     */
    public static @NotNull DBansAPI require() {
        return get().orElseThrow(() -> new DBansUnavailableException("dBans API is not available"));
    }
}