package de.cdnrelaxo.skyblocksystem.util.world.locations;

import de.cdnrelaxo.skyblocksystem.util.item.ItemCreator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Saved {

    SPAWN("Spawn", "Spawn", "spawn", new ItemCreator(Material.HONEYCOMB).toItemStack(), NamedTextColor.GOLD),
    SHOP("ShopNPC", "Shop","NPCs", new ItemCreator(Material.EGG).toItemStack(), NamedTextColor.GOLD),
    SPAWN_AREA_1("SpawnArea1","Spawn Area Top", "Area", new ItemCreator(Material.STRUCTURE_VOID).toItemStack(), NamedTextColor.GREEN),
    SPAWN_AREA_2("SpawnArea2","Spawn Area Bottom", "Area", new ItemCreator(Material.STRUCTURE_VOID).toItemStack(), NamedTextColor.GREEN),
    PVP_AREA_1("PvPArea1", "PvP Area Top","Area", new ItemCreator(Material.STRUCTURE_VOID).toItemStack(), NamedTextColor.RED),
    PVP_AREA_2("PvPArea2", "PvP Area Bottom","Area", new ItemCreator(Material.STRUCTURE_VOID).toItemStack(), NamedTextColor.RED);

    final String locationsName;
    final String name;
    final String category;
    final ItemStack item;
    final TextColor color;

    Saved(String LocationName, String name, String category, ItemStack item, TextColor color) {
        this.locationsName = LocationName;
        this.name = name;
        this.category = category;
        this.item = item;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItem() {
        return new ItemCreator(item.getType())
            .setName(Component.text("| ", NamedTextColor.DARK_GRAY).append(Component.text(name, NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true)))
            .setLore("§8(§a"+ category + "§8)", "§8| §7Klicke zum setzen!")
            .toItemStack();
    }

    public String locationsName() {
        return locationsName;
    }
}
