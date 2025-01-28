package net.harupiza.easycustomtp;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public final class Easycustomtp extends JavaPlugin {
    ArrayList<WarpPoint> warpPoints = new ArrayList<>();
    DataWriter dataWriter = new DataWriter(this);

    @Override
    public void onEnable() {
        Objects.requireNonNull(getServer().getPluginCommand("warp")).setExecutor(new WarpExecutor(this));
        Objects.requireNonNull(getServer().getPluginCommand("warpedit")).setExecutor(new WarpEditExecutor(this));
    }

    public void addWarpPoint(String name, Location location) {
        dataWriter.add(name, location);
        reloadWarpPoint();
    }

    public void reloadWarpPoint() {
        warpPoints = dataWriter.getAll();
    }
}
