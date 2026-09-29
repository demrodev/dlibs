package me.demro.dlibs;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * Утилита для сравнения версий по схеме major.minor.patch[-suffix].
 */
@UtilityClass
public class VersionComparator {

    /**
     * Проверяет, является ли версия {@code latest} новее, чем {@code current}.
     */
    public boolean isNewer(@NotNull String latest, @NotNull String current) {
        if (latest.equalsIgnoreCase(current)) {
            return false;
        }

        String[] latestParts = normalize(latest).split("\\.");
        String[] currentParts = normalize(current).split("\\.");

        int length = Math.max(latestParts.length, currentParts.length);
        for (int i = 0; i < length; i++) {
            int l = i < latestParts.length ? parseIntSafe(latestParts[i]) : 0;
            int c = i < currentParts.length ? parseIntSafe(currentParts[i]) : 0;
            if (l > c) return true;
            if (l < c) return false;
        }
        return false;
    }

    private String normalize(@NotNull String version) {
        String cleaned = version.trim();
        if (cleaned.startsWith("v") || cleaned.startsWith("V")) {
            cleaned = cleaned.substring(1);
        }
        int dash = cleaned.indexOf('-');
        if (dash != -1) {
            cleaned = cleaned.substring(0, dash);
        }
        return cleaned;
    }

    private int parseIntSafe(@NotNull String value) {
        try {
            String digits = value.replaceAll("\\D", "");
            return digits.isEmpty() ? 0 : Integer.parseInt(digits);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}