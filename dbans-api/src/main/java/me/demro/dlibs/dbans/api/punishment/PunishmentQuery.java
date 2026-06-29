package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

/**
 * Query object for searching punishments.
 *
 * @since 2.0.0
 */
public final class PunishmentQuery {

    private final UUID targetUuid;
    private final PunishmentType type;
    private final PunishmentStatus status;
    private final Integer limit;

    @Contract(pure = true)
    private PunishmentQuery(@NotNull Builder builder) {
        this.targetUuid = builder.targetUuid;
        this.type = builder.type;
        this.status = builder.status;
        this.limit = builder.limit;
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Builder builder() {
        return new Builder();
    }

    @Contract(pure = true)
    public @NotNull Optional<UUID> targetUuid() {
        return Optional.ofNullable(targetUuid);
    }

    @Contract(pure = true)
    public @NotNull Optional<PunishmentType> type() {
        return Optional.ofNullable(type);
    }

    @Contract(pure = true)
    public @NotNull Optional<PunishmentStatus> status() {
        return Optional.ofNullable(status);
    }

    @Contract(pure = true)
    public @NotNull Optional<Integer> limit() {
        return Optional.ofNullable(limit);
    }

    /**
     * Builder for punishment queries.
     *
     * @since 2.0.0
     */
    public static final class Builder {

        private UUID targetUuid;
        private PunishmentType type;
        private PunishmentStatus status;
        private Integer limit;

        @Contract(pure = true)
        private Builder() {
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder targetUuid(@NotNull UUID targetUuid) {
            this.targetUuid = targetUuid;
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder type(@NotNull PunishmentType type) {
            this.type = type;
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder status(@NotNull PunishmentStatus status) {
            this.status = status;
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder limit(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("Limit must be positive");
            }
            this.limit = limit;
            return this;
        }

        @Contract(value = " -> new", pure = true)
        public @NotNull PunishmentQuery build() {
            return new PunishmentQuery(this);
        }
    }
}