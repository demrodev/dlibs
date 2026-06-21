package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;
import org.jetbrains.annotations.NotNull;

public class PunishmentRevokeEvent extends PunishmentEvent {

    public PunishmentRevokeEvent(@NotNull Punishment punishment) {
        super(punishment);
    }
}