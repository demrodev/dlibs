package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;

public enum PunishmentType {
    BAN(true, true),
    MUTE(true, true),
    KICK(false, false),
    IP_BAN(true, true),
    JAIL(true, true),
    WARNING(true, true);

    private final boolean persistent;
    private final boolean supportsDuration;

    @Contract(pure = true)
    PunishmentType(boolean persistent, boolean supportsDuration) {
        this.persistent = persistent;
        this.supportsDuration = supportsDuration;
    }

    @Contract(pure = true)
    public boolean isPersistent() {
        return persistent;
    }

    @Contract(pure = true)
    public boolean supportsDuration() {
        return supportsDuration;
    }
}