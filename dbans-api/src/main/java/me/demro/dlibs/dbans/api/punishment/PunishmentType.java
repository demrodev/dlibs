package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.Contract;

/**
 * Types of punishment supported by dBans.
 *
 * @since 2.0.0
 */
public enum PunishmentType {
    /**
     * Account-based ban.
     */
    BAN(true, true),
    /**
     * Account-based chat mute.
     */
    MUTE(true, true),
    /**
     * Instant kick.
     */
    KICK(false, false),
    /**
     * IP-based ban.
     */
    IP_BAN(true, true),
    /**
     * Jail punishment.
     */
    JAIL(true, true),
    /**
     * Warning punishment.
     */
    WARNING(true, true);

    private final boolean persistent;
    private final boolean supportsDuration;

    @Contract(pure = true)
    PunishmentType(boolean persistent, boolean supportsDuration) {
        this.persistent = persistent;
        this.supportsDuration = supportsDuration;
    }

    /**
     * Checks if this type is stored as a persistent punishment record.
     *
     * @return {@code true} if this type is persistent
     * @apiNote Kicks are not persistent, while bans and mutes are.
     * @since 2.0.0
     */
    @Contract(pure = true)
    public boolean isPersistent() {
        return persistent;
    }

    /**
     * Checks if this type supports temporary and permanent durations.
     *
     * @return {@code true} if this type supports duration
     * @apiNote Kicks do not support durations, while bans and mutes do.
     * @since 2.0.0
     */
    @Contract(pure = true)
    public boolean supportsDuration() {
        return supportsDuration;
    }
}