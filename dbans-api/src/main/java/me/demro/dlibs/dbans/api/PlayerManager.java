package me.demro.dlibs.dbans.api;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * Provides player-related information used in punishment decisions.
 *
 * @since 1.0.0
 */
public interface PlayerManager {

    /**
     * Returns IP address stored for player, or {@code null} if not available.
     *
     * @param playerUuid UUID of player
     * @return IP address, or {@code null}
     * @since 1.0.0
     */
    @Contract(pure = true)
    @Nullable String getPlayerIp(@NotNull UUID playerUuid);

    /**
     * Checks whether player has immunity against punishment type.
     *
     * @param playerUuid UUID of player
     * @param type       punishment type to check immunity for
     * @return {@code true} if player is immune
     * @since 1.0.0
     */
    @Contract(pure = true)
    boolean hasImmunity(@NotNull UUID playerUuid, @NotNull PunishmentType type);

    /**
     * Returns priority of player.
     *
     * @param playerUuid UUID of player
     * @return priority value
     * @since 1.0.0
     */
    @Contract(pure = true)
    int getPriority(@NotNull UUID playerUuid);

    /**
     * Checks whether issuer can punish target based on priority.
     *
     * @param issuerUuid UUID of issuer
     * @param targetUuid UUID of target
     * @return {@code true} if issuer can punish target
     * @since 1.0.0
     */
    @Contract(pure = true)
    boolean canPunish(@NotNull UUID issuerUuid, @NotNull UUID targetUuid);

    /**
     * Returns UUIDs of alt accounts having the same IP as the player.
     *
     * <p>Be aware that the result can't be trusted since VPNs exist.</p>
     *
     * @param playerUuid UUID of player
     * @return list of UUIDs of accounts, never {@code null}
     * @since 1.0.2
     */
    @Contract(pure = true)
    @NotNull List<UUID> getAltAccounts(@NotNull UUID playerUuid);
}