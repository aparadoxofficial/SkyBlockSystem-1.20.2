package de.cdnrelaxo.skyblocksystem.util.world.schemetics;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SchematicsLoader {

    private Location location;
    private ClipboardFormat format;
    private Clipboard clipboard;

    public SchematicsLoader loadIsland(Location location) {
        this.location = location;
        File file = new File("plugins/SkyBlockSystem/schematics", "starter.schem");
        this.format = ClipboardFormats.findByFile(file);
        if (format != null) {
            try {
                clipboard = format.getReader(new FileInputStream(file)).read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public void paste() {
        EditSession session = WorldEdit.getInstance().newEditSessionBuilder().world(new BukkitWorld(location.getWorld())).maxBlocks(Integer.MAX_VALUE).build();
        try {
            Operation operation = new ClipboardHolder(clipboard)
                .createPaste(session)
                .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
                .ignoreAirBlocks(false)
                .build();
            Operations.complete(operation);
            session.close();
        } catch (WorldEditException e) {
            throw new RuntimeException(e);
        }
    }
}
