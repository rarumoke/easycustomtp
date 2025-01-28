package net.harupiza.easycustomtp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Objects;

public class WarpExecutor implements CommandExecutor {
    private final Easycustomtp plugin;

    WarpExecutor(Easycustomtp plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] strings) {
        if (commandSender instanceof Player player) {
            var loc = Objects.requireNonNull(
                    plugin.warpPoints.stream().filter(
                            wp -> wp.name.equals(strings[0])).findFirst().orElse(null)).location;
            if (loc == null) return false;
            player.teleport(loc);
            player.sendMessage(strings[0] + "へテレポートしました！");
            return true;
        }
        return false;
    }
}
