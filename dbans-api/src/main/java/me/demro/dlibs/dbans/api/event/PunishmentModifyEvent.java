package me.demro.dlibs.dbans.api.event;

import me.demro.dlibs.dbans.api.punishment.Punishment;
import me.demro.dlibs.dbans.api.punishment.PunishmentReason;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public final class PunishmentModifyEvent extends PunishmentEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final PunishmentReason oldReason;
    private final PunishmentReason newReason;
    private final Instant oldExpiresAt;
    private final Instant newExpiresAt;

    public PunishmentModifyEvent(
            @NotNull Punishment punishment,
            @NotNull PunishmentReason oldReason,
            @NotNull PunishmentReason newReason,
            Instant oldExpiresAt,
            Instant newExpiresAt,
            @NotNull EventOrigin origin,
            @NotNull Instant occurredAt,
            boolean async
    ) {
        super(punishment, origin, occurredAt, async);
        this.oldReason = Objects.requireNonNull(oldReason, "oldReason");
        this.newReason = Objects.requireNonNull(newReason, "newReason");
        this.oldExpiresAt = oldExpiresAt;
        this.newExpiresAt = newExpiresAt;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    public @NotNull PunishmentReason oldReason() {
        return oldReason;
    }

    public @NotNull PunishmentReason newReason() {
        return newReason;
    }

    public @NotNull Optional<Instant> oldExpiresAt() {
        return Optional.ofNullable(oldExpiresAt);
    }

    public @NotNull Optional<Instant> newExpiresAt() {
        return Optional.ofNullable(newExpiresAt);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}