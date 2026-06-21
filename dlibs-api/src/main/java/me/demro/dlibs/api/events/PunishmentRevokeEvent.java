package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;

public class PunishmentRevokeEvent extends PunishmentEvent {
    public PunishmentRevokeEvent(Punishment punishment) {
        super(punishment);
    }
}