package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;
import org.jetbrains.annotations.NotNull;

public class PunishmentExpireEvent extends PunishmentEvent {

    public PunishmentExpireEvent(@NotNull Punishment punishment) {
        super(punishment);
    }
}