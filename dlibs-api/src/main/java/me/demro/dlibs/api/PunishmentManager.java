package me.demro.dlibs.api;

import me.demro.dlibs.api.exceptions.PlayerNotFoundException;
import me.demro.dlibs.api.exceptions.PunishmentNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PunishmentManager {

    String punish(UUID targetUuid, PunishmentType type, String reason, Long duration,
                  UUID issuerUuid, String issuerName, String serverName
    )
            throws PlayerNotFoundException;

    void revoke(String punishmentId, String issuerName, String reason)
            throws PunishmentNotFoundException;

    Punishment getPunishment(String id);

    List<Punishment> getActivePunishments(UUID targetUuid);

    List<Punishment> getHistory(UUID targetUuid);

    boolean isBanned(UUID targetUuid);

    boolean isMuted(UUID targetUuid);

    CompletableFuture<String> punishAsync(UUID targetUuid, PunishmentType type, String reason,
                                          Long duration, UUID issuerUuid, String issuerName,
                                          String serverName
    );
}