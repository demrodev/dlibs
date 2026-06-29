package me.demro.dlibs.dbans.api.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when dBans is not available for API access.
 *
 * @since 2.0.0
 */
public class DBansUnavailableException extends DBansException {

    public DBansUnavailableException(@NotNull String message) {
        super(message);
    }
}