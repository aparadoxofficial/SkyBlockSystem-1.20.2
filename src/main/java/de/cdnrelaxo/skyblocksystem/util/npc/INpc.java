package de.cdnrelaxo.skyblocksystem.util.npc;

import org.bukkit.entity.Player;

public interface INpc {

    void create();
    void sendPacket(Player player);
    void removePacket(Player player);
    Integer getID();
}
