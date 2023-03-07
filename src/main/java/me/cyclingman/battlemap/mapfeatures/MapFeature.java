package me.cyclingman.battlemap.mapfeatures;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.Serial;
import java.io.Serializable;

public abstract class MapFeature implements Serializable {

    @Serial
    private static final long serialVersionUID = -1681012206529286330L;
    protected Location location;
    protected transient boolean isActive = false;
    protected String name;
    protected String featureName;

    public MapFeature(Location location, String featureName, String name) {
        this.location = location;
        this.featureName = featureName;
        this.name = name;
    }

    public abstract void activate();

    public abstract void deactivate();

    public abstract void addPlayer(Player p);

    public abstract void removePlayer(Player p);

    public String getFeatureName() {
        return featureName;
    }

    public String getName() {
        return name;
    }
}
