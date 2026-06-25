package me.demro.dlibs.dbans.api.exception;

import me.demro.dlibs.dbans.api.punishment.PunishmentId;
import org.jetbrains.annotations.NotNull;

/**
 * Thrown when a punishment ID does not exist.
 *
 * @since 2.0.0
 */
public class PunishmentNotFoundException extends DBansException {

    public PunishmentNotFoundException(@NotNull PunishmentId id) {
        super("Punishment not found: " + id.value());
    }
}