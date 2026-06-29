package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Request for revoking a punishment.
 *
 * @since 2.0.0
 */
public record PunishmentRevokeRequest(
        @NotNull PunishmentId punishmentId,
        @NotNull PunishmentIssuer issuer,
        @NotNull PunishmentReason reason,
        @NotNull String serverName,
        @NotNull PunishmentOptions options
) {

    public PunishmentRevokeRequest {
        Objects.requireNonNull(punishmentId, "punishmentId");
        Objects.requireNonNull(issuer, "issuer");
        Objects.requireNonNull(reason, "reason");
        Objects.requireNonNull(options, "options");
        Objects.requireNonNull(serverName, "serverName");
        if (serverName.isBlank()) {
            throw new IllegalArgumentException("Server name cannot be blank");
        }
    }

    @Contract("_, _, _, _ -> new")
    public static @NotNull PunishmentRevokeRequest of(
            @NotNull PunishmentId punishmentId,
            @NotNull PunishmentIssuer issuer,
            @NotNull PunishmentReason reason,
            @NotNull String serverName
    ) {
        return new PunishmentRevokeRequest(punishmentId, issuer, reason, serverName, PunishmentOptions.defaults());
    }
}