package me.demro.dlibs.dbans.api.event;

import me.demro.dlibs.dbans.api.punishment.Punishment;
import me.demro.dlibs.dbans.api.punishment.PunishmentReason;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * Fired when a punishment reason or expiration time changes.
 *
 * @since 2.0.0
 */
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
            @Nullable Instant oldExpiresAt,
            @Nullable Instant newExpiresAt,
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

    /**
     * @return previous expiration time, or an empty optional if punishment was permanent
     */
    public @NotNull Optional<Instant> oldExpiresAt() {
        return Optional.ofNullable(oldExpiresAt);
    }

    /**
     * @return new expiration time, or an empty optional if punishment is now permanent
     */
    public @NotNull Optional<Instant> newExpiresAt() {
        return Optional.ofNullable(newExpiresAt);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}