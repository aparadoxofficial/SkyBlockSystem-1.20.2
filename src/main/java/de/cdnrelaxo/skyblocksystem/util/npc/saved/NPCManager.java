package de.cdnrelaxo.skyblocksystem.util.npc.saved;

import de.cdnrelaxo.skyblocksystem.SkyBlockSystem;
import de.cdnrelaxo.skyblocksystem.util.npc.skin.SavedSkins;

public class NPCManager {
    ShopNPC shopNPC;

    public NPCManager() {
    }

    public NPCManager(SkyBlockSystem plugin) {
        // Shop start //
        shopNPC = new ShopNPC("§6§lShop", SavedSkins.SURVIVAL.getTexture(), SavedSkins.SURVIVAL.getSignature());
        shopNPC.create();
        // Shop End //
    }

    public ShopNPC getShopNPC() {
        return shopNPC;
    }
}
