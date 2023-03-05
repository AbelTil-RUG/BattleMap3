package me.cyclingman.battlemap.mapfeatures;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.Serializable;

public abstract class MapFeature implements Serializable {

    protected Location location;

    protected transient boolean isActive = false;

    public MapFeature(Location location) {
        this.location = location;
    }

    public void activate() {
        if (isActive) {
            return;
        }
        isActive = true;
    }

    public void deactivate() {
        if (!isActive) {
            return;
        }
        isActive = false;
    }

    public void addPlayer(Player p) {

    }

    public void removePlayer(Player p) {

    }
}
