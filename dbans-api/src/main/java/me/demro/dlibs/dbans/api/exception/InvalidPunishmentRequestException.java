package me.demro.dlibs.dbans.api.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when a punishment request contains invalid data.
 *
 * @since 2.0.0
 */
public class InvalidPunishmentRequestException extends DBansException {

    public InvalidPunishmentRequestException(@NotNull String message) {
        super(message);
    }
}