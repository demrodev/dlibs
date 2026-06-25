package me.demro.dlibs.dbans.api.punishment;

import me.demro.dlibs.dbans.api.player.PlayerIdentity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class PunishmentCreateRequest {

    private final PlayerIdentity target;
    private final PunishmentType type;
    private final PunishmentReason reason;
    private final PunishmentDuration duration;
    private final PunishmentIssuer issuer;
    private final String serverName;
    private final PunishmentOptions options;

    private PunishmentCreateRequest(@NotNull Builder builder) {
        this.target = Objects.requireNonNull(builder.target, "target");
        this.type = Objects.requireNonNull(builder.type, "type");
        this.reason = Objects.requireNonNull(builder.reason, "reason");
        this.duration = Objects.requireNonNull(builder.duration, "duration");
        this.issuer = Objects.requireNonNull(builder.issuer, "issuer");
        this.serverName = requireNonBlank(builder.serverName, "serverName");
        this.options = builder.options == null ? PunishmentOptions.defaults() : builder.options;

        if (!type.supportsDuration() && !duration.isInstant()) {
            throw new IllegalArgumentException("Punishment type " + type + " requires instant duration");
        }
    }

    @Contract(" -> new")
    public static @NotNull Builder builder() {
        return new Builder();
    }

    @Contract("null, _ -> fail")
    private static @NotNull String requireNonBlank(String value, @SuppressWarnings("SameParameterValue") String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " cannot be blank");
        }
        return value;
    }

    @Contract(pure = true)
    public @NotNull PlayerIdentity target() {
        return target;
    }

    @Contract(pure = true)
    public @NotNull PunishmentType type() {
        return type;
    }

    @Contract(pure = true)
    public @NotNull PunishmentReason reason() {
        return reason;
    }

    @Contract(pure = true)
    public @NotNull PunishmentDuration duration() {
        return duration;
    }

    @Contract(pure = true)
    public @NotNull PunishmentIssuer issuer() {
        return issuer;
    }

    @Contract(pure = true)
    public @NotNull String serverName() {
        return serverName;
    }

    @Contract(pure = true)
    public @NotNull PunishmentOptions options() {
        return options;
    }

    public static final class Builder {

        private PlayerIdentity target;
        private PunishmentType type;
        private PunishmentReason reason;
        private PunishmentDuration duration = PunishmentDuration.permanent();
        private PunishmentIssuer issuer = PunishmentIssuer.console();
        private String serverName;
        private PunishmentOptions options = PunishmentOptions.defaults();

        private Builder() {
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder target(@NotNull PlayerIdentity target) {
            this.target = target;
            return this;
        }

        @Contract("_ -> this")
        public @NotNull Builder type(@NotNull PunishmentType type) {
            this.type = type;
            if (!type.supportsDuration()) {
                this.duration = PunishmentDuration.instant();
            }
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder reason(@NotNull PunishmentReason reason) {
            this.reason = reason;
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder duration(@NotNull PunishmentDuration duration) {
            this.duration = duration;
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder issuer(@NotNull PunishmentIssuer issuer) {
            this.issuer = issuer;
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder serverName(@NotNull String serverName) {
            this.serverName = serverName;
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder options(@NotNull PunishmentOptions options) {
            this.options = options;
            return this;
        }

        @Contract(" -> new")
        public @NotNull PunishmentCreateRequest build() {
            return new PunishmentCreateRequest(this);
        }
    }
}