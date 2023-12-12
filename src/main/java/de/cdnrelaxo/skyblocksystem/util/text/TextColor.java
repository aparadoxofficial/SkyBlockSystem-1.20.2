package de.cdnrelaxo.skyblocksystem.util.text;

import net.md_5.bungee.api.ChatColor;

public enum TextColor {

    DARKER_BLUE("#0D2CA6"),
    BLUE_2("#113BDF"),
    BLUE_3("#0738FB"),
    NOVA_RED("#FE2517"),
    WHITE_BLUE("#C1CBF4"),
    WHITE("#FFFFFF"),
    WHITE_GREEN("#BAFADE"),
    GREEN_2("#48E4A0"),
    GREEN_3("#2DD58C"),
    TPA_1("#98358F"),
    TPA_2("#A62C9B"),
    TPA_3("#AF2CA3");


    private ChatColor color;
    private String colorString;

    TextColor(String color) {
        this.color = ChatColor.of(color);
        this.colorString = color;
    }

    public ChatColor get() {
        return color;
    }

    public net.kyori.adventure.text.format.TextColor getTextColor() {
        return net.kyori.adventure.text.format.TextColor.fromHexString(colorString);
    }
}
