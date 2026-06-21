package me.demro.dlibs.api;

import java.util.UUID;

public interface Punishment {

    String getId();

    UUID getPlayerUuid();

    String getPlayerName();

    UUID getIssuerUuid();

    String getIssuerName();

    PunishmentType getType();

    String getReason();

    long getStartTime();

    Long getEndTime();

    boolean isActive();

    boolean isExpired();

    String getServerName();
}