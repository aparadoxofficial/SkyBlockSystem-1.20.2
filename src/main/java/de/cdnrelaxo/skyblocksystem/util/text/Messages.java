package de.cdnrelaxo.skyblocksystem.util.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public enum Messages {

    PREFIX(Component.text("SkyBlock", NamedTextColor.GOLD)
        .decorate(TextDecoration.BOLD)
        .append(Component.text(" | ", NamedTextColor.DARK_GRAY)
            .decoration(TextDecoration.BOLD, false))),
    NO_PERMISSION(PREFIX.get().append(Component.text("u dont have permission to do that!", NamedTextColor.RED)));

    final Component msg;

    Messages(Component message) {
        this.msg = message;
    }

    public Component get() {
        return msg;
    }

    public String translateToString() {
        return LegacyComponentSerializer.legacyAmpersand().serialize(msg);
    }
}
