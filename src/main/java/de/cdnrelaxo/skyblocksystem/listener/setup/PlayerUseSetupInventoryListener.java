package de.cdnrelaxo.skyblocksystem.listener.setup;

import de.cdnrelaxo.skyblocksystem.util.text.Messages;
import de.cdnrelaxo.skyblocksystem.util.world.locations.ConfigLocationUtil;
import de.cdnrelaxo.skyblocksystem.util.world.locations.Saved;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class PlayerUseSetupInventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (event.getView().title().equals(Component.text("| ", NamedTextColor.DARK_GRAY).append(Component.text("Einrichten", NamedTextColor.AQUA)))) {
                event.setCancelled(true);
                if (event.getCurrentItem() == null) return;
                if (!event.getCurrentItem().hasItemMeta()) return;
                for (Saved points : Saved.values()) {
                    if (event.getCurrentItem().getItemMeta().displayName().equals(points.getItem().getItemMeta().displayName())) {
                        player.closeInventory();
                        new ConfigLocationUtil(player.getLocation(), points.locationsName()).saveLocation();
                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        player.sendMessage(Messages.PREFIX.get()
                            .append(Component.text("Du hast die Location ", NamedTextColor.GRAY)
                                .append(Component.text("[", NamedTextColor.DARK_GRAY))
                                .append(Component.text(points.getName(), NamedTextColor.AQUA))
                                .append(Component.text("]", NamedTextColor.DARK_GRAY))
                                .append(Component.text(" gesetzt!", NamedTextColor.GRAY))
                            ).decoration(TextDecoration.BOLD, false));
                    }
                }
            }
        }
    }
}
