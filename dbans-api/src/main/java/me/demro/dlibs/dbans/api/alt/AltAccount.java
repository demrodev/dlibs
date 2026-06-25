package me.demro.dlibs.dbans.api.alt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * Possible alternative account relation.
 *
 * @param uuid   related account UUID
 * @param reason detection reason
 * @since 2.0.0
 */
public record AltAccount(@NotNull UUID uuid, @NotNull AltDetectionReason reason) {

    public AltAccount {
        Objects.requireNonNull(uuid, "uuid");
        Objects.requireNonNull(reason, "reason");
    }
}