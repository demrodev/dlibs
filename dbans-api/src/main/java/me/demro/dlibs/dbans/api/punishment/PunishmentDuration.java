package me.demro.dlibs.dbans.api.punishment;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Optional;

public sealed interface PunishmentDuration
        permits PunishmentDuration.Permanent, PunishmentDuration.Temporary, PunishmentDuration.Instant {

    @Contract(pure = true)
    static @NotNull PunishmentDuration permanent() {
        return Permanent.INSTANCE;
    }

    @Contract(pure = true)
    static @NotNull PunishmentDuration temporary(@NotNull Duration duration) {
        return new Temporary(duration);
    }

    @Contract(pure = true)
    static @NotNull PunishmentDuration instant() {
        return Instant.INSTANCE;
    }

    boolean isPermanent();

    boolean isInstant();

    @NotNull Optional<Duration> temporaryDuration();

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