package me.cyclingman.battlemap.ultils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import java.util.Collection;

public class Teams {
    public static final String BLU = "blu";
    public static final String RED = "red";
    public static final String NEUTRAL = "neutral";

    public static Team getTeam(String teamName) {
        return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
    }

    public static Team getTeam(Player player) {
        return Bukkit.getScoreboardManager().getMainScoreboard().getEntityTeam(player);
    }

    public static Collection<Team> getTeams() {
        return Bukkit.getScoreboardManager().getMainScoreboard().getTeams();
    }

    public static void createTeams() {
        if (getTeam(BLU) == null) {
            Team blue = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(Teams.BLU);
            blue.color(NamedTextColor.AQUA);
            blue.displayName(Component.text("Blu").color(TextColor.color(65, 231, 255)));
        }
        if (getTeam(RED) == null) {
            Team red = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(Teams.RED);
            red.color(NamedTextColor.RED);
            red.displayName(Component.text("Red").color(TextColor.color(255, 0, 0)));
            //Red.displayName(TextComponent.builder().content(Teams.RED).color(TextColor.RED).build());
        }
        if (getTeam(NEUTRAL) == null) {
            Team neutral = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(Teams.NEUTRAL);
            neutral.color(NamedTextColor.WHITE);
            neutral.displayName(Component.text("Neutral").color(TextColor.color(255, 255, 255)));
            //Neutral.displayName((TextComponent.builder().content(Teams.NEUTRAL).color(TextColor.GREEN).build());
        }
    }
}
