package me.demro.dlibs.dbans.api.alt;

/**
 * Reason why dBans linked two accounts as possible alternatives.
 *
 * @since 2.0.0
 */
public enum AltDetectionReason {
    /**
     * Accounts share the same last known IP address.
     */
    SHARED_IP,
    /**
     * Accounts were linked manually.
     */
    MANUAL_LINK,
    /**
     * Accounts were linked by an external integration.
     */
    EXTERNAL_SOURCE
}