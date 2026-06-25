package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PunishmentService {

    @NotNull CompletableFuture<PunishmentCreateResult> create(@NotNull PunishmentCreateRequest request);

    @NotNull CompletableFuture<Void> revoke(@NotNull PunishmentRevokeRequest request);

    @NotNull CompletableFuture<Optional<Punishment>> findById(@NotNull PunishmentId id);

    @NotNull CompletableFuture<List<Punishment>> find(@NotNull PunishmentQuery query);

    default @NotNull CompletableFuture<List<Punishment>> findByTarget(@NotNull UUID targetUuid) {
        return find(PunishmentQuery.builder().targetUuid(targetUuid).build());
    }

    default @NotNull CompletableFuture<List<Punishment>> findActiveByTarget(@NotNull UUID targetUuid) {
        return find(PunishmentQuery.builder().targetUuid(targetUuid).status(PunishmentStatus.ACTIVE).build());
    }

    @NotNull CompletableFuture<Boolean> hasActive(@NotNull UUID targetUuid, @NotNull PunishmentType type);
}