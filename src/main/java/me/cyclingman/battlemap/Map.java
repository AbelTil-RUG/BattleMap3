package me.cyclingman.battlemap;

import me.cyclingman.battlemap.gamemodes.Gamemode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Map implements Serializable {
    @Serial
    private static final long serialVersionUID = -1681012206529286330L;
    private Gamemode gamemode;
    private String name;
    private Location location;
    private transient List<Player> players;

    public Map(String name, Location location, Gamemode gamemode) {
        this.name = name;
        this.location = location;
        this.gamemode = gamemode;
    }

    public Gamemode getGamemode() {
        return gamemode;
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
