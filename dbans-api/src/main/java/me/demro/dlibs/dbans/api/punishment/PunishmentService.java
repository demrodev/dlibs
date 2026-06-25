package me.demro.dlibs.dbans.api.punishment;

import me.demro.dlibs.dbans.api.exception.DBansException;
import me.demro.dlibs.dbans.api.exception.InvalidPunishmentRequestException;
import me.demro.dlibs.dbans.api.exception.PlayerNotFoundException;
import me.demro.dlibs.dbans.api.exception.PunishmentNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Service for creating, revoking, modifying, and querying punishments.
 *
 * @since 2.0.0
 */
public interface PunishmentService {

    /**
     * Creates a punishment.
     *
     * @param request punishment creation request
     * @return future completed with the creation result
     * @throws NullPointerException if {@code request} is {@code null}
     * @apiNote The returned future may complete exceptionally with
     * {@link InvalidPunishmentRequestException} if the request structure is invalid,
     * {@link PlayerNotFoundException} if the target player is unknown to DBans,
     * or another {@link DBansException} if the punishment could not be created.
     * @since 2.0.0
     */
    @NotNull CompletableFuture<PunishmentCreateResult> create(@NotNull PunishmentCreateRequest request);

    /**
     * Revokes a punishment.
     *
     * @param request punishment revocation request
     * @return future completed when the punishment has been revoked
     * @throws NullPointerException if {@code request} is {@code null}
     * @apiNote The returned future may complete exceptionally with
     * {@link InvalidPunishmentRequestException} if the request structure is invalid,
     * {@link PunishmentNotFoundException} if the punishment does not exist,
     * or another {@link DBansException} if the punishment could not be revoked.
     * @since 2.0.0
     */
    @NotNull CompletableFuture<Void> revoke(@NotNull PunishmentRevokeRequest request);

    /**
     * Finds a punishment by ID.
     *
     * @param id punishment ID
     * @return future optional punishment
     * @since 2.0.0
     */
    @NotNull CompletableFuture<Optional<Punishment>> findById(@NotNull PunishmentId id);

    /**
     * Finds punishments matching a query.
     *
     * @param query search query
     * @return future list of punishments
     * @since 2.0.0
     */
    @NotNull CompletableFuture<List<Punishment>> find(@NotNull PunishmentQuery query);

    /**
     * Finds all punishments for a target.
     *
     * @param targetUuid target UUID
     * @return future list of punishments
     * @since 2.0.0
     */
    default @NotNull CompletableFuture<List<Punishment>> findByTarget(@NotNull UUID targetUuid) {
        return find(PunishmentQuery.builder().targetUuid(targetUuid).build());
    }

    /**
     * Finds active punishments for a target.
     *
     * @param targetUuid target UUID
     * @return future list of active punishments
     * @since 2.0.0
     */
    default @NotNull CompletableFuture<List<Punishment>> findActiveByTarget(@NotNull UUID targetUuid) {
        return find(PunishmentQuery.builder().targetUuid(targetUuid).status(PunishmentStatus.ACTIVE).build());
    }

    /**
     * Checks if a target has an active punishment of a given type.
     *
     * @param targetUuid target UUID
     * @param type       punishment type
     * @return future boolean result
     * @since 2.0.0
     */
    @NotNull CompletableFuture<Boolean> hasActive(@NotNull UUID targetUuid, @NotNull PunishmentType type);
}