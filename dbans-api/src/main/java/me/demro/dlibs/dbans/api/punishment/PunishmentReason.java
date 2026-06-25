package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record PunishmentReason(@NotNull String value) {

    public PunishmentReason {
        if (value.isBlank()) {
            throw new IllegalArgumentException("Punishment reason cannot be blank");
        }
    }

    @Contract("_ -> new")
    public static @NotNull PunishmentReason of(@NotNull String value) {
        return new PunishmentReason(value);
    }
}