package me.cyclingman.battlemap;

import me.cyclingman.battlemap.gamemodes.Gamemode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.List;

public class Map implements Serializable {
    private transient Gamemode gamemode;
    private int id;
    private String name;
    private Location location;
    private transient List<Player> players;

    public Map(String name, Location location, MapCatalog catalog) {
        this.name = name;
        this.location = location;
    }

    public Gamemode getGamemode() {
        return gamemode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
