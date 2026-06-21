package me.demro.dlibs.api;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface PlayerManager {

    @Contract(pure = true)
    @Nullable String getPlayerIp(@NotNull UUID playerUuid);

    @Contract(pure = true)
    boolean hasImmunity(@NotNull UUID playerUuid, @NotNull PunishmentType type);

    @Contract(pure = true)
    int getPriority(@NotNull UUID playerUuid);

    @Contract(pure = true)
    boolean canPunish(@NotNull UUID issuerUuid, @NotNull UUID targetUuid);
}