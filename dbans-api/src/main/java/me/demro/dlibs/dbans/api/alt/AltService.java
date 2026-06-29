package me.demro.dlibs.dbans.api.alt;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Service for alternative account detection.
 *
 * @since 2.0.0
 */
public interface AltService {

    /**
     * Finds possible alternative accounts.
     *
     * @param playerUuid player UUID
     * @return future list of possible alt accounts
     * @apiNote Alternative accounts are identified on the basis of assumptions. VPNs can produce false positives.
     * @since 2.0.0
     */
    @NotNull CompletableFuture<List<AltAccount>> findAlts(@NotNull UUID playerUuid);
}