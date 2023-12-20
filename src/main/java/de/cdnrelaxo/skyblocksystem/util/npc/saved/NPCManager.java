package de.cdnrelaxo.skyblocksystem.util.npc.saved;

import de.cdnrelaxo.skyblocksystem.SkyBlockSystem;
import de.cdnrelaxo.skyblocksystem.util.npc.INpc;
import de.cdnrelaxo.skyblocksystem.util.npc.skin.SavedSkins;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;

public class NPCManager {
    private static final ArrayList<INpc> npcs = new ArrayList<>();
    ShopNPC shopNPC;

    public NPCManager(SkyBlockSystem plugin) {
        // Shop start //
        shopNPC = new ShopNPC("§6§lShop", Component.text("Shop", NamedTextColor.GOLD).decorate(TextDecoration.BOLD), SavedSkins.SURVIVAL.getTexture(), SavedSkins.SURVIVAL.getSignature());
        npcs.add(shopNPC);
        // Shop End //
    }

    public ShopNPC getShopNPC() {
        return shopNPC;
    }

    public static ArrayList<INpc> getNpcs() {
        return npcs;
    }
}
