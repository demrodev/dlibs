package me.demro.dlibs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;
import me.demro.dlibs.model.UpdatePlatform;
import me.demro.dlibs.model.UpdateResult;
import me.demro.dlibs.util.HttpUtils;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Основной класс проверки обновлений на поддерживаемых платформах.
 */
@UtilityClass
public class UpdateChecker {

    private final String USER_AGENT = "dLibs-UpdateChecker/1.0";

    /**
     * Универсальная точка входа.
     */
    public CompletableFuture<UpdateResult> check(@NotNull UpdatePlatform platform,
                                                 @NotNull String projectId,
                                                 @NotNull String currentVersion) {
        return switch (platform) {
            case GITHUB -> checkGitHub(projectId, currentVersion);
            case MODRINTH -> checkModrinth(projectId, currentVersion);
            case HANGAR -> checkHangar(projectId, currentVersion);
        };
    }

    /**
     * Проверка обновлений через GitHub Releases.
     *
     * @param repo репозиторий в формате "owner/repo"
     */
    public CompletableFuture<UpdateResult> checkGitHub(@NotNull String repo,
                                                       @NotNull String currentVersion) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String url = "https://api.github.com/repos/" + repo + "/releases/latest";
                String body = HttpUtils.get(url, USER_AGENT);

                JsonObject json = JsonParser.parseString(body).getAsJsonObject();
                String tagName = json.get("tag_name").getAsString();
                String htmlUrl = json.get("html_url").getAsString();

                String latest = tagName.startsWith("v") ? tagName.substring(1) : tagName;
                boolean updateAvailable = VersionComparator.isNewer(latest, currentVersion);

                return UpdateResult.builder()
                        .updateAvailable(updateAvailable)
                        .latestVersion(latest)
                        .currentVersion(currentVersion)
                        .downloadUrl(htmlUrl)
                        .platform(UpdatePlatform.GITHUB)
                        .build();
            } catch (Exception e) {
                return buildError(UpdatePlatform.GITHUB, currentVersion, e);
            }
        });
    }

    /**
     * Проверка обновлений через Modrinth API.
     *
     * @param projectId slug или ID проекта на Modrinth
     */
    public CompletableFuture<UpdateResult> checkModrinth(@NotNull String projectId,
                                                         @NotNull String currentVersion) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String url = "https://api.modrinth.com/v2/project/" + projectId + "/version";
                String body = HttpUtils.get(url, USER_AGENT);

                var versions = JsonParser.parseString(body).getAsJsonArray();
                if (versions.isEmpty()) {
                    throw new IllegalStateException("Нет доступных версий на Modrinth");
                }

                JsonObject latestObj = versions.get(0).getAsJsonObject();
                String latest = latestObj.get("version_number").getAsString();
                String downloadUrl = "https://modrinth.com/project/" + projectId;

                boolean updateAvailable = VersionComparator.isNewer(latest, currentVersion);

                return UpdateResult.builder()
                        .updateAvailable(updateAvailable)
                        .latestVersion(latest)
                        .currentVersion(currentVersion)
                        .downloadUrl(downloadUrl)
                        .platform(UpdatePlatform.MODRINTH)
                        .build();
            } catch (Exception e) {
                return buildError(UpdatePlatform.MODRINTH, currentVersion, e);
            }
        });
    }

    /**
     * Проверка обновлений через Hangar API.
     *
     * @param projectId идентификатор проекта в формате "author/slug"
     */
    public CompletableFuture<UpdateResult> checkHangar(@NotNull String projectId,
                                                       @NotNull String currentVersion) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String url = "https://hangar.papermc.io/api/v1/projects/" + projectId + "/versions";
                String body = HttpUtils.get(url, USER_AGENT);

                JsonObject json = JsonParser.parseString(body).getAsJsonObject();
                var result = json.getAsJsonArray("result");
                if (result == null || result.isEmpty()) {
                    throw new IllegalStateException("Нет доступных версий на Hangar");
                }

                JsonObject latestObj = result.get(0).getAsJsonObject();
                String latest = latestObj.get("name").getAsString();
                String downloadUrl = "https://hangar.papermc.io/" + projectId;

                boolean updateAvailable = VersionComparator.isNewer(latest, currentVersion);

                return UpdateResult.builder()
                        .updateAvailable(updateAvailable)
                        .latestVersion(latest)
                        .currentVersion(currentVersion)
                        .downloadUrl(downloadUrl)
                        .platform(UpdatePlatform.HANGAR)
                        .build();
            } catch (Exception e) {
                return buildError(UpdatePlatform.HANGAR, currentVersion, e);
            }
        });
    }

    private UpdateResult buildError(UpdatePlatform platform, String currentVersion, Exception e) {
        return UpdateResult.builder()
                .updateAvailable(false)
                .currentVersion(currentVersion)
                .platform(platform)
                .error(e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName())
                .build();
    }
}