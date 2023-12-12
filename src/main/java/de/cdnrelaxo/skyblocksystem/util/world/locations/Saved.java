package de.cdnrelaxo.skyblocksystem.util.world.locations;

public enum Saved {
    SPAWN("Spawn"),
    SHOP("ShopNPC"),
    SPAWN_AREA_1("SpawnArea1"),
    SPAWN_AREA_2("SpawnArea2"),
    PVP_AREA_1("PvPArea1"),
    PVP_AREA_2("PvPArea2");

    final String locationsName;

    Saved(String LocationName) {
        this.locationsName = LocationName;
    }

    public String locationsName() {
        return locationsName;
    }
}
