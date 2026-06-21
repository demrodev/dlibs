package me.demro.dlibs.api;

import me.demro.dlibs.api.events.PunishmentEvent;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public interface EventManager {

    void registerListener(@NotNull Listener listener);

    void unregisterListener(@NotNull Listener listener);

    void callEvent(@NotNull PunishmentEvent event);
}