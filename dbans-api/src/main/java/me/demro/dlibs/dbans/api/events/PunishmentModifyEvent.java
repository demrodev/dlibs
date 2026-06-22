package me.demro.dlibs.dbans.api.events;

import lombok.Getter;
import me.demro.dlibs.dbans.api.Punishment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Fired when reason or duration of a punishment is changed.
 *
 * @since 1.0.0
 */
@Getter
public class PunishmentModifyEvent extends PunishmentEvent {

    private final @NotNull String oldReason;
    private final @NotNull String newReason;
    private final @Nullable Long oldEndTime;
    private final @Nullable Long newEndTime;

    /**
     * Creates event carrying state of modified punishment.
     *
     * @param punishment modified punishment
     * @param oldReason  reason before modification
     * @param newReason  reason after modification
     * @param oldEndTime expiration timestamp before modification (ms), or {@code null} if was permanent
     * @param newEndTime expiration timestamp after modification (ms), or {@code null} if now permanent
     * @since 1.0.0
     */
    public PunishmentModifyEvent(@NotNull Punishment punishment, @NotNull String oldReason,
                                 @NotNull String newReason, @Nullable Long oldEndTime,
                                 @Nullable Long newEndTime
    ) {
        super(punishment);
        this.oldReason = oldReason;
        this.newReason = newReason;
        this.oldEndTime = oldEndTime;
        this.newEndTime = newEndTime;
    }
}