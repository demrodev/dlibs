package me.demro.dlibs.dbans.api.event;

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

/**
 * Base class for all dBans API events.
 *
 * @since 2.0.0
 */
public abstract class DBansEvent extends Event {

    private final EventOrigin origin;
    private final Instant occurredAt;

    protected DBansEvent(@NotNull EventOrigin origin, @NotNull Instant occurredAt, boolean async) {
        super(async);
        this.origin = Objects.requireNonNull(origin, "origin");
        this.occurredAt = Objects.requireNonNull(occurredAt, "occurredAt");
    }

    /**
     * Returns the event origin.
     *
     * @return event origin
     * @since 2.0.0
     */
    public final @NotNull EventOrigin origin() {
        return origin;
    }

    /**
     * Returns the time when this event occurred.
     *
     * @return event time
     * @since 2.0.0
     */
    public final @NotNull Instant occurredAt() {
        return occurredAt;
    }
}