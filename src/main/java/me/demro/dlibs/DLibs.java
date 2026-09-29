package me.demro.dlibs;

import lombok.NonNull;
import me.demro.dlibs.annotation.UpdateCheck;
import me.demro.dlibs.model.UpdatePlatform;
import me.demro.dlibs.model.UpdateResult;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * Главный фасад библиотеки dLibs.
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 *   DLibs dLibs = new DLibs(this);
 *   dLibs.checkUpdates("demro/my-plugin", getDescription().getVersion())
 *        .thenAccept(result -> {
 *            if (result.isUpdateAvailable()) {
 *                getLogger().info("Доступна версия " + result.getLatestVersion());
 *            }
 *        });
 * }</pre>
 *
 * <p>Работает на Spigot, Bukkit и Paper (использует только универсальный API).</p>
 */
public record DLibs(JavaPlugin plugin) {

    /**
     * Проверка обновлений без указания платформы — по умолчанию GitHub.
     */
    public CompletableFuture<UpdateResult> checkUpdates(@NonNull String projectId,
                                                        @NonNull String currentVersion) {
        return checkUpdates(UpdatePlatform.GITHUB, projectId, currentVersion);
    }

    /**
     * Проверка обновлений на заданной платформе.
     */
    public CompletableFuture<UpdateResult> checkUpdates(@NonNull UpdatePlatform platform,
                                                        @NonNull String projectId,
                                                        @NonNull String currentVersion) {
        return UpdateChecker.check(platform, projectId, currentVersion);
    }

    /**
     * Проверка обновлений с автоматическим логированием результата.
     * Использует стандартный {@link java.util.logging.Logger} Bukkit.
     */
    public @NotNull CompletableFuture<UpdateResult> checkUpdatesAndLog(@NonNull String projectId,
                                                                       @NonNull String currentVersion) {
        return checkUpdates(projectId, currentVersion).thenApply(result -> {
            if (result.hasError()) {
                plugin.getLogger().log(Level.WARNING,
                        "Не удалось проверить обновления: " + result.getError());
            } else if (result.isUpdateAvailable()) {
                plugin.getLogger().info(
                        "Доступно обновление плагина: " + result.getCurrentVersion()
                                + " -> " + result.getLatestVersion());
                plugin.getLogger().info("Скачать: " + result.getDownloadUrl());
            } else {
                plugin.getLogger().info("Плагин обновлён до последней версии.");
            }
            return result;
        });
    }

    /**
     * Проверка обновлений через аннотацию {@link UpdateCheck}.
     * Аннотация читается с класса плагина.
     */
    public CompletableFuture<UpdateResult> checkFromAnnotation() {
        UpdateCheck annotation = plugin.getClass().getAnnotation(UpdateCheck.class);
        if (annotation == null) {
            return CompletableFuture.failedFuture(
                    new IllegalStateException("Класс " + plugin.getClass().getName()
                            + " не помечен @UpdateCheck"));
        }
        return checkUpdates(annotation.platform(), annotation.repo(), annotation.currentVersion());
    }

    /**
     * Регистрирует callback на завершение проверки обновлений в главном потоке.
     */
    public void checkAsync(@NonNull String projectId,
                           @NonNull String currentVersion,
                           @NonNull Consumer<UpdateResult> callback) {
        checkUpdates(projectId, currentVersion).thenAccept(result ->
                Bukkit.getScheduler().runTask(plugin, () -> callback.accept(result))
        );
    }
}