package me.demro.dlibs.dbans.api.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Base exception for all dBans API errors.
 *
 * @since 2.0.0
 */
public class DBansException extends RuntimeException {

    public DBansException(@NotNull String message) {
        super(message);
    }

    public DBansException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}