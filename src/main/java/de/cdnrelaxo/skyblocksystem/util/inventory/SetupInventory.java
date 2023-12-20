package de.cdnrelaxo.skyblocksystem.util.inventory;

import de.cdnrelaxo.skyblocksystem.util.item.ItemCreator;
import de.cdnrelaxo.skyblocksystem.util.world.locations.Saved;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SetupInventory {

    public static Inventory get() {
        Inventory inventory = Bukkit.createInventory(null, 9*6, Component.text("| ", NamedTextColor.DARK_GRAY).append(Component.text("Einrichten", NamedTextColor.AQUA)));
        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        for (int i = 0; i < Saved.values().length; i++)
            inventory.setItem(i, new ItemStack(Material.AIR));
        for (Saved saved : Saved.values())
            inventory.addItem(saved.getItem());
        return inventory;
    }
}
