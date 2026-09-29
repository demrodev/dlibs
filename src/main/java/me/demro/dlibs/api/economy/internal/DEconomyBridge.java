package me.demro.dlibs.api.economy.internal;

import me.demro.dlibs.api.economy.EconomyService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;

/**
 * Фабрика {@link EconomyService}.
 *
 * <p>Рефлексия используется ровно один раз — чтобы создать
 * {@link DEconomyBridgeImpl}, которому передаётся живой экземпляр
 * {@code me.demro.deconomy.api.DEconomyAPI}. Все дальнейшие вызовы
 * идут через прямой interface-вызов, без reflection.</p>
 *
 * <p>Класс {@link DEconomyBridgeImpl} не будет загружен JVM, пока
 * dEconomy не установлен — поэтому {@code NoClassDefFoundError} не возникнет.</p>
 */
public final class DEconomyBridge {

    private static final String PLUGIN_NAME = "dEconomy";
    private static final String API_CLASS = "me.demro.deconomy.api.DEconomyAPI";
    private static final String IMPL_CLASS = "me.demro.dlibs.api.economy.internal.DEconomyBridgeImpl";

    private DEconomyBridge() {
    }

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
            // 1. Получаем живой экземпляр DEconomyAPI из плагина (рефлексия — 1 раз).
            Object api = deconomy.getClass().getMethod("getApi").invoke(deconomy);
            if (api == null) {
                Bukkit.getLogger().warning("[dLibs] dEconomy.getApi() вернул null.");
                return null;
            }

            // 2. Загружаем impl-класс, у которого нет проблем с вызовом invoke —
            //    он вызывает dEconomy API напрямую, компилятор это проверил.
            Class<?> apiClass = Class.forName(API_CLASS, true, deconomy.getClass().getClassLoader());
            Class<?> implClass = Class.forName(IMPL_CLASS, true, DEconomyBridge.class.getClassLoader());

            Constructor<?> ctor = implClass.getDeclaredConstructor(apiClass);
            ctor.setAccessible(true);

            Object impl = ctor.newInstance(api);
            return (EconomyService) impl;

        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().warning("[dLibs] Класс API dEconomy не найден: " + e.getMessage());
            return null;
        } catch (NoSuchMethodException e) {
            Bukkit.getLogger().warning("[dLibs] Несовместимая версия dEconomy: " + e.getMessage());
            return null;
        } catch (Throwable t) {
            Bukkit.getLogger().warning("[dLibs] Не удалось подключиться к dEconomy API: "
                    + t.getClass().getSimpleName() + ": " + t.getMessage());
            return null;
        }
    }
}