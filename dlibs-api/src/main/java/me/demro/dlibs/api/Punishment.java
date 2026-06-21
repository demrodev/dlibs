package me.demro.dlibs.api;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface Punishment {

    @Contract(pure = true)
    @NotNull String getId();

    @Contract(pure = true)
    @NotNull UUID getPlayerUuid();

    @Contract(pure = true)
    @NotNull String getPlayerName();

    @Contract(pure = true)
    @NotNull UUID getIssuerUuid();

    @Contract(pure = true)
    @NotNull String getIssuerName();

    @Contract(pure = true)
    @NotNull PunishmentType getType();

    @Contract(pure = true)
    @NotNull String getReason();

    @Contract(pure = true)
    long getStartTime();

    @Contract(pure = true)
    @Nullable Long getEndTime();

    @Contract(pure = true)
    boolean isActive();

    @Contract(pure = true)
    boolean isExpired();

    @Contract(pure = true)
    @NotNull String getServerName();
}