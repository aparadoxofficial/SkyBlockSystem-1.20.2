package de.cdnrelaxo.skyblocksystem.util.scoreboard.playerlist;

import de.cdnrelaxo.skyblocksystem.util.scoreboard.ranks.Ranks;
import de.cdnrelaxo.skyblocksystem.util.text.TextColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PlayerTablist {

    public static void setTablist(Player player) {
        setAllPlayersTeam();
    }

    private static void setAllPlayersTeam() {
        Bukkit.getOnlinePlayers().forEach(PlayerTablist::setPlayerTeams);
    }

    private static void setPlayerTeams(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        NumberFormat format = new DecimalFormat("0000");
        for (Ranks ranks : Ranks.values()) {
            Team rank = scoreboard.getTeam(format.format(ranks.getTablistID()) +ranks.getPrefix());
            if (rank == null)
                rank = scoreboard.registerNewTeam( format.format(ranks.getTablistID()) + ranks.getPrefix());
            rank.prefix(ranks.getPrefixForTablist());
            rank.color(NamedTextColor.GRAY);
            rank.suffix(Component.text(" §8[§b1§8]"));
        }
        for (Player target : Bukkit.getOnlinePlayers()) {
            boolean found = false;
            for (Ranks ranks : Ranks.values()) {
                Team rank = scoreboard.getTeam(format.format(ranks.getTablistID()) + ranks.getPrefix());
                if (target.hasPermission(ranks.getPermission())) {
                    assert rank != null;
                    rank.addEntry(target.getName());
                    found = true;
                    break;
                }
            }
            if (!found) {
                Team rank = scoreboard.getTeam(format.format(Ranks.PLAYER.getTablistID()) + Ranks.PLAYER.getPrefix());
                assert rank != null;
                rank.addEntry(target.getName());
            }
        }
    }

    private static void setPlayerList(Player player) {
        int targetPing = player.getPing();


        // player.sendPlayerListHeaderAndFooter(header, footer);
    }
}
