package me.demro.dlibs.dbans.api.event;

import me.demro.dlibs.dbans.api.punishment.Punishment;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

/**
 * Base class for punishment lifecycle events.
 *
 * @since 2.0.0
 */
public abstract class PunishmentEvent extends DBansEvent {

    private final Punishment punishment;

    protected PunishmentEvent(
            @NotNull Punishment punishment,
            @NotNull EventOrigin origin,
            @NotNull Instant occurredAt,
            boolean async
    ) {
        super(origin, occurredAt, async);
        this.punishment = Objects.requireNonNull(punishment, "punishment");
    }

    /**
     * Returns the punishment in this event.
     *
     * @return punishment
     * @since 2.0.0
     */
    public final @NotNull Punishment punishment() {
        return punishment;
    }
}