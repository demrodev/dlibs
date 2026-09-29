package me.demro.dlibs.metrics;

import lombok.Getter;
import me.demro.dlibs.metrics.chart.ChartRegistry;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.CustomChart;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Менеджер bStats. Отвечает за инициализацию {@link Metrics} и регистрацию чартов.
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 *   BStatsManager.start(this, 12345);
 * }</pre>
 */
public final class BStatsManager {

    @Getter
    private static Metrics metrics;

    private static final AtomicBoolean STARTED = new AtomicBoolean(false);

    private BStatsManager() {
    }

    /**
     * Запускает bStats для указанного плагина.
     *
     * @param plugin   экземпляр плагина
     * @param pluginId ID плагина с bstats.org
     */
    public static void start(@NotNull JavaPlugin plugin, int pluginId) {
        if (STARTED.getAndSet(true)) {
            plugin.getSLF4JLogger().warn("bStats уже запущен, повторный вызов проигнорирован.");
            return;
        }

        try {
            metrics = new Metrics(plugin, pluginId);
            for (var supplier : ChartRegistry.getCharts()) {
                try {
                    CustomChart chart = supplier.get();
                    if (chart != null) {
                        metrics.addCustomChart(chart);
                    }
                } catch (Exception e) {
                    plugin.getSLF4JLogger().warn("Не удалось зарегистрировать чарт bStats: {}",
                            e.getMessage());
                }
            }
            ChartRegistry.clear();

            plugin.getSLF4JLogger().info("bStats инициализирован (ID: {}).", pluginId);
        } catch (Exception e) {
            plugin.getSLF4JLogger().error("Ошибка при инициализации bStats: {}", e.getMessage());
            STARTED.set(false);
        }
    }

    /**
     * Возвращает {@code true}, если bStats уже запущен.
     */
    public static boolean isStarted() {
        return STARTED.get();
    }

    /**
     * Корректно завершает работу bStats.
     */
    public static void shutdown() {
        if (metrics != null) {
            try {
                metrics.shutdown();
            } catch (Exception ignored) {
            }
            metrics = null;
        }
        STARTED.set(false);
    }
}