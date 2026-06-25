package me.demro.dlibs.dbans.api.exception;

import me.demro.dlibs.dbans.api.punishment.PunishmentId;
import org.jetbrains.annotations.NotNull;

public class PunishmentNotFoundException extends DBansException {

    public PunishmentNotFoundException(@NotNull PunishmentId id) {
        super("Punishment not found: " + id.value());
    }
}