package me.demro.dlibs.metrics.chart;

import lombok.experimental.UtilityClass;
import org.bstats.charts.CustomChart;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Глобальный реестр кастомных чартов bStats.
 * Плагины добавляют чарты до вызова {@link me.demro.dlibs.metrics.BStatsManager#start(int)}.
 */
@UtilityClass
public class ChartRegistry {

    private final List<Supplier<CustomChart>> CHARTS = new ArrayList<>();

    /**
     * Регистрирует поставщик чарта. Чарт будет создан в момент старта bStats.
     */
    public void register(Supplier<CustomChart> chartSupplier) {
        CHARTS.add(chartSupplier);
    }

    /**
     * Возвращает все зарегистрированные чарты (копию).
     */
    public List<Supplier<CustomChart>> getCharts() {
        return List.copyOf(CHARTS);
    }

    /**
     * Очищает реестр. Вызывается после старта Metrics.
     */
    public void clear() {
        CHARTS.clear();
    }
}