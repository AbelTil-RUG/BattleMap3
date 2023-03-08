package me.cyclingman.battlemap.gamemodes;

import me.cyclingman.battlemap.mapfeatures.ControlPoint;
import me.cyclingman.battlemap.mapfeatures.MapFeature;
import me.cyclingman.battlemap.ultils.Teams;

import java.util.Arrays;

public class KOTH extends Gamemode {

    @Override
    public void determineWinner() {

    }

    @Override
    public void addSettingsToFeature(MapFeature feature) {
        if (feature instanceof ControlPoint cp) {
            cp.setDefaultOwnerName(Teams.NEUTRAL);
            cp.setAllowedCapturers(Arrays.asList(Teams.RED, Teams.BLU));
        }
    }
}
