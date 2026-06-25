package me.demro.dlibs.dbans.api.event;

import me.demro.dlibs.dbans.api.punishment.PunishmentIssuer;
import me.demro.dlibs.dbans.api.punishment.PunishmentReason;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

public record PunishmentRevocation(
        @NotNull PunishmentIssuer issuer,
        @NotNull PunishmentReason reason,
        @NotNull String serverName,
        @NotNull Instant revokedAt
) {

    public PunishmentRevocation {
        Objects.requireNonNull(issuer, "issuer");
        Objects.requireNonNull(reason, "reason");
        Objects.requireNonNull(revokedAt, "revokedAt");
        if (serverName.isBlank()) {
            throw new IllegalArgumentException("Server name cannot be blank");
        }
    }
}