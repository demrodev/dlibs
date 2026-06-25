package me.demro.dlibs.dbans.api;

import me.demro.dlibs.dbans.api.exceptions.PlayerNotFoundException;
import me.demro.dlibs.dbans.api.exceptions.PunishmentNotFoundException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Core manager for creating, revoking, and querying punishments.
 *
 * @since 1.0.0
 */
public interface PunishmentManager {

    /**
     * Creates punishment for player.
     *
     * @param targetUuid UUID of player
     * @param type       punishment type
     * @param reason     reason
     * @param duration   duration in ms, or {@code null} for permanent
     * @param issuerUuid issuer UUID; use {@code UUID.nameUUIDFromBytes("CONSOLE".getBytes())} for console
     * @param issuerName issuer name
     * @param serverName server name
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
     * Returns punishment history for player, including inactive records.
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
     * Checks whether player has an active mute.
     *
     * @param targetUuid UUID of player
     * @return {@code true} if player is muted
     * @since 1.0.0
     */
    @Contract(pure = true)
    boolean isMuted(@NotNull UUID targetUuid);

    /**
     * Checks whether player is jailed.
     *
     * @param targetUuid UUID of player
     * @return {@code true} if player is jailed
     * @since 1.0.2
     */
    @Contract(pure = true)
    boolean isJailed(@NotNull UUID targetUuid);

    /**
     * Checks whether player has at least one active warning.
     *
     * @param targetUuid UUID of player
     * @return {@code true} if player has an active warning
     * @since 1.0.2
     */
    @Contract(pure = true)
    boolean isWarned(@NotNull UUID targetUuid);

    /**
     * Creates punishment for player.
     *
     * <p>Recommended over {@link #punish} to avoid blocking the main server thread.</p>
     *
     * @param targetUuid UUID of player
     * @param type       punishment type
     * @param reason     reason
     * @param duration   duration in ms, or {@code null} for permanent
     * @param issuerUuid issuer UUID; use {@code UUID.nameUUIDFromBytes("CONSOLE".getBytes())} for console
     * @param issuerName issuer name
     * @param serverName server name
     * @return future ID of created punishment
     * @since 1.0.0
     */
    @NotNull CompletableFuture<String> punishAsync(@NotNull UUID targetUuid, @NotNull PunishmentType type,
                                                   @NotNull String reason, @Nullable Long duration,
                                                   @NotNull UUID issuerUuid, @NotNull String issuerName,
                                                   @NotNull String serverName
    );

    /**
     * Revokes punishment by ID.
     *
     * <p>Recommended over {@link #revoke} to avoid blocking the main server thread.</p>
     *
     * @param punishmentId ID of punishment
     * @param issuerName   issuer name
     * @param reason       reason for revocation
     * @return future that completes when the punishment has been revoked
     * @since 1.0.2
     */
    @NotNull CompletableFuture<Void> revokeAsync(@NotNull String punishmentId, @NotNull String issuerName,
                                                 @NotNull String reason
    );
}