package me.cyclingman.battlemap.gamemodes;

import me.cyclingman.battlemap.mapfeatures.MapFeature;
import org.bukkit.entity.Player;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Gamemode implements Serializable {

    @Serial
    private static final long serialVersionUID = -1681012206529286330L;
    protected List<MapFeature> features;
    protected List<String> teams;
    protected String gamemodeName;

    public Gamemode() {
        this.features = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public abstract void activate();

    public abstract void deactivate();

    public abstract void determineWinner();

    public abstract void addPlayer();

    public abstract void removePlayer();

    public void addFeature(MapFeature feature) {
        features.add(feature);
    }

    public void removeFeature(int index) {
        features.remove(index);
    }

    public void printFeatures(Player recipient) {
        int i = 0;
        recipient.sendMessage("Features in map.");
        for(MapFeature feature: features) {
            i++;
            recipient.sendMessage("(" + i + ") " + feature.getClass().toString() + ": " + feature.getName());
        }
    }

}
