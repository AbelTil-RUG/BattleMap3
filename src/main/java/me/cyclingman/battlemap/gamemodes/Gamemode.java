package me.cyclingman.battlemap.gamemodes;

import me.cyclingman.battlemap.mapfeatures.MapFeature;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Gamemode implements Serializable {

    @Serial
    private static final long serialVersionUID = -1681012206529286330L;
    protected List<MapFeature> features;
    protected List<String> teams;
    protected transient boolean active = false;

    public Gamemode() {
        this.features = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public void activate() {
        if (active) deactivate();
        active = true;
        for (MapFeature feature : features) {
            feature.activate();
        }
    }

    public void deactivate() {
        if (active) {
            active = false;
            for (MapFeature feature : features) {
                feature.deactivate();
            }
        }
    }

    public abstract void determineWinner();

    public abstract void addSettingsToFeature(MapFeature feature);

    public boolean addFeature(MapFeature feature) {
        if (active) deactivate();
        if (getFeature(feature.getName()) == null) {
            features.add(feature);
            addSettingsToFeature(feature);
            return true;
        } else {
            return false;
        }
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
        if (active) deactivate();
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
            List<String> name = Arrays.stream(feature.getClass().toString().split("\\.")).toList();
            recipient.sendMessage(" - " + name.get(name.size() - 1) + ": " + feature.getName());
        }
    }

    public boolean addPlayer(Player player) {
        if (!active) return false;
        for (MapFeature feature : features) {
            feature.addPlayer(player);
        }
        return true;
    }

    public boolean removePlayer(Player player) {
        if (!active) return false;
        for (MapFeature feature : features) {
            feature.removePlayer(player);
        }
        return true;
    }

}
