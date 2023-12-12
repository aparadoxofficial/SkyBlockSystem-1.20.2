package de.cdnrelaxo.skyblocksystem.util.item;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
        ItemCreator by Mxscha (NovaGames HeadDev):
        Version 1.8.9 - 1.20.2
        How to Use: new ItemCreator(MATERIAL).setName("Name").setLore("Lore").toItemStack();
                                                 |-> you can also add more...         |-> this is required
*/
public class ItemCreator {
    private final ItemStack item;
    private ItemMeta itemMeta;

    // subID = 1.8.9 Builder -> creates the ItemStack
    public ItemCreator(Material material) {
        this.item = new ItemStack(material, 1);
        this.itemMeta = this.item.getItemMeta();
        itemMeta.addItemFlags();
    }

    public ItemCreator setBundleItems(List<ItemStack> items) {
        BundleMeta meta = (BundleMeta) this.itemMeta;
        meta.setItems(items);
        this.itemMeta = meta;
        return this;
    }

    public ItemCreator setCustomModelData(Integer modelData) {
        this.itemMeta.setCustomModelData(modelData);
        return this;
    }

    // adding an Enchantment to your Item
    public ItemCreator addEnchantment(Enchantment enchantment, int amount, boolean ignoreLevel) {
        this.itemMeta.addEnchant(enchantment, amount, ignoreLevel);
        return this;
    }

    // adding the player head texture to the player head -> requires PLAYER_HEAD
    public ItemCreator setSkull(String displayName, Player player, String... lore) {
        SkullMeta skullMeta = (SkullMeta) this.itemMeta;
        skullMeta.setDisplayName(displayName);
        skullMeta.setLore(Arrays.asList(lore));
        skullMeta.setOwningPlayer(Bukkit.getPlayer(player.getUniqueId()));
        this.itemMeta = skullMeta;
        return this;
    }

    // adding the player head texture to the player head -> requires PLAYER_HEAD
    public ItemCreator setSkull(String displayName, String player, String... lore) {
        SkullMeta skullMeta = (SkullMeta) this.itemMeta;
        skullMeta.setDisplayName(displayName);
        skullMeta.setLore(Arrays.asList(lore));
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(player));
        this.itemMeta = skullMeta;
        return this;
    }

    // adding the player head texture from a custom head url -> requires PLAYER_HEAD
    public ItemCreator setSkull(String displayName, URL url, String... lore) {
        PlayerProfile profile = Bukkit.getServer().createPlayerProfile(UUID.randomUUID(), "headprofile");
        PlayerTextures texture = profile.getTextures();
        texture.setSkin(url);
        SkullMeta meta = (SkullMeta) this.itemMeta;
        meta.setOwnerProfile(profile);
        meta.displayName(Component.text(displayName));
        meta.setLore(Arrays.asList(lore));
        this.itemMeta = meta;
        return this;
    }

    // setting the Name of the Item
    public ItemCreator setName(String name) {
        this.itemMeta.displayName(Component.text(name));
        return this;
    }

    // setting the Name of the Item
    public ItemCreator setName(Component name) {
        this.itemMeta.displayName(name);
        return this;
    }

    // setting the Lore of the Item
    public ItemCreator setLore(String... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    // setting the Color of Leather Armor
    public ItemCreator setLeatherArmorColor(Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) this.itemMeta;
        meta.setColor(color);
        return this;
    }

    public ItemCreator setFireworkChargeColor(Color color) {
        FireworkEffectMeta meta = (FireworkEffectMeta) this.itemMeta;
        meta.setEffect(FireworkEffect.builder().withColor(color).build());
        return this;
    }


    // adding a PotionEffect to your Potion -> requires POTION
    public ItemCreator addPotionEffect(PotionEffectType effect, int duration, int amplifier, Color PotionColor) {
        PotionMeta meta = (PotionMeta) this.itemMeta;
        meta.addCustomEffect(new PotionEffect(effect, duration, amplifier), false);
        meta.setColor(PotionColor);
        return this;
    }

    // setting a PotionEffect to your Potion -> requires POTION
    public ItemCreator setPotionEffect(PotionEffectType effect, int duration, int amplifier, Color PotionColor) {
        PotionMeta meta = (PotionMeta) this.itemMeta;
        meta.addCustomEffect(new PotionEffect(effect, duration, amplifier), true);
        meta.setColor(PotionColor);
        this.itemMeta = meta;
        return this;
    }

    public ItemCreator setPotionColor(Color color) {
        PotionMeta meta = (PotionMeta) this.itemMeta;
        meta.setColor(color);
        this.itemMeta = meta;
        return this;
    }

    // setting the item Unbreackable
    public ItemCreator setUnbreakable(boolean isUnbreakable) {
        this.itemMeta.setUnbreakable(isUnbreakable);
        return this;
    }

    // setting the Amount of your Item
    public ItemCreator setAmount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    // adding ItemFlags to your Item
    public ItemCreator addItemFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(itemFlag);
        return this;
    }

    // removing ItemFlags from your Item
    public ItemCreator removeItemFlag(ItemFlag itemFlag) {
        this.itemMeta.removeItemFlags(itemFlag);
        return this;
    }

    // creating the Item
    public ItemStack toItemStack() {
        this.item.setItemMeta(this.itemMeta);
        return this.item;
    }
}
