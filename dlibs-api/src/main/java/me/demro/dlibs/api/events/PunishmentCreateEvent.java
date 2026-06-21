package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a new punishment is created via command, API, or automation.
 *
 * @since 1.0.0
 */
public class PunishmentCreateEvent extends PunishmentEvent {

    /**
     * Creates event for given punishment.
     *
     * @param punishment created punishment
     * @since 1.0.0
     */
    public PunishmentCreateEvent(@NotNull Punishment punishment) {
        super(punishment);
    }
}