package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.NotNull;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * Read-only punishment view exposed by dBans.
 *
 * @since 2.0.0
 */
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
        return isActive(Clock.systemUTC());
    }

    default boolean isActive(@NotNull Clock clock) {
        return status() == PunishmentStatus.ACTIVE && !isExpired(clock);
    }

    default boolean isPermanent() {
        return expiresAt().isEmpty();
    }

    default boolean isExpired() {
        return isExpired(Clock.systemUTC());
    }

    default boolean isExpired(@NotNull Clock clock) {
        return expiresAt().map(expiresAt -> !expiresAt.isAfter(Instant.now(clock))).orElse(false);
    }

    default @NotNull Optional<Duration> remainingDuration(@NotNull Clock clock) {
        return expiresAt().map(expiresAt -> Duration.between(Instant.now(clock), expiresAt))
                          .filter(duration -> !duration.isNegative() && !duration.isZero());
    }
}