package me.cyclingman.battlemap.gamemodes;

import me.cyclingman.battlemap.mapfeatures.MapFeature;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
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

    public MapFeature getFeature(String featureName) {
        for (MapFeature feature : features) {
            if (feature.getName().equalsIgnoreCase(featureName)) {
                return feature;
            }
        }
        return null;
    }

    public boolean removeFeature(String featureName) {
        for (MapFeature feature : features) {
            if (feature.getName().equalsIgnoreCase(featureName)) {
                features.remove(feature);
                return true;
            }
        }
        return false;
    }

    public void printFeatures(CommandSender recipient) {
        recipient.sendMessage(ChatColor.BOLD + "Features in map.");
        for(MapFeature feature: features) {
            recipient.sendMessage(" - " + feature.getClass().toString() + ": " + feature.getName());
        }
    }

}
