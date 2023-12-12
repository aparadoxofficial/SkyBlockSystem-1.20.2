package de.cdnrelaxo.skyblocksystem.util.npc.saved;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.cdnrelaxo.skyblocksystem.util.npc.INpc;
import de.cdnrelaxo.skyblocksystem.util.world.locations.ConfigLocationUtil;
import de.cdnrelaxo.skyblocksystem.util.world.locations.Saved;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R2.CraftServer;
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

public class ShopNPC implements INpc {
    ServerPlayer npcPlayer;
    String displayName;
    String texture;
    String signature;

    public ShopNPC(String displayName, String texture, String signature) {
        this.displayName = displayName;
        this.texture = texture;
        this.signature = signature;
    }

    @Override
    public void create() {
        Location npcLocation = new ConfigLocationUtil(Saved.SHOP.locationsName()).loadLocation();
        if (npcLocation == null) return;

        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel level = ((CraftWorld) npcLocation.getWorld()).getHandle();

        GameProfile npc = new GameProfile(UUID.randomUUID(), displayName);
        npcPlayer = new ServerPlayer(server, level, npc, ClientInformation.createDefault());

        npcPlayer.getGameProfile().getProperties().put("textures", new Property("textures", texture, signature));

        npcPlayer.setPos(npcLocation.getX(), npcLocation.getY(), npcLocation.getZ());
        npcPlayer.setRot(npcLocation.getYaw(), npcLocation.getPitch());
    }

    @Override
    public void sendPacket(Player player) {
        if (npcPlayer == null) return;
        ServerGamePacketListenerImpl ps = ((CraftPlayer) player).getHandle().connection;
        ps.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npcPlayer));
        ps.send(new ClientboundAddEntityPacket(npcPlayer));
        ps.send(new ClientboundRotateHeadPacket(npcPlayer, (byte) ((npcPlayer.getBukkitYaw()%360)*256/360)));
        //used for body movement and vertical head movement
        //ps.send(new ClientboundMoveEntityPacket.Rot(npc.getBukkitEntity().getEntityId(), (byte) ((yaw%360)*256/360), (byte) ((pitch%360)*256/360), true));

        // Add Skin data (Overlays)
        SynchedEntityData data = npcPlayer.getEntityData(); // DataWatcher
        byte bitmask = (byte) (0x01 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40);
        if (!data.get(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE)).equals(bitmask)) {
            data.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), bitmask);
        }
        ps.send(new ClientboundSetEntityDataPacket(npcPlayer.getId(), Objects.requireNonNull(data.getNonDefaultValues())));
    }

    @Override
    public void removePacket(Player player) {
        if (npcPlayer == null) return;
        ServerGamePacketListenerImpl ps = ((CraftPlayer) player).getHandle().connection;
        ps.send(new ClientboundPlayerInfoRemovePacket(Collections.singletonList(npcPlayer.getUUID())));
        // Add Skin data (Overlays)
        SynchedEntityData data = npcPlayer.getEntityData(); // DataWatcher
        // ps.send(new ClientboundSetEntityDataPacket(npc.getId(), data.packDirty()));
    }

    @Override
    public Integer getID() {
        return npcPlayer.getId();
    }
}
