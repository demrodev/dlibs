package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;
import org.jetbrains.annotations.NotNull;

public class PunishmentCreateEvent extends PunishmentEvent {

    public PunishmentCreateEvent(@NotNull Punishment punishment) {
        super(punishment);
    }
}