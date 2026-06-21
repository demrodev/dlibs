package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a temporary punishment expires naturally.
 *
 * @since 1.0.0
 */
public class PunishmentExpireEvent extends PunishmentEvent {

    /**
     * Creates event for given expired punishment.
     *
     * @param punishment expired punishment
     * @since 1.0.0
     */
    public PunishmentExpireEvent(@NotNull Punishment punishment) {
        super(punishment);
    }
}