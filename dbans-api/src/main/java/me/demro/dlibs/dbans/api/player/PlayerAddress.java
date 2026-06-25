package me.demro.dlibs.dbans.api.player;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

public record PlayerAddress(@NotNull String value, @NotNull Instant recordedAt) {

    public PlayerAddress {
        if (value.isBlank()) {
            throw new IllegalArgumentException("Address cannot be blank");
        }
        Objects.requireNonNull(recordedAt, "recordedAt");
    }
}