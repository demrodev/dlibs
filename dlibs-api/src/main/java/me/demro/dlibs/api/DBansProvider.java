package me.demro.dlibs.api;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

/**
 * Static accessor for {@link DBansAPI}.
 *
 * <p>Call {@link #get()} after a short delay on server startup, as DBans may take a few ticks to initialise.</p>
 *
 * @since 1.0.0
 */
@UtilityClass
public final class DBansProvider {

    private static DBansAPI apiInstance;

    /**
     * Returns {@link DBansAPI} instance if DBans is loaded and enabled, {@code null} otherwise.
     *
     * @return API instance, or {@code null} if unavailable
     * @since 1.0.0
     */
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

    /**
     * Implemented by DBans plugin to expose its {@link DBansAPI}.
     *
     * @since 1.0.0
     */
    @FunctionalInterface
    public interface DBansAPIProvider {

        /**
         * Returns API instance provided by DBans.
         *
         * @return API instance
         * @since 1.0.0
         */
        DBansAPI getAPI();
    }
}