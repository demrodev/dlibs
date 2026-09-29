package me.demro.dlibs;

import lombok.NonNull;
import me.demro.dlibs.annotation.BStats;
import me.demro.dlibs.annotation.UpdateCheck;
import me.demro.dlibs.metrics.BStatsManager;
import me.demro.dlibs.metrics.chart.ChartRegistry;
import me.demro.dlibs.model.UpdatePlatform;
import me.demro.dlibs.model.UpdateResult;
import org.bstats.charts.CustomChart;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Главный фасад библиотеки dLibs.
 */
public record DLibs(JavaPlugin plugin) {

    // ==============================
    //  bStats
    // ==============================

    /**
     * Запускает bStats с указанным ID.
     *
     * @param pluginId ID плагина с <a href="https://bstats.org/what-is-my-plugin-id">bStats</a>
     */
    public void startBStats(int pluginId) {
        BStatsManager.start(plugin, pluginId);
    }

    /**
     * Запускает bStats, читая ID из аннотации {@link BStats} на классе плагина.
     */
    public void startBStatsFromAnnotation() {
        BStats annotation = plugin.getClass().getAnnotation(BStats.class);
        if (annotation == null) {
            plugin.getSLF4JLogger().warn(
                    "Класс {} не помечен @BStats — bStats не будет запущен.",
                    plugin.getClass().getName());
            return;
        }
        startBStats(annotation.pluginId());
    }

    /**
     * Регистрирует кастомный чарт bStats.
     * Можно вызывать до {@link #startBStats(int)} — чарты будут добавлены при старте.
     */
    public void registerChart(@NonNull Supplier<CustomChart> chartSupplier) {
        ChartRegistry.register(chartSupplier);
    }

    /**
     * Останавливает bStats. Вызывайте в {@code onDisable()}.
     */
    public void shutdownBStats() {
        BStatsManager.shutdown();
    }

    // ==============================
    //  Update Checker (существующий код)
    // ==============================

    public CompletableFuture<UpdateResult> checkUpdates(@NonNull String projectId,
                                                        @NonNull String currentVersion) {
        return checkUpdates(UpdatePlatform.GITHUB, projectId, currentVersion);
    }

    public CompletableFuture<UpdateResult> checkUpdates(@NonNull UpdatePlatform platform,
                                                        @NonNull String projectId,
                                                        @NonNull String currentVersion) {
        return UpdateChecker.check(platform, projectId, currentVersion);
    }

    public CompletableFuture<UpdateResult> checkUpdatesAndLog(@NonNull String projectId,
                                                              @NonNull String currentVersion) {
        return checkUpdates(projectId, currentVersion).thenApply(result -> {
            if (result.hasError()) {
                plugin.getSLF4JLogger().warn("Не удалось проверить обновления: {}", result.getError());
            } else if (result.isUpdateAvailable()) {
                plugin.getSLF4JLogger().info("Доступно обновление: {} -> {}",
                        result.getCurrentVersion(), result.getLatestVersion());
                plugin.getSLF4JLogger().info("Скачать: {}", result.getDownloadUrl());
            } else {
                plugin.getSLF4JLogger().info("Плагин обновлён до последней версии.");
            }
            return result;
        });
    }

    public CompletableFuture<UpdateResult> checkFromAnnotation() {
        UpdateCheck annotation = plugin.getClass().getAnnotation(UpdateCheck.class);
        if (annotation == null) {
            return CompletableFuture.failedFuture(
                    new IllegalStateException("Класс " + plugin.getClass().getName()
                            + " не помечен @UpdateCheck"));
        }
        return checkUpdates(annotation.platform(), annotation.repo(), annotation.currentVersion());
    }

    public void checkAsync(@NonNull String projectId,
                           @NonNull String currentVersion,
                           @NonNull Consumer<UpdateResult> callback) {
        checkUpdates(projectId, currentVersion).thenAccept(result ->
                Bukkit.getScheduler().runTask(plugin, () -> callback.accept(result)));
    }
}