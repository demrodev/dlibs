package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Entity that issued a punishment action.
 *
 * @since 2.0.0
 */
public final class PunishmentIssuer {

    private static final PunishmentIssuer CONSOLE = new PunishmentIssuer(null, "CONSOLE", true);

    private final UUID uuid;
    private final String name;
    private final boolean console;

    @Contract(pure = true)
    private PunishmentIssuer(UUID uuid, String name, boolean console) {
        this.uuid = uuid;
        this.name = Objects.requireNonNull(name, "name");
        this.console = console;
    }

    /**
     * Creates a console issuer.
     *
     * @return console issuer
     * @since 2.0.0
     */
    @Contract(pure = true)
    public static @NotNull PunishmentIssuer console() {
        return CONSOLE;
    }

    /**
     * Creates a player issuer.
     *
     * @param uuid issuer UUID
     * @param name issuer name
     * @return player issuer
     * @since 2.0.0
     */
    @Contract("_, _ -> new")
    public static @NotNull PunishmentIssuer player(@NotNull UUID uuid, @NotNull String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Issuer name cannot be blank");
        }
        return new PunishmentIssuer(Objects.requireNonNull(uuid, "uuid"), name, false);
    }

    /**
     * Returns the issuer UUID if the issuer is a player.
     *
     * @return issuer UUID
     * @since 2.0.0
     */
    @Contract(pure = true)
    public @NotNull Optional<UUID> uuid() {
        return Optional.ofNullable(uuid);
    }

    /**
     * Returns the issuer name.
     *
     * @return issuer name
     * @since 2.0.0
     */
    @Contract(pure = true)
    public @NotNull String name() {
        return name;
    }

    /**
     * Checks if the issuer is the console.
     *
     * @return {@code true} if console
     * @since 2.0.0
     */
    @Contract(pure = true)
    public boolean isConsole() {
        return console;
    }
}