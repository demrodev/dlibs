package me.demro.dlibs.dbans.api.player;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Service for player data known to dBans.
 *
 * @since 2.0.0
 */
public interface PlayerService {

    /**
     * Finds the last known player name.
     *
     * @param playerUuid player UUID
     * @return future optional player name
     * @since 2.0.0
     */
    @NotNull CompletableFuture<Optional<String>> findName(@NotNull UUID playerUuid);

    /**
     * Finds the last known player UUID by name.
     *
     * @param playerName player name
     * @return future optional player UUID
     * @since 2.0.0
     */
    @NotNull CompletableFuture<Optional<UUID>> findUuid(@NotNull String playerName);

    /**
     * Finds the last known IP of a player.
     *
     * @param playerUuid player UUID
     * @return future optional player IP
     * @since 2.0.0
     */
    @NotNull CompletableFuture<Optional<PlayerAddress>> findLastKnownAddress(@NotNull UUID playerUuid);
}