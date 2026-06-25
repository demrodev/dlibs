package me.demro.dlibs.dbans.api.permission;

import me.demro.dlibs.dbans.api.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Service for punishment-related priority and immunity checks.
 *
 * @since 2.0.0
 */
public interface PermissionService {

    /**
     * Checks if a player has immunity against a punishment type.
     *
     * @param playerUuid player UUID
     * @param type       punishment type
     * @return future boolean result
     * @since 2.0.0
     */
    @NotNull CompletableFuture<Boolean> hasImmunity(@NotNull UUID playerUuid, @NotNull PunishmentType type);

    /**
     * Returns the priority of a player.
     *
     * @param playerUuid player UUID
     * @return future priority value
     * @since 2.0.0
     */
    @NotNull CompletableFuture<Integer> priority(@NotNull UUID playerUuid);

    /**
     * Checks if an issuer can punish a target.
     *
     * @param issuerUuid issuer UUID
     * @param targetUuid target UUID
     * @return future boolean result
     * @since 2.0.0
     */
    @NotNull CompletableFuture<Boolean> canPunish(@NotNull UUID issuerUuid, @NotNull UUID targetUuid);
}