package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Typed punishment identifier.
 *
 * @param value raw identifier value
 * @since 2.0.0
 */
public record PunishmentId(@NotNull String value) {

    public PunishmentId {
        if (value.isBlank()) {
            throw new IllegalArgumentException("Punishment ID cannot be blank");
        }
    }

    /**
     * Creates a punishment ID from a string.
     *
     * @param value raw identifier value
     * @return punishment ID
     * @since 2.0.0
     */
    @Contract("_ -> new")
    public static @NotNull PunishmentId of(@NotNull String value) {
        return new PunishmentId(value);
    }
}