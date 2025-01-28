package net.harupiza.easycustomtp;

import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class DataWriter {
    private Path path;
    private final Easycustomtp pl;

    public DataWriter(Easycustomtp pl) {
        this.pl = pl;
        try {
            path = Paths.get(pl.getDataFolder().toString(), "warppointstore.txt");

            File file = path.toFile();
            pl.getLogger().info(file.getParentFile().mkdirs() ? "warppointstore.txt directory created" : "warppointstore.txt directory already exists");

            if (!file.exists()) pl.getLogger().info(file.createNewFile() ? "warppointstore.txt created" : "warppointstore.txt already exists");
        } catch (IOException e) {
            pl.getLogger().warning(e.getMessage());
        }
    }

    public void add(String name, Location location) {
        try {
            Files.writeString(path, MessageFormat.format("{0}\t{1}\t{2}\t{3}\t{4}\n",
                    name, location.getX(), location.getY(), location.getZ(),
                    Objects.requireNonNull(location.getWorld()).getUID().toString())
                    , StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            pl.getLogger().warning(e.getMessage());
        }
    }

    public ArrayList<WarpPoint> getAll() {
        try {
            var result = new ArrayList<WarpPoint>();

            for (String line : Files.readAllLines(path)) {
                var s = line.split("\t");

                if (s.length != 5) continue;
                result.add(new WarpPoint(
                        s[0], new Location(pl.getServer().getWorld(UUID.fromString(s[4])), Double.parseDouble(s[1]),
                        Double.parseDouble(s[2]), Double.parseDouble(s[3]))
                ));
            }

            return result;
        } catch (IOException e) {
            pl.getLogger().warning(e.getMessage());
            return new ArrayList<>();
        }
    }
}
