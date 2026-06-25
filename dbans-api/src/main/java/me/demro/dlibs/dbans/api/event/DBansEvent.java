package me.demro.dlibs.dbans.api.event;

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

public abstract class DBansEvent extends Event {

    private final EventOrigin origin;
    private final Instant occurredAt;

    protected DBansEvent(@NotNull EventOrigin origin, @NotNull Instant occurredAt, boolean async) {
        super(async);
        this.origin = Objects.requireNonNull(origin, "origin");
        this.occurredAt = Objects.requireNonNull(occurredAt, "occurredAt");
    }

    public final @NotNull EventOrigin origin() {
        return origin;
    }

    public final @NotNull Instant occurredAt() {
        return occurredAt;
    }
}