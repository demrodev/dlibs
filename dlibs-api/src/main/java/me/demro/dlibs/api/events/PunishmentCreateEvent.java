package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;

public class PunishmentCreateEvent extends PunishmentEvent {

    public PunishmentCreateEvent(Punishment punishment) {
        super(punishment);
    }
}