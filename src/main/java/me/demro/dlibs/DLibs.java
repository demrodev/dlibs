package me.demro.dlibs;

import lombok.NonNull;
import me.demro.dlibs.annotation.BStats;
import me.demro.dlibs.annotation.UpdateCheck;
import me.demro.dlibs.api.economy.EconomyProvider;
import me.demro.dlibs.api.economy.EconomyService;
import me.demro.dlibs.metrics.BStatsManager;
import me.demro.dlibs.metrics.chart.ChartRegistry;
import me.demro.dlibs.model.UpdatePlatform;
import me.demro.dlibs.model.UpdateResult;
import org.bstats.charts.CustomChart;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Главный фасад библиотеки dLibs.
 */
public record DLibs(JavaPlugin plugin) {

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
     * Возвращает экономический сервис dEconomy, если плагин установлен.
     *
     * <pre>{@code
     *   dLibs.economy().ifPresent(econ ->
     *       econ.deposit(player, "gold", 100));
     * }</pre>
     */
    public @org.jspecify.annotations.NonNull Optional<EconomyService> economy() {
        return EconomyProvider.get();
    }

    /**
     * Возвращает {@code true}, если dEconomy доступен на сервере.
     */
    public boolean hasEconomy() {
        return EconomyProvider.isAvailable();
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

    public CompletableFuture<UpdateResult> checkUpdates(@NotNull String projectId,
                                                        @NotNull String currentVersion) {
        return checkUpdates(UpdatePlatform.GITHUB, projectId, currentVersion);
    }

    public CompletableFuture<UpdateResult> checkUpdates(@NotNull UpdatePlatform platform,
                                                        @NotNull String projectId,
                                                        @NotNull String currentVersion) {
        return UpdateChecker.check(platform, projectId, currentVersion);
    }

    public @org.jspecify.annotations.NonNull CompletableFuture<UpdateResult> checkUpdatesAndLog(@NotNull String projectId,
                                                                                                         @NotNull String currentVersion) {
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