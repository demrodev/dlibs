package me.demro.dlibs.api;

import me.demro.dlibs.api.exceptions.PlayerNotFoundException;
import me.demro.dlibs.api.exceptions.PunishmentNotFoundException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PunishmentManager {

    @NotNull String punish(@NotNull UUID targetUuid, @NotNull PunishmentType type, @NotNull String reason,
                           @Nullable Long duration, @NotNull UUID issuerUuid, @NotNull String issuerName,
                           @NotNull String serverName
    ) throws PlayerNotFoundException;

    void revoke(@NotNull String punishmentId, @NotNull String issuerName, @NotNull String reason)
            throws PunishmentNotFoundException;

    @Contract(pure = true)
    @Nullable Punishment getPunishment(@NotNull String id);

    @Contract(pure = true)
    @NotNull List<Punishment> getActivePunishments(@NotNull UUID targetUuid);

    @Contract(pure = true)
    @NotNull List<Punishment> getHistory(@NotNull UUID targetUuid);

    @Contract(pure = true)
    boolean isBanned(@NotNull UUID targetUuid);

    @Contract(pure = true)
    boolean isMuted(@NotNull UUID targetUuid);

    @NotNull CompletableFuture<String> punishAsync(@NotNull UUID targetUuid, @NotNull PunishmentType type,
                                                   @NotNull String reason, @Nullable Long duration,
                                                   @NotNull UUID issuerUuid, @NotNull String issuerName,
                                                   @NotNull String serverName
    );
}