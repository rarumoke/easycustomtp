package net.harupiza.easycustomtp;

import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;

public class DataWriter {
    private Path path;
    private final Easycustomtp pl;

    public DataWriter(Easycustomtp pl) {
        this.pl = pl;
        try {
            path = Paths.get(pl.getDataFolder().toString(), "warppointstore.txt");

            File file = path.toFile();
            file.getParentFile().mkdirs();

            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            pl.getLogger().warning(e.getMessage());
        }
    }

    public void add(String name, Location location) {
        try {
            Files.writeString(path,
                    name + " " + location.getX() + " " + location.getY() + " " + location.getZ() + " " +
                            Objects.requireNonNull(location.getWorld()).getName() + "\n"
                    , StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            pl.getLogger().warning(e.getMessage());
        }
    }

    public ArrayList<WarpPoint> getAll() throws IOException {
        var result = new ArrayList<WarpPoint>();

        for (String line : Files.readAllLines(path)) {
            var s = line.split(" ");

            if (s.length != 5) {
                continue;
            }
            result.add(new WarpPoint(
                    s[0], new Location(pl.getServer().getWorld(s[4]), Double.parseDouble(s[1]),
                    Double.parseDouble(s[2]), Double.parseDouble(s[3]))
            ));
        }

        return result;
    }
}
