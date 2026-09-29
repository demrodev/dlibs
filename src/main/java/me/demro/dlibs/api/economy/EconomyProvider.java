package me.demro.dlibs.api.economy;

import me.demro.dlibs.api.economy.internal.DEconomyBridge;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Единая точка получения {@link EconomyService}.
 *
 * <p>Обнаружение ленивое: первый вызов {@link #get()} пытается найти dEconomy
 * на сервере. Результат кэшируется. Для повторного поиска (например, при
 * {@code /reload}) используйте {@link #reset()}.</p>
 */
public final class EconomyProvider {

    private static volatile EconomyService service;
    private static volatile boolean initialized;

    private EconomyProvider() {
    }

    /**
     * Возвращает экономический сервис, если dEconomy установлен и работает.
     */
    @NotNull
    public static Optional<EconomyService> get() {
        if (!initialized) {
            synchronized (EconomyProvider.class) {
                if (!initialized) {
                    service = DEconomyBridge.tryCreate();
                    initialized = true;
                }
            }
        }
        return Optional.ofNullable(service);
    }

    /**
     * Возвращает {@code true}, если dEconomy доступен.
     */
    public static boolean isAvailable() {
        return get().isPresent();
    }

    /**
     * Сбрасывает кэш — пригодится, если dEconomy был установлен/перезапущен
     * без рестарта всего сервера.
     */
    public static void reset() {
        synchronized (EconomyProvider.class) {
            initialized = false;
            service = null;
        }
    }
}