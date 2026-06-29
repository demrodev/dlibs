package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Reason attached to a punishment action.
 *
 * @param value reason text
 * @since 2.0.0
 */
public record PunishmentReason(@NotNull String value) {

    public PunishmentReason {
        if (value.isBlank()) {
            throw new IllegalArgumentException("Punishment reason cannot be blank");
        }
    }

    /**
     * Creates a punishment reason from text.
     *
     * @param value reason text
     * @return punishment reason
     * @since 2.0.0
     */
    @Contract("_ -> new")
    public static @NotNull PunishmentReason of(@NotNull String value) {
        return new PunishmentReason(value);
    }
}