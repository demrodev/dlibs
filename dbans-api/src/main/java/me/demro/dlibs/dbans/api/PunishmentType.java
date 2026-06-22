package me.demro.dlibs.dbans.api;

/**
 * Types of punishment.
 *
 * @since 1.0.0
 */
public enum PunishmentType {
    /**
     * Permanent or temporary ban.
     */
    BAN,
    /**
     * Permanent or temporary mute.
     */
    MUTE,
    /**
     * Instant kick.
     */
    KICK,
    /**
     * IP-based ban.
     */
    IPBAN,
    /**
     * Temporary or permanent jail.
     */
    JAIL,
    /**
     * Warning.
     */
    WARNING
}