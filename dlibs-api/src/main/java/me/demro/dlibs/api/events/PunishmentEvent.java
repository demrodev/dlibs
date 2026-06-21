package me.demro.dlibs.api.events;

import me.demro.dlibs.api.Punishment;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class PunishmentEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Punishment punishment;

    public PunishmentEvent(Punishment punishment) {
        this.punishment = punishment;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Punishment getPunishment() {
        return punishment;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}