package me.demro.dlibs.dbans.api.exceptions;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when target player is not found in DBans.
 *
 * @since 1.0.0
 */
public class PlayerNotFoundException extends APIException {

    /**
     * Creates exception for player name.
     *
     * @param playerName name of player that was not found
     * @since 1.0.0
     */
    public PlayerNotFoundException(@NotNull String playerName) {
        super("Player not found: " + playerName);
    }
}