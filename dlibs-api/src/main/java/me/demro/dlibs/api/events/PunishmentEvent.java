package me.demro.dlibs.api.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.demro.dlibs.api.Punishment;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public abstract class PunishmentEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Punishment punishment;

    @Contract(pure = true)
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}