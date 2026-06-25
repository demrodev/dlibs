package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class PunishmentIssuer {

    private static final PunishmentIssuer CONSOLE = new PunishmentIssuer(null, "CONSOLE", true);

    private final UUID uuid;
    private final String name;
    private final boolean console;

    @Contract(pure = true)
    public PunishmentIssuer(UUID uuid, String name, boolean console) {
        this.uuid = uuid;
        this.name = Objects.requireNonNull(name, "name");
        this.console = console;
    }

    @Contract(pure = true)
    public static @NotNull PunishmentIssuer console() {
        return CONSOLE;
    }

    @Contract("_, _ -> new")
    public static @NotNull PunishmentIssuer player(@NotNull UUID uuid, @NotNull String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Issuer name cannot be blank");
        }
        return new PunishmentIssuer(Objects.requireNonNull(uuid, "uuid"), name, false);
    }

    @Contract(pure = true)
    public @NotNull Optional<UUID> uuid() {
        return Optional.ofNullable(uuid);
    }

    @Contract(pure = true)
    public @NotNull String name() {
        return name;
    }

    @Contract(pure = true)
    public boolean isConsole() {
        return console;
    }
}