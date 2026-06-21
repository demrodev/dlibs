package me.demro.dlibs.api.exceptions;

import org.jetbrains.annotations.NotNull;

public class APIException extends RuntimeException {

    public APIException(@NotNull String message) {
        super(message);
    }

    public APIException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}