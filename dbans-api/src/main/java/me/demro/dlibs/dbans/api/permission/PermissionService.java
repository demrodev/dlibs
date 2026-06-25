package me.demro.dlibs.dbans.api.permission;

import me.demro.dlibs.dbans.api.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PermissionService {

    @NotNull CompletableFuture<Boolean> hasImmunity(@NotNull UUID playerUuid, @NotNull PunishmentType type);

    @NotNull CompletableFuture<Integer> priority(@NotNull UUID playerUuid);

    @NotNull CompletableFuture<Boolean> canPunish(@NotNull UUID issuerUuid, @NotNull UUID targetUuid);
}