package me.demro.dlibs.dbans.api.event;

/**
 * Origin of a dBans event.
 *
 * @since 2.0.0
 */
public enum EventOrigin {
    /**
     * Event was caused by a command.
     */
    COMMAND,
    /**
     * Event was caused by a public API call.
     */
    API,
    /**
     * Event was caused by internal automation.
     */
    AUTO,
    /**
     * Event was caused by proxy or network synchronisation.
     */
    SYNC,
    /**
     * Event was caused by internal dBans logic.
     */
    INTERNAL
}