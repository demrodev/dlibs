package me.demro.dlibs.api;

import me.demro.dlibs.api.exceptions.PlayerNotFoundException;
import me.demro.dlibs.api.exceptions.PunishmentNotFoundException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Core manager for creating, revoking, and querying punishments.
 *
 * <p>Synchronous methods must be called on the main server thread.
 * Use {@link #punishAsync} to avoid blocking the main thread.</p>
 *
 * @since 1.0.0
 */
public interface PunishmentManager {

    /**
     * Creates punishment for target player synchronously.
     *
     * @param targetUuid UUID of player to punish
     * @param type       punishment type
     * @param reason     reason for punishment
     * @param duration   duration in milliseconds, or {@code null} for permanent
     * @param issuerUuid UUID of issuer; use {@code UUID.nameUUIDFromBytes("CONSOLE".getBytes())} for console
     * @param issuerName name of issuer
     * @param serverName server name, used for cross-server synchronisation
     * @return ID of created punishment
     * @throws PlayerNotFoundException if target player is not known to DBans
     * @since 1.0.0
     */
    @NotNull String punish(@NotNull UUID targetUuid, @NotNull PunishmentType type, @NotNull String reason,
                           @Nullable Long duration, @NotNull UUID issuerUuid, @NotNull String issuerName,
                           @NotNull String serverName
    ) throws PlayerNotFoundException;

    /**
     * Revokes punishment by ID.
     *
     * @param punishmentId ID of punishment to revoke
     * @param issuerName   name of who revokes it
     * @param reason       reason for revocation
     * @throws PunishmentNotFoundException if punishment ID does not exist
     * @since 1.0.0
     */
    void revoke(@NotNull String punishmentId, @NotNull String issuerName, @NotNull String reason)
            throws PunishmentNotFoundException;

    /**
     * Returns punishment by ID, or {@code null} if not found.
     *
     * <p>Searches across all punishment tables (bans, warnings, jail).</p>
     *
     * @param id punishment ID
     * @return punishment, or {@code null}
     * @since 1.0.0
     */
    @Contract(pure = true)
    @Nullable Punishment getPunishment(@NotNull String id);

    /**
     * Returns all active punishments for player.
     *
     * @param targetUuid UUID of player
     * @return list of active punishments
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull List<Punishment> getActivePunishments(@NotNull UUID targetUuid);

    /**
     * Returns full punishment history for player, including inactive records.
     *
     * @param targetUuid UUID of player
     * @return list of all punishments
     * @since 1.0.0
     */
    @Contract(pure = true)
    @NotNull List<Punishment> getHistory(@NotNull UUID targetUuid);

    /**
     * Checks whether player has an active, non-expired ban.
     *
     * @param targetUuid UUID of player
     * @return {@code true} if player is banned
     * @since 1.0.0
     */
    @Contract(pure = true)
    boolean isBanned(@NotNull UUID targetUuid);

    /**
     * Checks whether player has an active, non-expired mute.
     *
     * @param targetUuid UUID of player
     * @return {@code true} if player is muted
     * @since 1.0.0
     */
    @Contract(pure = true)
    boolean isMuted(@NotNull UUID targetUuid);

    /**
     * Creates punishment for target player asynchronously.
     *
     * <p>Recommended over {@link #punish} to avoid blocking the main server thread.</p>
     *
     * @param targetUuid UUID of player to punish
     * @param type       punishment type
     * @param reason     reason for punishment
     * @param duration   duration in milliseconds, or {@code null} for permanent
     * @param issuerUuid UUID of issuer; use {@code UUID.nameUUIDFromBytes("CONSOLE".getBytes())} for console
     * @param issuerName name of issuer
     * @param serverName server name, used for cross-server synchronisation
     * @return future resolving to ID of created punishment
     * @since 1.0.0
     */
    @NotNull CompletableFuture<String> punishAsync(@NotNull UUID targetUuid, @NotNull PunishmentType type,
                                                   @NotNull String reason, @Nullable Long duration,
                                                   @NotNull UUID issuerUuid, @NotNull String issuerName,
                                                   @NotNull String serverName
    );
}