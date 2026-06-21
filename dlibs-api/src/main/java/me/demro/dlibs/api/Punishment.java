package me.demro.dlibs.api;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Represents a punishment and provides read‑only access to its fields.
 *
 * @since 1.0.0
 */
public interface Punishment {

    /**
     * Returns unique ID of punishment (5 characters).
     *
     * @return punishment ID (5 characters)
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull String getId();

    /**
     * Returns UUID of punished player.
     *
     * @return player UUID
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull UUID getPlayerUuid();

    /**
     * Returns name of punished player.
     *
     * @return player name
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull String getPlayerName();

    /**
     * Returns UUID of issuer.
     *
     * @return issuer UUID
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull UUID getIssuerUuid();

    /**
     * Returns name of issuer.
     *
     * @return issuer name
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull String getIssuerName();

    /**
     * Returns type of punishment.
     *
     * @return punishment type
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull PunishmentType getType();

    /**
     * Returns reason for punishment.
     *
     * @return reason
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull String getReason();

    /**
     * Returns creation timestamp in milliseconds.
     *
     * @return start time (ms since epoch)
     * @since 1.0.0
     */
    @Contract(pure = true)
    long getStartTime();

    /**
     * Returns expiration timestamp in milliseconds, or {@code null} if punishment is permanent.
     *
     * @return end time (ms since epoch), or {@code null} if permanent
     * @since 1.0.0
     */
    @Contract(pure = true)
    @Nullable Long getEndTime();

    /**
     * Checks whether punishment is currently active.
     *
     * @return {@code true} if active
     * @since 1.0.0
     */
    @Contract(pure = true)
    boolean isActive();

    /**
     * Checks whether temporary punishment has expired.
     *
     * @return {@code true} if expired
     * @since 1.0.0
     */
    @Contract(pure = true)
    boolean isExpired();

    /**
     * Returns name of server where punishment was issued.
     *
     * @return server name from plugin's config
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull String getServerName();
}