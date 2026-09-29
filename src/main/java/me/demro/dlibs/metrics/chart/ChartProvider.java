package me.demro.dlibs.metrics.chart;

import org.bstats.charts.CustomChart;

/**
 * Поставщик кастомного чарта bStats.
 * Плагины могут регистрировать свои чарты через {@link ChartRegistry}.
 */
@FunctionalInterface
public interface ChartProvider {
    CustomChart create();
}