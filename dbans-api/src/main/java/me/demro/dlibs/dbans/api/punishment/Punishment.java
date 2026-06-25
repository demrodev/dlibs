package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.NotNull;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface Punishment {

    @NotNull PunishmentId id();

    @NotNull String shortId();

    @NotNull UUID targetUuid();

    @NotNull String targetName();

    @NotNull PunishmentIssuer issuer();

    @NotNull PunishmentType type();

    @NotNull PunishmentReason reason();

    @NotNull Instant createdAt();

    @NotNull Optional<Instant> expiresAt();

    @NotNull PunishmentStatus status();

    @NotNull String serverName();

    default boolean isActive() {
        return status() == PunishmentStatus.ACTIVE && !isExpired();
    }

    default boolean isPermanent() {
        return expiresAt().isEmpty();
    }

    default boolean isExpired() {
        return expiresAt().map(expiresAt -> !expiresAt.isAfter(Instant.now())).orElse(false);
    }

    default @NotNull Optional<Duration> remainingDuration(@NotNull Clock clock) {
        return expiresAt().map(expiresAt -> Duration.between(Instant.now(clock), expiresAt))
                          .filter(duration -> !duration.isNegative() && !duration.isZero());
    }
}