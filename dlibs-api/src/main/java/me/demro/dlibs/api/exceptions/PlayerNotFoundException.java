package me.demro.dlibs.api.exceptions;

import org.jetbrains.annotations.NotNull;

public class PlayerNotFoundException extends APIException {

    public PlayerNotFoundException(@NotNull String playerName) {
        super("Player not found: " + playerName);
    }
}