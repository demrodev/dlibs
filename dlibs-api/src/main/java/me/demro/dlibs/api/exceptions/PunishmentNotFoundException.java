package me.demro.dlibs.api.exceptions;

public class PunishmentNotFoundException extends APIException {

    public PunishmentNotFoundException(String id) {
        super("Punishment not found: " + id);
    }
}