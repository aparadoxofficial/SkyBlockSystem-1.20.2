package de.cdnrelaxo.skyblocksystem.util.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum Messages {

    ;

    final Component msg;

    Messages(Component message) {
        this.msg = message;
    }

    public Component getComponent() {
        return msg;
    }

    public String translateToString() {
        return null;
    }
}
