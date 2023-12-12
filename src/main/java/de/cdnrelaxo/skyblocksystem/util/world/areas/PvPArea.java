package de.cdnrelaxo.skyblocksystem.util.world.areas;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PvPArea extends Area {

    public PvPArea(Location location1, Location location2, World world) {
        this.location1 = location1;
        this.location2 = location2;
        this.world = world;
    }

    @Override
    public boolean isIn(Location location, Area area) {
        // req if the players X is between the locations
        if (location.getWorld() == area.getWorld())
            if (location.getX() > area.getLocation1().getX() && location.getX() < area.getLocation2().getX())
                if (location.getY() > area.getLocation1().getY() && location.getY() < area.getLocation2().getY())
                    return location.getZ() > area.getLocation1().getZ() && location.getZ() < area.getLocation2().getZ();
        return false;
    }

    @Override
    public boolean isIn(Player player, Area area) {
        // req if the players X is between the locations
        if (player.getWorld() == area.getWorld())
            if (player.getLocation().getX() > area.getLocation1().getX() && player.getLocation().getX() < area.getLocation2().getX())
                if (player.getLocation().getY() > area.getLocation1().getY() && player.getLocation().getY() < area.getLocation2().getY())
                    return player.getLocation().getZ() > area.getLocation1().getZ() && player.getLocation().getZ() < area.getLocation2().getZ();
        return false;
    }
}
