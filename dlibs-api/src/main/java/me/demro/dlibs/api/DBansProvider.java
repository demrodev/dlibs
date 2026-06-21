package me.demro.dlibs.api;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public final class DBansProvider {

    private static DBansAPI apiInstance;

    public static @Nullable DBansAPI get() {
        if (apiInstance != null) return apiInstance;

        Plugin plugin = Bukkit.getPluginManager().getPlugin("DBans");
        if (plugin == null || !plugin.isEnabled()) return null;

        if (plugin instanceof DBansAPIProvider) {
            apiInstance = ((DBansAPIProvider) plugin).getAPI();
            return apiInstance;
        }

        return null;
    }

    @FunctionalInterface
    public interface DBansAPIProvider {

        DBansAPI getAPI();
    }
}