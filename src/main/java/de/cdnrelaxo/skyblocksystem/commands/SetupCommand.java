package de.cdnrelaxo.skyblocksystem.commands;

import de.cdnrelaxo.skyblocksystem.util.inventory.SetupInventory;
import de.cdnrelaxo.skyblocksystem.util.permission.Permissions;
import de.cdnrelaxo.skyblocksystem.util.text.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission(Permissions.SETUP_COMMAND.get())) {
                if (args.length == 0) {
                    player.openInventory(SetupInventory.get());
                }
            } else
                player.sendMessage(Messages.NO_PERMISSION.get());
        }
        return false;
    }
}
