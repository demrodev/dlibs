package me.demro.dlibs.dbans.api.events;

import me.demro.dlibs.dbans.api.Punishment;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a punishment is revoked via command or API.
 *
 * @since 1.0.0
 */
public class PunishmentRevokeEvent extends PunishmentEvent {

    /**
     * Creates event for given revoked punishment.
     *
     * @param punishment revoked punishment
     * @since 1.0.0
     */
    public PunishmentRevokeEvent(@NotNull Punishment punishment) {
        super(punishment);
    }
}