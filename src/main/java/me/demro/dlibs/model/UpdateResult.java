package me.demro.dlibs.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

/**
 * Результат проверки обновлений.
 */
@Getter
@Builder
@ToString
public class UpdateResult {

    /** Доступно ли обновление */
    private final boolean updateAvailable;

    /** Последняя доступная версия */
    private final String latestVersion;

    /** Текущая версия плагина */
    private final String currentVersion;

    /** Ссылка на страницу загрузки */
    private final String downloadUrl;

    /** Платформа, через которую проводилась проверка */
    private final UpdatePlatform platform;

    /** Текст ошибки, если проверка не удалась */
    private final String error;

    public Optional<String> getErrorOptional() {
        return Optional.ofNullable(error);
    }

    public Optional<String> getLatestVersionOptional() {
        return Optional.ofNullable(latestVersion);
    }

    public Optional<String> getDownloadUrlOptional() {
        return Optional.ofNullable(downloadUrl);
    }

    public boolean hasError() {
        return error != null && !error.isBlank();
    }
}