package me.demro.dlibs.dbans.api.event;

import me.demro.dlibs.dbans.api.punishment.PunishmentIssuer;
import me.demro.dlibs.dbans.api.punishment.PunishmentReason;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

/**
 * Details of a punishment revocation.
 *
 * @param issuer     revocation issuer
 * @param reason     revocation reason
 * @param serverName server where the revocation was issued
 * @param revokedAt  revocation time
 * @since 2.0.0
 */
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
        Objects.requireNonNull(serverName, "serverName");
        if (serverName.isBlank()) {
            throw new IllegalArgumentException("Server name cannot be blank");
        }
    }
}