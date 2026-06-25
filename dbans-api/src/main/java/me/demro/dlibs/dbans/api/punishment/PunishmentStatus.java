package me.demro.dlibs.dbans.api.punishment;

/**
 * Lifecycle state of a punishment.
 *
 * @since 2.0.0
 */
public enum PunishmentStatus {
    /**
     * Punishment is currently active.
     */
    ACTIVE,
    /**
     * Temporary punishment expired.
     */
    EXPIRED,
    /**
     * Punishment was manually revoked.
     */
    REVOKED
}