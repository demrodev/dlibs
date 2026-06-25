package me.demro.dlibs.dbans.api;

import me.demro.dlibs.dbans.api.alt.AltService;
import me.demro.dlibs.dbans.api.permission.PermissionService;
import me.demro.dlibs.dbans.api.player.PlayerService;
import me.demro.dlibs.dbans.api.punishment.PunishmentService;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface DBansAPI {

    @NotNull PunishmentService punishments();

    @NotNull PlayerService players();

    @NotNull PermissionService permissions();

    @NotNull AltService alts();

    boolean isAvailable();

    @NotNull String version();

    @NotNull Plugin plugin();
}