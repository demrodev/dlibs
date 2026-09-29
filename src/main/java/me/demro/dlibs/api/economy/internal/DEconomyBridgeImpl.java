package me.demro.dlibs.api.economy.internal;

import me.demro.deconomy.api.DEconomyAPI;
import me.demro.dlibs.api.economy.EconomyCurrency;
import me.demro.dlibs.api.economy.EconomyService;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Прямая имплементация {@link EconomyService} поверх {@link DEconomyAPI}.
 *
 * <p>Загружается только когда dEconomy присутствует на сервере —
 * поэтому compile-time зависимость безопасна.</p>
 */
public final class DEconomyBridgeImpl implements EconomyService {

    private final DEconomyAPI api;

    public DEconomyBridgeImpl(@NotNull DEconomyAPI api) {
        this.api = api;
    }

    @Override
    public @NotNull String platformName() {
        return "dEconomy";
    }

    @Override
    public double getBalance(@NotNull OfflinePlayer player, @NotNull String currencyId) {
        return api.getBalance(player, currencyId);
    }

    @Override
    public double getBalance(@NotNull UUID uuid, @NotNull String currencyId) {
        return api.getBalance(uuid, currencyId);
    }

    @Override
    public void setBalance(@NotNull OfflinePlayer player,
                           @NotNull String currencyId,
                           double amount) {
        api.setBalance(player, currencyId, amount);
    }

    @Override
    public boolean deposit(@NotNull OfflinePlayer player,
                           @NotNull String currencyId,
                           double amount) {
        return api.deposit(player, currencyId, amount);
    }

    @Override
    public boolean withdraw(@NotNull OfflinePlayer player,
                            @NotNull String currencyId,
                            double amount) {
        return api.withdraw(player, currencyId, amount);
    }

    @Override
    public boolean has(@NotNull OfflinePlayer player,
                       @NotNull String currencyId,
                       double amount) {
        return api.has(player, currencyId, amount);
    }

    @Override
    public boolean convert(@NotNull OfflinePlayer player,
                           @NotNull String fromCurrencyId,
                           @NotNull String toCurrencyId,
                           double amount) {
        return api.convert(player, fromCurrencyId, toCurrencyId, amount);
    }

    @Override
    public double calculateConversion(@NotNull String fromCurrencyId,
                                      @NotNull String toCurrencyId,
                                      double amount) {
        return api.calculateConversion(fromCurrencyId, toCurrencyId, amount);
    }

    @Override
    public @NotNull List<String> getCurrencyIds() {
        List<String> ids = api.getCurrencyIds();
        return ids != null ? ids : Collections.emptyList();
    }

    @Override
    @Nullable
    public EconomyCurrency getCurrency(@NotNull String id) {
        var currency = api.getCurrency(id);
        if (currency == null) return null;
        return new EconomyCurrency(
                currency.id(),
                currency.displayName(),
                currency.symbol(),
                currency.decimals(),
                currency.startingBalance(),
                currency.maxBalance(),
                currency.transferable(),
                currency.exchangeable()
        );
    }

    @Override
    public boolean currencyExists(@NotNull String id) {
        return api.currencyExists(id);
    }

    @Override
    public @NotNull String format(@NotNull OfflinePlayer player, @NotNull String currencyId) {
        return Objects.requireNonNullElse(api.format(player, currencyId), "");
    }

    @Override
    public @NotNull CompletableFuture<Double> getBalanceAsync(@NotNull UUID uuid,
                                                              @NotNull String currencyId) {
        return api.getBalanceAsync(uuid, currencyId);
    }

    @Override
    public @NotNull CompletableFuture<Boolean> depositAsync(@NotNull UUID uuid,
                                                            @NotNull String currencyId,
                                                            double amount) {
        return api.depositAsync(uuid, currencyId, amount);
    }
}