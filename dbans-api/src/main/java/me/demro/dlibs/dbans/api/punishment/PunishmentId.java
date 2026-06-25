package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record PunishmentId(@NotNull String value) {

    public PunishmentId {
        if (value.isBlank()) {
            throw new IllegalArgumentException("Punishment ID cannot be blank");
        }
    }

    @Contract("_ -> new")
    public static @NotNull PunishmentId of(@NotNull String value) {
        return new PunishmentId(value);
    }
}