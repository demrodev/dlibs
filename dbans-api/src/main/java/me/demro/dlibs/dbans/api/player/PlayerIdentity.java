package me.demro.dlibs.dbans.api.player;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class PlayerIdentity {

    private final UUID uuid;
    private final String name;

    @Contract(pure = true)
    public PlayerIdentity(UUID uuid, String name) {
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
}