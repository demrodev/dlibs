package me.demro.dlibs.api.exceptions;

import org.jetbrains.annotations.NotNull;

public class PunishmentNotFoundException extends APIException {

    public PunishmentNotFoundException(@NotNull String id) {
        super("Punishment not found: " + id);
    }
}