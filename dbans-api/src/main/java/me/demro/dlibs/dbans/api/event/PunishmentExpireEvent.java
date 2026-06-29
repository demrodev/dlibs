package me.demro.dlibs.dbans.api.event;

import me.demro.dlibs.dbans.api.punishment.Punishment;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

/**
 * Fired when a temporary punishment expires.
 *
 * @since 2.0.0
 */
public final class PunishmentExpireEvent extends PunishmentEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public PunishmentExpireEvent(
            @NotNull Punishment punishment,
            @NotNull EventOrigin origin,
            @NotNull Instant occurredAt,
            boolean async
    ) {
        super(punishment, origin, occurredAt, async);
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}