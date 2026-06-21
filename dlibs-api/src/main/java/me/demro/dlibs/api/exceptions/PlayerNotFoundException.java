package me.demro.dlibs.api.exceptions;

public class PlayerNotFoundException extends APIException {

    public PlayerNotFoundException(String playerName) {
        super("Player not found: " + playerName);
    }
}