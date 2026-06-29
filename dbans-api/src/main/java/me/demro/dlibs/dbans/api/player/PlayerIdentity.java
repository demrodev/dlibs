package me.demro.dlibs.dbans.api.player;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Player identity used by dBans API requests.
 *
 * @since 2.0.0
 */
public final class PlayerIdentity {

    private final UUID uuid;
    private final String name;

    @Contract(pure = true)
    private PlayerIdentity(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    @Contract("_ -> new")
    public static @NotNull PlayerIdentity uuid(@NotNull UUID uuid) {
        return new PlayerIdentity(Objects.requireNonNull(uuid, "uuid"), null);
    }

    @Contract("_ -> new")
    public static @NotNull PlayerIdentity name(@NotNull String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank");
        }
        return new PlayerIdentity(null, name);
    }

    @Contract("_, _ -> new")
    public static @NotNull PlayerIdentity of(@NotNull UUID uuid, @NotNull String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank");
        }
        return new PlayerIdentity(Objects.requireNonNull(uuid, "uuid"), name);
    }

    @Contract(pure = true)
    public @NotNull Optional<UUID> uuid() {
        return Optional.ofNullable(uuid);
    }

    @Contract(pure = true)
    public @NotNull Optional<String> name() {
        return Optional.ofNullable(name);
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlayerIdentity other)) return false;
        return Objects.equals(uuid, other.uuid) && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name);
    }
}