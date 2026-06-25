package me.demro.dlibs.dbans.api.player;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

/**
 * Last known IP of a player.
 *
 * @param value      IP
 * @param recordedAt time when the IP was recorded
 * @since 2.0.0
 */
public record PlayerAddress(@NotNull String value, @NotNull Instant recordedAt) {

    public PlayerAddress {
        if (value.isBlank()) {
            throw new IllegalArgumentException("Address cannot be blank");
        }
        Objects.requireNonNull(recordedAt, "recordedAt");
    }
}