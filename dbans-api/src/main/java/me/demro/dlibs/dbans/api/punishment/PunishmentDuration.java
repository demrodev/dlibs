package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Optional;

/**
 * Duration policy of a punishment.
 *
 * @since 2.0.0
 */
public sealed interface PunishmentDuration
        permits PunishmentDuration.Permanent, PunishmentDuration.Temporary, PunishmentDuration.Instant {

    /**
     * Creates a permanent duration.
     *
     * @return permanent duration
     * @since 2.0.0
     */
    @Contract(pure = true)
    static @NotNull PunishmentDuration permanent() {
        return Permanent.INSTANCE;
    }

    /**
     * Creates a temporary duration.
     *
     * @param duration positive duration
     * @return temporary duration
     * @since 2.0.0
     */
    @Contract("_ -> new")
    static @NotNull PunishmentDuration temporary(@NotNull Duration duration) {
        return new Temporary(duration);
    }

    /**
     * Creates an instant duration for actions such as kicks.
     *
     * @return instant duration
     * @since 2.0.0
     */
    @Contract(pure = true)
    static @NotNull PunishmentDuration instant() {
        return Instant.INSTANCE;
    }

    /**
     * Checks if the punishment is permanent.
     *
     * @return {@code true} if permanent
     * @since 2.0.0
     */
    boolean isPermanent();

    /**
     * Checks if the punishment is instant.
     *
     * @return {@code true} if instant
     * @since 2.0.0
     */
    boolean isInstant();

    /**
     * Returns the temporary duration if available.
     *
     * @return temporary duration
     * @since 2.0.0
     */
    @NotNull Optional<Duration> temporaryDuration();

    /**
     * Permanent punishment duration.
     *
     * @since 2.0.0
     */
    final class Permanent implements PunishmentDuration {

        private static final Permanent INSTANCE = new Permanent();

        @Contract(pure = true)
        private Permanent() {
        }

        @Contract(pure = true)
        @Override
        public boolean isPermanent() {
            return true;
        }

        @Contract(pure = true)
        @Override
        public boolean isInstant() {
            return false;
        }

        @Contract(pure = true)
        @Override
        public @NotNull Optional<Duration> temporaryDuration() {
            return Optional.empty();
        }
    }

    /**
     * Temporary punishment duration.
     *
     * @param duration positive duration
     * @since 2.0.0
     */
    record Temporary(@NotNull Duration duration) implements PunishmentDuration {

        public Temporary {
            if (duration.isZero() || duration.isNegative()) {
                throw new IllegalArgumentException("Temporary punishment duration must be positive");
            }
        }

        @Contract(pure = true)
        @Override
        public boolean isPermanent() {
            return false;
        }

        @Contract(pure = true)
        @Override
        public boolean isInstant() {
            return false;
        }

        @Contract(value = " -> new", pure = true)
        @Override
        public @NotNull Optional<Duration> temporaryDuration() {
            return Optional.of(duration);
        }
    }

    /**
     * Instant punishment duration.
     *
     * @since 2.0.0
     */
    final class Instant implements PunishmentDuration {

        private static final Instant INSTANCE = new Instant();

        @Contract(pure = true)
        private Instant() {
        }

        @Contract(pure = true)
        @Override
        public boolean isPermanent() {
            return false;
        }

        @Contract(pure = true)
        @Override
        public boolean isInstant() {
            return true;
        }

        @Contract(pure = true)
        @Override
        public @NotNull Optional<Duration> temporaryDuration() {
            return Optional.empty();
        }
    }
}