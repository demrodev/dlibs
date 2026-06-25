package me.demro.dlibs.dbans.api.player;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PlayerService {

    @NotNull CompletableFuture<Optional<String>> findName(@NotNull UUID playerUuid);

    @NotNull CompletableFuture<Optional<UUID>> findUuid(@NotNull String playerName);

    @NotNull CompletableFuture<Optional<PlayerAddress>> findLastKnownAddress(@NotNull UUID playerUuid);
}