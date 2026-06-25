package me.demro.dlibs.dbans.api.exception;

import org.jetbrains.annotations.NotNull;

public class DBansUnavailableException extends DBansException {

    public DBansUnavailableException(@NotNull String message) {
        super(message);
    }
}