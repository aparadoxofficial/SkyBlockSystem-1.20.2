package de.cdnrelaxo.skyblocksystem.util;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import de.cdnrelaxo.skyblocksystem.SkyBlockSystem;
import de.cdnrelaxo.skyblocksystem.commands.SetupCommand;
import de.cdnrelaxo.skyblocksystem.listener.player.connection.PlayerJoinListener;
import de.cdnrelaxo.skyblocksystem.listener.setup.PlayerUseSetupInventoryListener;
import de.cdnrelaxo.skyblocksystem.util.database.MySQL;
import de.cdnrelaxo.skyblocksystem.util.npc.saved.NPCManager;
import de.cdnrelaxo.skyblocksystem.util.world.areas.saved.SystemAreas;
import de.cdnrelaxo.skyblocksystem.util.world.locations.ConfigLocationUtil;
import de.cdnrelaxo.skyblocksystem.util.world.locations.Saved;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class Register {

    public void initialize(SkyBlockSystem plugin) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        new ConfigLocationUtil().saveConfig();

        SystemAreas systemAreas = new SystemAreas(plugin);

        MySQL mySQL = new MySQL();

        pluginManager.registerEvents(new PlayerJoinListener(), plugin);
        pluginManager.registerEvents(new PlayerUseSetupInventoryListener(), plugin);

        Objects.requireNonNull(plugin.getCommand("setup")).setExecutor(new SetupCommand());

        new BukkitRunnable() {

            @Override
            public void run() {
                NPCManager npcManager = new NPCManager(plugin);
            }
        }.runTaskLater(plugin, 20*3);
    }
}
