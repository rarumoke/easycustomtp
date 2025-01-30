package net.harupiza.easycustomtp;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public final class Easycustomtp extends JavaPlugin {
    ArrayList<WarpPoint> warpPoints = new ArrayList<>();
    DataWriter dataWriter = new DataWriter(this);

    @Override
    public void onEnable() {
        Objects.requireNonNull(getServer().getPluginCommand("warp")).setExecutor(new WarpExecutor(this));
        Objects.requireNonNull(getServer().getPluginCommand("warpedit")).setExecutor(new WarpEditExecutor(this));
        try {
            reloadWarpPoint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWarpPoint(String name, Location location) throws IOException {
        dataWriter.add(name, location);
        reloadWarpPoint();
    }

    public void reloadWarpPoint() throws IOException {
        warpPoints = dataWriter.getAll();
    }
}
