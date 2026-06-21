package me.demro.dlibs.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public final class DBansProvider {

    private static DBansAPI apiInstance;

    private DBansProvider() {
    }

    public static DBansAPI get() {
        if (apiInstance != null) return apiInstance;
        Plugin plugin = Bukkit.getPluginManager().getPlugin("DBans");
        if (plugin == null || !plugin.isEnabled()) return null;
        if (plugin instanceof DBansAPIProvider) {
            apiInstance = ((DBansAPIProvider) plugin).getAPI();
            return apiInstance;
        }
        return null;
    }

    public interface DBansAPIProvider {

        DBansAPI getAPI();
    }
}