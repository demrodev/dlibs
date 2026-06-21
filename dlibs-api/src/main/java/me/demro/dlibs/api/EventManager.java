package me.demro.dlibs.api;

import me.demro.dlibs.api.events.PunishmentEvent;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * Manager for registering/unregistering and calling punishment events.
 *
 * @since 1.0.0
 */
public interface EventManager {

    /**
     * Registers listener to receive punishment events.
     *
     * @param listener listener to register
     * @since 1.0.0
     */
    void registerListener(@NotNull Listener listener);

    /**
     * Unregisters listener so it no longer receives punishment events.
     *
     * @param listener listener to unregister
     * @since 1.0.0
     */
    void unregisterListener(@NotNull Listener listener);

    /**
     * Fires punishment event, notifying all registered listeners.
     *
     * @param event event to call
     * @apiNote This method is intended for internal use by DBans, and it is recommended to use {@link me.demro.dlibs.api.PunishmentManager} methods for creating/updating/deleting punishments, which will automatically call appropriate events.
     * @since 1.0.0
     */
    void callEvent(@NotNull PunishmentEvent event);
}