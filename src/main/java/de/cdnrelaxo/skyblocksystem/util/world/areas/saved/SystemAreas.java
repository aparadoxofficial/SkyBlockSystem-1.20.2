package de.cdnrelaxo.skyblocksystem.util.world.areas.saved;

import de.cdnrelaxo.skyblocksystem.SkyBlockSystem;
import de.cdnrelaxo.skyblocksystem.util.world.areas.PvPArea;
import de.cdnrelaxo.skyblocksystem.util.world.areas.SpawnArea;
import de.cdnrelaxo.skyblocksystem.util.world.locations.ConfigLocationUtil;
import de.cdnrelaxo.skyblocksystem.util.world.locations.Saved;
import org.bukkit.Location;

public class SystemAreas {
    SpawnArea spawnArea;
    PvPArea pvPArea;

    public SystemAreas() {
    }

    public SystemAreas(SkyBlockSystem plugin) {
        // Spawn Start //
        Location spawnArea1 = new ConfigLocationUtil(Saved.SPAWN_AREA_1.locationsName()).loadLocation();
        Location spawnArea2 = new ConfigLocationUtil(Saved.SPAWN_AREA_2.locationsName()).loadLocation();
        if (spawnArea1 != null && spawnArea2 != null) {
            this.spawnArea = new SpawnArea(spawnArea1, spawnArea2, spawnArea1.getWorld());
        }
        // Spawn Stop //

        // PvP Start //
        Location pvpArea1 = new ConfigLocationUtil(Saved.PVP_AREA_1.locationsName()).loadLocation();
        Location pvpArea2 = new ConfigLocationUtil(Saved.PVP_AREA_2.locationsName()).loadLocation();
        if (pvpArea1 != null && pvpArea2 != null) {
            this.pvPArea = new PvPArea(pvpArea1, pvpArea2, pvpArea2.getWorld());
        }
        // PvP Stop //
    }

    public PvPArea getPvPArea() {
        return pvPArea;
    }

    public SpawnArea getSpawnArea() {
        return spawnArea;
    }

}
