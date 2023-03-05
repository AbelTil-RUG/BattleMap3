package me.cyclingman.battlemap.gamemodes;

import me.cyclingman.battlemap.mapfeatures.MapFeature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Gamemode implements Serializable {
    List<MapFeature> features;
    List<String> teams;

    public Gamemode() {
        this.features = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public void activate() {

    }

    public void deactivate() {

    }

    public void determineWinner() {

    }

    public void addPlayer() {

    }

    public void removePlayer() {

    }
}
