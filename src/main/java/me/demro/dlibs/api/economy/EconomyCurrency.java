package me.demro.dlibs.api.economy;

import lombok.NonNull;

/**
 * DTO валюты dEconomy.
 *
 * <p>Не зависит от классов dEconomy — создаётся через рефлексию внутри
 * {@code DEconomyBridge}.</p>
 *
 * @param id              идентификатор (lowercase)
 * @param displayName     отображаемое имя
 * @param symbol          символ валюты
 * @param decimals        знаков после запятой
 * @param startingBalance стартовый баланс
 * @param maxBalance      максимальный баланс
 * @param transferable    разрешены ли переводы
 * @param exchangeable    разрешён ли обмен
 */
public record EconomyCurrency(
        @NonNull String id,
        @NonNull String displayName,
        @NonNull String symbol,
        int decimals,
        double startingBalance,
        double maxBalance,
        boolean transferable,
        boolean exchangeable
) {
}