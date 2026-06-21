package me.demro.dlibs.api.events;

import lombok.Getter;
import me.demro.dlibs.api.Punishment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class PunishmentModifyEvent extends PunishmentEvent {

    private final @NotNull String oldReason;
    private final @NotNull String newReason;
    private final @Nullable Long oldEndTime;
    private final @Nullable Long newEndTime;

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