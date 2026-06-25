package me.demro.dlibs.dbans.api.exception;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PlayerNotFoundException extends DBansException {

    public PlayerNotFoundException(@NotNull UUID playerUuid) {
        super("Player not found: " + playerUuid);
    }

    public PlayerNotFoundException(@NotNull String playerName) {
        super("Player not found: " + playerName);
    }
}