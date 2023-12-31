package de.cdnrelaxo.skyblocksystem.util.scoreboard.creator;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public abstract class ScoreboardBuilder {

    protected final Scoreboard scoreboard;
    protected final Objective objective;
    protected final Player player;

    public ScoreboardBuilder(Player player, String displayName) {
        this.player = player;
        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard()))
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        this.scoreboard = player.getScoreboard();
        if (this.scoreboard.getObjective("display") != null)
            this.scoreboard.getObjective("display").unregister();
        this.objective = this.scoreboard.registerNewObjective("display", "dummy", displayName);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public abstract void createScoreboard();

    public abstract void update();

    public void setDisplayName(String displayName) {
        this.objective.setDisplayName(displayName);
    }

    public void setScore(String content, int score) {
        Team team = getTeamByScore(score);
        if (team == null)
            return;
        team.setPrefix(content);
        showScore(score);
    }

    public void setScore(Component content, int score) {
        Team team = getTeamByScore(score);
        if (team == null)
            return;
        team.prefix(content);
        showScore(score);
    }

    public void removeScore(int score) {
        hideScore(score);
    }

    private EntryName getEntryNameByScore(int score) {
        for (EntryName name : EntryName.values()) {
            if (score == name.getEntry())
                return name;
        }
        return null;
    }

    private Team getTeamByScore(int score) {
        EntryName name = getEntryNameByScore(score);
        if (name == null)
            return null;
        Team team = this.scoreboard.getEntryTeam(name.getEntryName());
        if (team != null)
            return team;
        team = this.scoreboard.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());
        return team;
    }

    private void showScore(int score) {
        EntryName name = getEntryNameByScore(score);
        if (name == null)
            return;
        if (this.objective.getScore(name.getEntryName()).isScoreSet())
            return;
        this.objective.getScore(name.getEntryName()).setScore(score);
    }

    private void hideScore(int score) {
        EntryName name = getEntryNameByScore(score);
        if (name == null)
            return;
        if (!this.objective.getScore(name.getEntryName()).isScoreSet())
            return;
        this.scoreboard.resetScores(name.getEntryName());
    }
}
