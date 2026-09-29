package me.demro.dlibs.metrics;

import lombok.Getter;
import me.demro.dlibs.metrics.chart.ChartRegistry;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.CustomChart;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Менеджер bStats.
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 *   // Способ 1 — явный:
 *   BStatsManager.start(this, 12345);
 *
 *   // Способ 2 — после init:
 *   BStatsManager.init(this);
 *   BStatsManager.start(12345);
 * }</pre>
 */
public final class BStatsManager {

    @Getter
    private static Metrics metrics;

    private static JavaPlugin pluginRef;
    private static final AtomicBoolean STARTED = new AtomicBoolean(false);

    private BStatsManager() {
    }

    /**
     * Запоминает плагин для последующих вызовов {@link #start(int)}.
     */
    public static void init(@NotNull JavaPlugin plugin) {
        pluginRef = plugin;
    }

    /**
     * Запускает bStats с явным указанием плагина.
     */
    public static void start(@NotNull JavaPlugin plugin, int pluginId) {
        if (STARTED.getAndSet(true)) {
            plugin.getSLF4JLogger().warn("bStats уже запущен, повторный вызов проигнорирован.");
            return;
        }

        pluginRef = plugin;

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
     * Запускает bStats, используя плагин, сохранённый через {@link #init(JavaPlugin)}.
     * Бросает {@link IllegalStateException}, если плагин не был установлен.
     */
    public static void start(int pluginId) {
        if (pluginRef == null) {
            throw new IllegalStateException(
                    "BStatsManager не инициализирован. Сначала вызовите BStatsManager.init(plugin) " +
                            "или используйте start(JavaPlugin, int).");
        }
        start(pluginRef, pluginId);
    }

    public static boolean isStarted() {
        return STARTED.get();
    }

    public static void shutdown() {
        if (metrics != null) {
            try {
                metrics.shutdown();
            } catch (Exception ignored) {
            }
            metrics = null;
        }
        pluginRef = null;
        STARTED.set(false);
    }
}