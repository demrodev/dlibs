package me.demro.dlibs.dbans.api.exception;

import org.jetbrains.annotations.NotNull;

public class InvalidPunishmentRequestException extends DBansException {

    public InvalidPunishmentRequestException(@NotNull String message) {
        super(message);
    }
}