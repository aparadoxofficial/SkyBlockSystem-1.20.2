package de.cdnrelaxo.skyblocksystem.listener.player.connection;

import de.cdnrelaxo.skyblocksystem.SkyBlockSystem;
import de.cdnrelaxo.skyblocksystem.util.npc.INpc;
import de.cdnrelaxo.skyblocksystem.util.npc.saved.NPCManager;
import de.cdnrelaxo.skyblocksystem.util.world.locations.ConfigLocationUtil;
import de.cdnrelaxo.skyblocksystem.util.world.locations.Saved;
import de.cdnrelaxo.skyblocksystem.util.world.schemetics.SchematicsLoader;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.joinMessage(null);

        // teleport player to spawn if he is in Spawn World
        Location spawn = new ConfigLocationUtil(Saved.SPAWN.locationsName()).loadLocation();
        if (spawn != null) {
            if (player.getLocation().getWorld().getName().equals(spawn.getWorld().getName()))
                player.teleport(spawn);
        }


        // Show all the NPCs to the Player!
        for (INpc npc : NPCManager.getNpcs())
            npc.sendPacket(player);
    }
}
