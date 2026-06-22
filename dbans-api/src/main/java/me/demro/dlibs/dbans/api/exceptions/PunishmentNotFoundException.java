package me.demro.dlibs.dbans.api.exceptions;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when punishment ID does not exist.
 *
 * @since 1.0.0
 */
public class PunishmentNotFoundException extends APIException {

    /**
     * Creates exception for given punishment ID.
     *
     * @param id punishment ID that was not found
     * @since 1.0.0
     */
    public PunishmentNotFoundException(@NotNull String id) {
        super("Punishment not found: " + id);
    }
}