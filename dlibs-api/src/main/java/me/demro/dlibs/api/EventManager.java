package me.demro.dlibs.api;

import me.demro.dlibs.api.events.PunishmentEvent;
import org.bukkit.event.Listener;

public interface EventManager {
    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    void callEvent(PunishmentEvent event);
}