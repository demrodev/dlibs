package me.demro.dlibs.dbans.api.alt;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface AltService {

    @NotNull CompletableFuture<List<AltAccount>> findAlts(@NotNull UUID playerUuid);
}