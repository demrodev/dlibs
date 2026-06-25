package me.demro.dlibs.dbans.api.punishment;

import org.jetbrains.annotations.NotNull;

/**
 * Result of a successful punishment creation.
 *
 * @param punishment created punishment
 * @since 2.0.0
 */
public record PunishmentCreateResult(@NotNull Punishment punishment) {

}