package me.demro.dlibs.dbans.api.event;

import me.demro.dlibs.dbans.api.punishment.Punishment;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

/**
 * Fired when a punishment is revoked.
 *
 * @since 2.0.0
 */
public final class PunishmentRevokeEvent extends PunishmentEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final PunishmentRevocation revocation;

    public PunishmentRevokeEvent(
            @NotNull Punishment punishment,
            @NotNull PunishmentRevocation revocation,
            @NotNull EventOrigin origin,
            @NotNull Instant occurredAt,
            boolean async
    ) {
        super(punishment, origin, occurredAt, async);
        this.revocation = Objects.requireNonNull(revocation, "revocation");
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    public @NotNull PunishmentRevocation revocation() {
        return revocation;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}