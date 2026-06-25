package me.demro.dlibs.dbans.api.exception;

import org.jetbrains.annotations.NotNull;

public class DBansException extends RuntimeException {

    public DBansException(@NotNull String message) {
        super(message);
    }

    public DBansException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}