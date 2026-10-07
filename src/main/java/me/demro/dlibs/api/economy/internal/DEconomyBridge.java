package me.demro.dlibs.api.economy.internal;

import me.demro.dlibs.api.economy.EconomyService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

/**
 * Фабрика {@link EconomyService} для dEconomy.
 * <p>
 * Использует <b>только рефлексию</b> — ни одной compile-time ссылки
 * на классы dEconomy. Это критично: dLibs и dEconomy имеют разные
 * ClassLoader'ы, и любой импорт {@code me.demro.deconomy.*} в dLibs
 * приводит к тому, что JVM видит «разные» классы с одинаковыми именами
 * и не находит подходящий конструктор/метод.
 */
public final class DEconomyBridge {

    private static final String PLUGIN_NAME = "dEconomy";
    private static final String API_METHOD = "getApi";

    private DEconomyBridge() {}

    /**
     * Пытается создать сервис. Возвращает {@code null}, если dEconomy
     * не установлен / не включён / API недоступно.
     */
    @Nullable
    public static EconomyService tryCreate() {
        Plugin deconomy = Bukkit.getPluginManager().getPlugin(PLUGIN_NAME);
        if (deconomy == null || !deconomy.isEnabled()) {
            return null;
        }

        try {
            Object api = deconomy.getClass().getMethod(API_METHOD).invoke(deconomy);
            if (api == null) {
                Bukkit.getLogger().warning("[dLibs] dEconomy.getApi() вернул null.");
                return null;
            }

            return new ReflectiveEconomyService(api);

        } catch (NoSuchMethodException e) {
            Bukkit.getLogger().warning("[dLibs] Несовместимая версия dEconomy: метод getApi() не найден.");
            return null;
        } catch (Throwable t) {
            Bukkit.getLogger().warning("[dLibs] Не удалось подключиться к dEconomy API: "
                    + t.getClass().getSimpleName() + ": " + t.getMessage());
            return null;
        }
    }
}