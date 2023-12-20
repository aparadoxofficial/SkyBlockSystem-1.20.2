package de.cdnrelaxo.skyblocksystem;

import com.sk89q.worldedit.WorldEdit;
import de.cdnrelaxo.skyblocksystem.util.Register;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyBlockSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        new Register().initialize(this);
    }

    @Override
    public void onDisable() {
    }
}
