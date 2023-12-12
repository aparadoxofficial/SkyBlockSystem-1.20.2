package de.cdnrelaxo.skyblocksystem.util;

import de.cdnrelaxo.skyblocksystem.SkyBlockSystem;
import de.cdnrelaxo.skyblocksystem.util.npc.saved.NPCManager;
import de.cdnrelaxo.skyblocksystem.util.world.areas.saved.SystemAreas;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Register {

    public void initialize(SkyBlockSystem plugin) {
        PluginManager pluginManager = Bukkit.getPluginManager();

        SystemAreas systemAreas = new SystemAreas(plugin);
        NPCManager npcManager = new NPCManager(plugin);
    }
}
