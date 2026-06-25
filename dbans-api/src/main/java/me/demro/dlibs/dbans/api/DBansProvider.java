package me.demro.dlibs.dbans.api;

import me.demro.dlibs.dbans.api.exception.DBansUnavailableException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class DBansProvider {

    @Contract(value = " -> fail", pure = true)
    private DBansProvider() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static @NotNull Optional<DBansAPI> get() {
        RegisteredServiceProvider<DBansAPI> registration = Bukkit.getServicesManager()
                                                                 .getRegistration(DBansAPI.class);

        if (registration == null) {
            return Optional.empty();
        }

        DBansAPI api = registration.getProvider();
        return api.isAvailable() ? Optional.of(api) : Optional.empty();
    }

    public static @NotNull DBansAPI require() {
        return get().orElseThrow(() -> new DBansUnavailableException("dBans API is not available"));
    }
}