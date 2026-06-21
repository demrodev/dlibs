package me.demro.dlibs.api;

import java.util.UUID;

public interface PlayerManager {

    String getPlayerIp(UUID playerUuid);

    boolean hasImmunity(UUID playerUuid, PunishmentType type);

    int getPriority(UUID playerUuid);

    boolean canPunish(UUID issuerUuid, UUID targetUuid);
}