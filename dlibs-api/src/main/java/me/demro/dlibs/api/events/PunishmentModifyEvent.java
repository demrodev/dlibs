package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;

public class PunishmentModifyEvent extends PunishmentEvent {
    private final String oldReason;
    private final String newReason;
    private final Long oldEndTime;
    private final Long newEndTime;

    public PunishmentModifyEvent(Punishment punishment, String oldReason, String newReason, Long oldEndTime, Long newEndTime) {
        super(punishment);
        this.oldReason = oldReason;
        this.newReason = newReason;
        this.oldEndTime = oldEndTime;
        this.newEndTime = newEndTime;
    }

    public String getOldReason() { return oldReason; }
    public String getNewReason() { return newReason; }
    public Long getOldEndTime() { return oldEndTime; }
    public Long getNewEndTime() { return newEndTime; }
}