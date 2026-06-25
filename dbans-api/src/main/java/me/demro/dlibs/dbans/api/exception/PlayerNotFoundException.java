package me.demro.dlibs.dbans.api.exception;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Thrown when a player is not known to dBans.
 *
 * @since 2.0.0
 */
public class PlayerNotFoundException extends DBansException {

    public PlayerNotFoundException(@NotNull UUID playerUuid) {
        super("Player not found: " + playerUuid);
    }

    public PlayerNotFoundException(@NotNull String playerName) {
        super("Player not found: " + playerName);
    }
}