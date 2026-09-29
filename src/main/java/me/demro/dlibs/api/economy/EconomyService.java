package me.demro.dlibs.api.economy;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Абстрактный сервис экономики dEconomy.
 *
 * <p>Получить экземпляр:</p>
 * <pre>{@code
 *   Optional<EconomyService> opt = dLibs.economy();
 *   opt.ifPresent(econ -> econ.deposit(player, "gold", 100));
 * }</pre>
 *
 * <p>Если dEconomy не установлен, {@link me.demro.dlibs.api.economy.EconomyProvider#get()}
 * вернёт {@link java.util.Optional#empty()}.</p>
 */
public interface EconomyService {

    /** Имя провайдера (для логов/диагностики). */
    @NotNull
    String platformName();


    double getBalance(@NotNull OfflinePlayer player, @NotNull String currencyId);

    double getBalance(@NotNull UUID uuid, @NotNull String currencyId);

    void setBalance(@NotNull OfflinePlayer player, @NotNull String currencyId, double amount);

    boolean deposit(@NotNull OfflinePlayer player, @NotNull String currencyId, double amount);

    boolean withdraw(@NotNull OfflinePlayer player, @NotNull String currencyId, double amount);

    boolean has(@NotNull OfflinePlayer player, @NotNull String currencyId, double amount);


    boolean convert(@NotNull OfflinePlayer player,
                    @NotNull String fromCurrencyId,
                    @NotNull String toCurrencyId,
                    double amount);

    double calculateConversion(@NotNull String fromCurrencyId,
                               @NotNull String toCurrencyId,
                               double amount);


    @NotNull
    List<String> getCurrencyIds();

    @Nullable
    EconomyCurrency getCurrency(@NotNull String id);

    boolean currencyExists(@NotNull String id);

    @NotNull
    String format(@NotNull OfflinePlayer player, @NotNull String currencyId);


    @NotNull
    CompletableFuture<Double> getBalanceAsync(@NotNull UUID uuid, @NotNull String currencyId);

    @NotNull
    CompletableFuture<Boolean> depositAsync(@NotNull UUID uuid,
                                            @NotNull String currencyId,
                                            double amount);
}