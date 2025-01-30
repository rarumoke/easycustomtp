package net.harupiza.easycustomtp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class WarpExecutor implements CommandExecutor {
    private final Easycustomtp plugin;

    WarpExecutor(Easycustomtp plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] strings) {
        if (commandSender instanceof Player player) {
            var loc = plugin.warpPoints.stream().filter(
                            wp -> wp.name.equals(strings[0])).toList().getLast();
            if (loc == null) {
                player.sendMessage(strings[0] + "は存在しません");
                return false;
            }
            player.teleport(loc.location);
            player.sendMessage(strings[0] + "へテレポートしました！");
            return true;
        }
        return false;
    }
}
