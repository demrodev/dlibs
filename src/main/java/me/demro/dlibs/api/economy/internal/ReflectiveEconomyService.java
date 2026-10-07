package me.demro.dlibs.api.economy.internal;

import me.demro.dlibs.api.economy.EconomyCurrency;
import me.demro.dlibs.api.economy.EconomyService;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Рефлексивная реализация {@link EconomyService} поверх dEconomy API.
 * <p>
 * Хранит объект API как {@link Object} — ни одной прямой ссылки на
 * классы {@code me.demro.deconomy.*}. Все методы вызываются через
 * {@link Method#invoke(Object, Object...)}.
 * <p>
 * Такой подход позволяет использовать dLibs и dEconomy, даже когда
 * они загружены разными ClassLoader'ами.
 */
public final class ReflectiveEconomyService implements EconomyService {

    private final @NotNull Object api;

    private final @NotNull Method mGetBalancePlayer;
    private final @NotNull Method mGetBalanceUuid;
    private final @NotNull Method mSetBalance;
    private final @NotNull Method mDeposit;
    private final @NotNull Method mWithdraw;
    private final @NotNull Method mHas;
    private final @NotNull Method mConvert;
    private final @NotNull Method mCalculateConversion;
    private final @NotNull Method mGetCurrencyIds;
    private final @NotNull Method mGetCurrency;
    private final @NotNull Method mCurrencyExists;
    private final @NotNull Method mFormat;
    private final @NotNull Method mGetBalanceAsync;
    private final @NotNull Method mDepositAsync;

    public ReflectiveEconomyService(@NotNull Object api) throws NoSuchMethodException {
        this.api = api;
        Class<?> apiClass = api.getClass();

        this.mGetBalancePlayer = findMethod(apiClass, "getBalance", OfflinePlayer.class, String.class);
        this.mGetBalanceUuid   = findMethod(apiClass, "getBalance", UUID.class, String.class);
        this.mSetBalance       = findMethod(apiClass, "setBalance", OfflinePlayer.class, String.class, double.class);
        this.mDeposit          = findMethod(apiClass, "deposit", OfflinePlayer.class, String.class, double.class);
        this.mWithdraw         = findMethod(apiClass, "withdraw", OfflinePlayer.class, String.class, double.class);
        this.mHas              = findMethod(apiClass, "has", OfflinePlayer.class, String.class, double.class);
        this.mConvert          = findMethod(apiClass, "convert", OfflinePlayer.class, String.class, String.class, double.class);
        this.mCalculateConversion = findMethod(apiClass, "calculateConversion", String.class, String.class, double.class);
        this.mGetCurrencyIds   = findMethod(apiClass, "getCurrencyIds");
        this.mGetCurrency      = findMethod(apiClass, "getCurrency", String.class);
        this.mCurrencyExists   = findMethod(apiClass, "currencyExists", String.class);
        this.mFormat           = findMethod(apiClass, "format", OfflinePlayer.class, String.class);
        this.mGetBalanceAsync  = findMethod(apiClass, "getBalanceAsync", UUID.class, String.class);
        this.mDepositAsync     = findMethod(apiClass, "depositAsync", UUID.class, String.class, double.class);
    }

    /** Ищет публичный метод с указанными типами параметров. */
    private static @NotNull Method findMethod(@NotNull Class<?> clazz,
                                              @NotNull String name,
                                              @NotNull Class<?>... params) throws NoSuchMethodException {
        return clazz.getMethod(name, params);
    }

    @Override public @NotNull String platformName() { return "dEconomy"; }

    @Override
    public double getBalance(@NotNull OfflinePlayer player, @NotNull String currencyId) {
        try {
            Object r = mGetBalancePlayer.invoke(api, player, currencyId);
            return r instanceof Number n ? n.doubleValue() : 0;
        } catch (Throwable t) {
            warn("getBalance(player)", t);
            return 0;
        }
    }

    @Override
    public double getBalance(@NotNull UUID uuid, @NotNull String currencyId) {
        try {
            Object r = mGetBalanceUuid.invoke(api, uuid, currencyId);
            return r instanceof Number n ? n.doubleValue() : 0;
        } catch (Throwable t) {
            warn("getBalance(uuid)", t);
            return 0;
        }
    }

    @Override
    public void setBalance(@NotNull OfflinePlayer player, @NotNull String currencyId, double amount) {
        try { mSetBalance.invoke(api, player, currencyId, amount); }
        catch (Throwable t) { warn("setBalance", t); }
    }

    @Override
    public boolean deposit(@NotNull OfflinePlayer player, @NotNull String currencyId, double amount) {
        try { return Boolean.TRUE.equals(mDeposit.invoke(api, player, currencyId, amount)); }
        catch (Throwable t) { warn("deposit", t); return false; }
    }

    @Override
    public boolean withdraw(@NotNull OfflinePlayer player, @NotNull String currencyId, double amount) {
        try { return Boolean.TRUE.equals(mWithdraw.invoke(api, player, currencyId, amount)); }
        catch (Throwable t) { warn("withdraw", t); return false; }
    }

    @Override
    public boolean has(@NotNull OfflinePlayer player, @NotNull String currencyId, double amount) {
        try { return Boolean.TRUE.equals(mHas.invoke(api, player, currencyId, amount)); }
        catch (Throwable t) { warn("has", t); return false; }
    }

    @Override
    public boolean convert(@NotNull OfflinePlayer player,
                           @NotNull String fromCurrencyId,
                           @NotNull String toCurrencyId,
                           double amount) {
        try { return Boolean.TRUE.equals(mConvert.invoke(api, player, fromCurrencyId, toCurrencyId, amount)); }
        catch (Throwable t) { warn("convert", t); return false; }
    }

    @Override
    public double calculateConversion(@NotNull String fromCurrencyId,
                                      @NotNull String toCurrencyId,
                                      double amount) {
        try {
            Object r = mCalculateConversion.invoke(api, fromCurrencyId, toCurrencyId, amount);
            return r instanceof Number n ? n.doubleValue() : 0;
        } catch (Throwable t) {
            warn("calculateConversion", t);
            return 0;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull List<String> getCurrencyIds() {
        try {
            Object r = mGetCurrencyIds.invoke(api);
            return r instanceof List<?> list ? (List<String>) list : Collections.emptyList();
        } catch (Throwable t) {
            warn("getCurrencyIds", t);
            return Collections.emptyList();
        }
    }

    @Override
    @Nullable
    public EconomyCurrency getCurrency(@NotNull String id) {
        try {
            Object currency = mGetCurrency.invoke(api, id);
            if (currency == null) return null;
            return convertCurrency(currency);
        } catch (Throwable t) {
            warn("getCurrency", t);
            return null;
        }
    }

    /**
     * Преобразует объект валюты dEconomy в наш {@link EconomyCurrency}
     * через рефлексию — конструктор dEconomy-валюты неизвестен.
     */
    private @Nullable EconomyCurrency convertCurrency(@NotNull Object currency) {
        try {
            Class<?> c = currency.getClass();
            return new EconomyCurrency(
                    callString(c, currency, "id"),
                    callString(c, currency, "displayName"),
                    callString(c, currency, "symbol"),
                    callInt(c, currency, "decimals"),
                    callDouble(c, currency, "startingBalance"),
                    callDouble(c, currency, "maxBalance"),
                    callBoolean(c, currency, "transferable"),
                    callBoolean(c, currency, "exchangeable")
            );
        } catch (Throwable t) {
            return null;
        }
    }

    private @NotNull String callString(@NotNull Class<?> c, @NotNull Object o, @NotNull String name) {
        try { return Objects.requireNonNullElse((String) c.getMethod(name).invoke(o), ""); }
        catch (Throwable t) { return ""; }
    }
    private int callInt(@NotNull Class<?> c, @NotNull Object o, @NotNull String name) {
        try { Object r = c.getMethod(name).invoke(o); return r instanceof Number n ? n.intValue() : 0; }
        catch (Throwable t) { return 0; }
    }
    private double callDouble(@NotNull Class<?> c, @NotNull Object o, @NotNull String name) {
        try { Object r = c.getMethod(name).invoke(o); return r instanceof Number n ? n.doubleValue() : 0; }
        catch (Throwable t) { return 0; }
    }
    private boolean callBoolean(@NotNull Class<?> c, @NotNull Object o, @NotNull String name) {
        try { return Boolean.TRUE.equals(c.getMethod(name).invoke(o)); }
        catch (Throwable t) { return false; }
    }

    @Override
    public boolean currencyExists(@NotNull String id) {
        try { return Boolean.TRUE.equals(mCurrencyExists.invoke(api, id)); }
        catch (Throwable t) { warn("currencyExists", t); return false; }
    }

    @Override
    public @NotNull String format(@NotNull OfflinePlayer player, @NotNull String currencyId) {
        try { return Objects.requireNonNullElse((String) mFormat.invoke(api, player, currencyId), ""); }
        catch (Throwable t) { warn("format", t); return ""; }
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull CompletableFuture<Double> getBalanceAsync(@NotNull UUID uuid, @NotNull String currencyId) {
        try { return (CompletableFuture<Double>) mGetBalanceAsync.invoke(api, uuid, currencyId); }
        catch (Throwable t) { warn("getBalanceAsync", t); return CompletableFuture.completedFuture(0.0); }
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull CompletableFuture<Boolean> depositAsync(@NotNull UUID uuid,
                                                            @NotNull String currencyId,
                                                            double amount) {
        try { return (CompletableFuture<Boolean>) mDepositAsync.invoke(api, uuid, currencyId, amount); }
        catch (Throwable t) { warn("depositAsync", t); return CompletableFuture.completedFuture(false); }
    }

    private void warn(@NotNull String method, @NotNull Throwable t) {
        Bukkit.getLogger().warning("[dLibs] dEconomy." + method + " упал: "
                + t.getClass().getSimpleName() + ": " + t.getMessage());
    }
}