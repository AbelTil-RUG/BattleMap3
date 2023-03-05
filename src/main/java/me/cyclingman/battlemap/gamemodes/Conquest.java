package me.cyclingman.battlemap.gamemodes;

import me.cyclingman.battlemap.BattleMap;
import me.cyclingman.battlemap.ultils.Teams;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class Conquest implements GameMode, Listener {

    private boolean active = false;
    private final String CONTROL_POINT_TAG = "conquest_control_point";
    private final String ACTIVE_TAG = "active";
    private final double CAPTURE_RADIUS = 4.5;

    List<ControlPoint> points;

    private BukkitTask task;


    @Override
    public void activate() {
        if (active) {
            return;
        }
        active = true;
        Bukkit.broadcast(Component.text("Starting up!"));
        points = new ArrayList<>();

        List<Entity> entities = Bukkit.getWorld("BattleMap2.0").getEntities().stream()
                .filter(e -> e.getType() == EntityType.ARMOR_STAND &&
                        e.getScoreboardTags().contains(CONTROL_POINT_TAG) &&
                             e.getScoreboardTags().contains(ACTIVE_TAG))
                .toList();

        List<Team> teams = new ArrayList<>();
        teams.add(Teams.getTeam(Teams.BLU));
        teams.add(Teams.getTeam(Teams.RED));
        for (Entity entity : entities) {
            Bukkit.broadcast(Component.text("Creating cp!"));
            Bukkit.getLogger().info(entity.getName());
            points.add(new ControlPoint((ArmorStand) entity, "Point", Teams.getTeam(Teams.NEUTRAL), teams, 20, 6.5));
        }

        task = Bukkit.getScheduler().runTaskTimer(BattleMap.plugin, () -> {
            for (ControlPoint point: points) {
                point.processCapture();
            }
        }, 0, 1);

        Bukkit.getWorld("BattleMap2.0").getPlayers().forEach(player -> points.forEach(point -> point.addPlayerToBossbar(player)));

    }

    @Override
    public void deactive() {
        if (!active) {
            return;
        }
        active = false;
        Bukkit.broadcast(Component.text("Stopping!"));
        for (ControlPoint point: points) {
            point.deactivate();
        }
        task.cancel();
    }
}
