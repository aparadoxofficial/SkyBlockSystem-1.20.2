package de.cdnrelaxo.skyblocksystem.util.world.areas;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public abstract class Area {
    public Location location1;
    public Location location2;
    public World world;

    public abstract boolean isIn(Player player, Area area);
    public abstract boolean isIn(Location location, Area area);

    public Location getLocation1() {
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }

    public World getWorld() {
        return world;
    }
}
