package me.demro.dlibs.dbans.api.spi;

import me.demro.dlibs.dbans.api.event.PunishmentEvent;
import org.jetbrains.annotations.NotNull;

public interface DBansEventDispatcher {

    void callEvent(@NotNull PunishmentEvent event);
}