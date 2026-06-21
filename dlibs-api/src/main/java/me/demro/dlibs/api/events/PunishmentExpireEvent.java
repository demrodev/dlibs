package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;

public class PunishmentExpireEvent extends PunishmentEvent {
    public PunishmentExpireEvent(Punishment punishment) {
        super(punishment);
    }
}