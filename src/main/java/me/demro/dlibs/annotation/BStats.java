package me.demro.dlibs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Помечает класс плагина для автоматического подключения bStats.
 *
 * <pre>{@code
 *   @BStats(pluginId = 12345)
 *   public class MyPlugin extends JavaPlugin { ... }
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BStats {

    /** ID плагина с bstats.org (<a href="https://bstats.org/what-is-my-plugin-id">bStats</a>) */
    int pluginId();

    /** Отправлять ли статистику только если плагин включён в bStats config.yml */
    boolean respectGlobalConfig() default true;
}