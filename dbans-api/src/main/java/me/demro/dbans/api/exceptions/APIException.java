package me.demro.dbans.api.exceptions;

import org.jetbrains.annotations.NotNull;

/**
 * Base exception for all DBans API errors.
 *
 * @since 1.0.0
 */
public class APIException extends RuntimeException {

    /**
     * Creates exception with message.
     *
     * @param message error message
     * @since 1.0.0
     */
    public APIException(@NotNull String message) {
        super(message);
    }

    /**
     * Creates exception with message and cause.
     *
     * @param message error message
     * @param cause   cause
     * @since 1.0.0
     */
    public APIException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}