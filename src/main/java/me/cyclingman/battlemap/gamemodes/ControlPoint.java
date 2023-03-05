package me.cyclingman.battlemap.gamemodes;

import me.cyclingman.battlemap.ultils.Teams;
import me.cyclingman.battlemap.ultils.ColorCaster;
import org.bukkit.*;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.HashMap;

public class ControlPoint implements Listener {

    private final BossBar bossBar;
    private final ArmorStand point;
    private final String name;
    private Team owner;
    private Team activeCapturer;
    private final Collection<Team> allowedCapturers;
    private int captureScore;
    private final int maxScore;
    private final int maxCapSpeed;
    private final double radius;
    private static final int decaySpeed = 1;

    public ControlPoint(ArmorStand point, String name, Team owner, Collection<Team> allowedCapturers, int maxScore, double radius) {
        this.point = point;
        this.name = name;
        this.owner = owner;
        this.allowedCapturers = allowedCapturers;
        this.captureScore = 0;
        this.maxScore = maxScore;
        this.radius = radius;
        this.maxCapSpeed = 3;
        this.bossBar = Bukkit.createBossBar(ColorCaster.toChatColor(owner.color()) + name, ColorCaster.toBarColor(owner.color()), BarStyle.SOLID);
        bossBar.setVisible(true);
        bossBar.setProgress(0);
    }

    public void removePlayerFromBossbar(Player p) {
        bossBar.removePlayer(p);
    }

    public void addPlayerToBossbar(Player p) {
        bossBar.addPlayer(p);
    }

    public void deactivate() {
        bossBar.removeAll();
    }

    public void processCapture() {
        showParticles(ColorCaster.toColor(owner.color()));
        HashMap<Team, Integer> teamMap = getPlayersOnPointPerTeam();
        Team currentCapturer = getCurrentCapturer(teamMap);
        int teamsOnPoint = teamMap.values().stream().filter(v -> v > 0).toList().size();

        // Ready for a team to capture
        if (captureScore == 0) {
            if (teamsOnPoint == 1 && !currentCapturer.equals(owner)) {
                setActiveCapturer(currentCapturer);
            } else {
                setActiveCapturer(null);
            }
        }

        if (activeCapturer == null) {
            return;
        }

        int playersOnPoint = teamMap.values().stream().mapToInt(Integer::intValue).sum();

        // calculate captureScore update
        if (teamsOnPoint == 0) {
            decayCaptureScore();
        } else if (teamsOnPoint == 1 && teamMap.get(activeCapturer) > 0) {
            increaseCaptureScore(playersOnPoint);
        } else if (teamsOnPoint == 1) {
            decreaseCaptureScore(playersOnPoint);
        } else if (teamsOnPoint >= 2 && teamMap.get(activeCapturer) == 0) {
            decreaseCaptureScore(playersOnPoint);
        }

        // set captured
        if (captureScore >= maxScore) {
            setOwner(activeCapturer);
        }

        bossBar.setProgress(((double) captureScore)/((double)maxScore));
    }

    private void setOwner(Team team) {
        owner = team;
        bossBar.setColor(ColorCaster.toBarColor(owner.color()));
        bossBar.setTitle(ColorCaster.toChatColor(owner.color()) + name);
        captureScore = 0;
    }

    private void setActiveCapturer(Team team) {
        activeCapturer = team;
        if (team == null) {
            bossBar.setColor(ColorCaster.toBarColor(owner.color()));
        } else {
            bossBar.setColor(ColorCaster.toBarColor(team.color()));
        }
    }

    /**
     * Get for all allowed teams the amount of players on the point.
     * @return Hashmap with teams and amount of players per team.
     */
    private HashMap<Team, Integer> getPlayersOnPointPerTeam() {
        Collection<Player> players = point.getLocation()
                .getNearbyPlayers(radius)
                .stream()
                .filter(p -> p.getLocation().distance(point.getLocation()) <= radius)
                .toList();

        HashMap<Team, Integer> teamMap = new HashMap<>();

        for(Team team: allowedCapturers) {
            teamMap.put(team, 0);
        }
        teamMap.putIfAbsent(owner, 0);

        for(Player p: players) {
            for (Team team : teamMap.keySet()) {
                if (Teams.getTeam(p).equals(team)) {
                    teamMap.merge(team, 1, Integer::sum);
                }
            }
        }

        return teamMap;
    }

    /**
     * Get single team currently capturing point.
     * @param teamMap Hashmap with amount of players per team on point.
     * @return The team, or null if there is no or multiple team on point.
     */
    private Team getCurrentCapturer(HashMap<Team, Integer> teamMap) {
        Team capturer = null;
        for (Team t: teamMap.keySet()) {
            if (teamMap.get(t) > 0) {
                if (capturer != null) {
                    return null;
                }
                capturer = t;
            }
        }
        return capturer;
    }

    private void showParticles(Color color) {
        Location location = point.getLocation();
        for (int d = 0; d <= 90; d += 1) {
            Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            particleLoc.setX(location.getX() + Math.cos(d) * radius);
            particleLoc.setZ(location.getZ() + Math.sin(d) * radius);
            location.getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 1, new Particle.DustOptions(color, 1));
        }
    }

    private void increaseCaptureScore(int amountOfPlayers) {
        if (amountOfPlayers > 0) {
            captureScore += Math.min(amountOfPlayers, maxCapSpeed);
        }
    }

    private void decreaseCaptureScore(int amountOfPlayers) {
        if (amountOfPlayers > 0) {
            captureScore -= Math.min(amountOfPlayers, maxCapSpeed);
        }
        if (captureScore < 0) {
            captureScore = 0;
        }
    }

    private void decayCaptureScore() {
        if (captureScore >= decaySpeed) {
            captureScore -= decaySpeed;
        }
    }
}
