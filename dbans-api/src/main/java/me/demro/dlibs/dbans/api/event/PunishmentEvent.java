package me.demro.dlibs.dbans.api.event;

import me.demro.dlibs.dbans.api.punishment.Punishment;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

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

    public final @NotNull Punishment punishment() {
        return punishment;
    }
}