package me.demro.dlibs.dbans.api.spi;

import me.demro.dlibs.dbans.api.event.PunishmentEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Internal service provider interface used by dBans implementations to dispatch API events.
 *
 * <p>This type is not intended for addon use. Addons should listen to Bukkit events from
 * {@link me.demro.dlibs.dbans.api.event} instead.</p>
 *
 * @since 2.0.0
 */
public interface DBansEventDispatcher {

    /**
     * Calls a punishment event.
     *
     * @param event event to call
     * @since 2.0.0
     */
    void callEvent(@NotNull PunishmentEvent event);
}