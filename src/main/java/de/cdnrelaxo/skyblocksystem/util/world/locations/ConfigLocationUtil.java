package de.cdnrelaxo.skyblocksystem.util.world.locations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigLocationUtil {
    private Location location;
    private String root;
    File file = new File("plugins//SkyBlockSystem", "locations.yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public ConfigLocationUtil(Location location, String root) {
        this.location = location;
        this.root = root;
    }

    public ConfigLocationUtil(String root) {
        this(null, root);
    }

    public void saveLocation() {
        config.set(root + ".World", location.getWorld().getName());
        config.set(root + ".X", location.getX());
        config.set(root + ".Y", location.getY());
        config.set(root + ".Z", location.getZ());
        config.set(root + ".Yaw", location.getYaw());
        config.set(root + ".Pitch", location.getPitch());
        save();
    }

    public Location loadLocation() {
        if (config.contains(root)) {
            World world = Bukkit.getWorld(config.getString(root + ".World"));
            double x = config.getDouble(root + ".X"),
                    y = config.getDouble(root + ".Y"),
                    z = config.getDouble(root + ".Z");
            float yaw = (float) config.getDouble(root + ".Yaw");
            float pitch = (float) config.getDouble(root + ".Pitch");
            return new Location(world, x, y, z, yaw, pitch);
        } else
            return null;
    }

    private void save() {
        if (!file.exists()) {
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }
}
