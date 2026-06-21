package me.demro.dlibs.api.events;

import lombok.Getter;
import me.demro.dlibs.api.Punishment;

@Getter
public class PunishmentModifyEvent extends PunishmentEvent {

    private final String oldReason;
    private final String newReason;
    private final Long oldEndTime;
    private final Long newEndTime;

    public PunishmentModifyEvent(Punishment punishment, String oldReason, String newReason, Long oldEndTime,
                                 Long newEndTime
    ) {
        super(punishment);
        this.oldReason = oldReason;
        this.newReason = newReason;
        this.oldEndTime = oldEndTime;
        this.newEndTime = newEndTime;
    }
}