package net.harupiza.easycustomtp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.io.IOException;

public class WarpEditExecutor implements CommandExecutor {
    private final Easycustomtp plugin;

    public WarpEditExecutor(Easycustomtp plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] strings) {
        if (strings.length != 1) return false;
        if (commandSender instanceof Player player) {
            try {
                plugin.addWarpPoint(strings[0], player.getLocation());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.sendMessage(strings[0] + "を追加しました!");
            return true;
        }
        return false;
    }
}
