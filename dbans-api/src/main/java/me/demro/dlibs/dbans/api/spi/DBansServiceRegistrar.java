package me.demro.dlibs.dbans.api.spi;

import me.demro.dlibs.dbans.api.DBansAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class DBansServiceRegistrar {

    @Contract(value = " -> fail", pure = true)
    private DBansServiceRegistrar() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void register(@NotNull Plugin plugin, @NotNull DBansAPI api) {
        Bukkit.getServicesManager().register(
                DBansAPI.class,
                Objects.requireNonNull(api, "api"),
                Objects.requireNonNull(plugin, "plugin"),
                ServicePriority.Normal
        );
    }

    public static void unregister(@NotNull DBansAPI api) {
        Bukkit.getServicesManager().unregister(DBansAPI.class, Objects.requireNonNull(api, "api"));
    }
}