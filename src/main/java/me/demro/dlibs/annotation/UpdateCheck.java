package me.demro.dlibs.annotation;

import me.demro.dlibs.model.UpdatePlatform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Помечает класс плагина или метод, для которого нужно проверять обновления.
 * Пример:
 * <pre>
 *   {@code @UpdateCheck(repo = "demro/my-plugin", currentVersion = "1.0.0")}
 *   public class MyPlugin extends JavaPlugin { ... }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UpdateCheck {

    /** Идентификатор проекта (например, "owner/repo" для GitHub) */
    String repo();

    /** Текущая версия плагина */
    String currentVersion();

    /** Платформа для проверки */
    UpdatePlatform platform() default UpdatePlatform.GITHUB;

    /** Интервал проверки в секундах (0 — только при запуске) */
    long interval() default 0L;

    /** Уведомлять ли игроков с правом dlibs.notify в чат */
    boolean notifyPlayers() default false;
}