package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Optional flags and metadata for a punishment action.
 *
 * @since 2.0.0
 */
public final class PunishmentOptions {

    private static final PunishmentOptions DEFAULT = builder().build();

    private final boolean silent;
    private final boolean notifyTarget;
    private final boolean broadcast;
    private final Map<String, String> metadata;

    private PunishmentOptions(@NotNull Builder builder) {
        this.silent = builder.silent;
        this.notifyTarget = builder.notifyTarget;
        this.broadcast = builder.broadcast;
        this.metadata = Collections.unmodifiableMap(new LinkedHashMap<>(builder.metadata));
    }

    /**
     * Returns default options.
     *
     * @return default options
     * @since 2.0.0
     */
    @Contract(pure = true)
    public static @NotNull PunishmentOptions defaults() {
        return DEFAULT;
    }

    /**
     * Creates a new options builder.
     *
     * @return builder
     * @since 2.0.0
     */
    @Contract(" -> new")
    public static @NotNull Builder builder() {
        return new Builder();
    }

    @Contract(pure = true)
    public boolean silent() {
        return silent;
    }

    @Contract(pure = true)
    public boolean notifyTarget() {
        return notifyTarget;
    }

    @Contract(pure = true)
    public boolean broadcast() {
        return broadcast;
    }

    @Contract(pure = true)
    public @NotNull Map<String, String> metadata() {
        return metadata;
    }

    /**
     * Builder for punishment options.
     *
     * @since 2.0.0
     */
    public static final class Builder {

        private final Map<String, String> metadata = new LinkedHashMap<>();
        private boolean silent;
        private boolean notifyTarget = true;
        private boolean broadcast = true;

        private Builder() {
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder silent(boolean silent) {
            this.silent = silent;
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder notifyTarget(boolean notifyTarget) {
            this.notifyTarget = notifyTarget;
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder broadcast(boolean broadcast) {
            this.broadcast = broadcast;
            return this;
        }

        @Contract("_, _ -> this")
        public @NotNull Builder metadata(@NotNull String key, @NotNull String value) {
            if (key.isBlank()) {
                throw new IllegalArgumentException("Metadata key cannot be blank");
            }
            metadata.put(key, Objects.requireNonNull(value, "value"));
            return this;
        }

        @Contract(" -> new")
        public @NotNull PunishmentOptions build() {
            return new PunishmentOptions(this);
        }
    }
}