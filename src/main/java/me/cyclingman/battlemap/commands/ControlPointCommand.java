package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.BattleMap;
import me.cyclingman.battlemap.Map;
import me.cyclingman.battlemap.mapfeatures.ControlPoint;
import me.cyclingman.battlemap.ultils.Teams;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ControlPointCommand implements CommandExecutor {

    private final BattleMap plugin;

    public ControlPointCommand (BattleMap plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {

            if (args.length < 1) {
                return false;
            }



            if (args[0].equalsIgnoreCase("create")) {
                if (args.length < 4) {
                    return false;
                }

                String mapName = args[1];
                String name = args[2];
                String radius = args[3];
                String captureTime = args[4];

                if (!(StringUtils.isNumeric(radius) || StringUtils.isNumeric(captureTime))) {
                    p.sendMessage("Given value for radius and capture time are not of type integer.");
                    return false;
                }

                Map map = plugin.getCatalog().getMap(mapName);

                if (map == null) {
                    p.sendMessage("Provided name does not match with any map.");
                }

                map.getGamemode().addFeature(new ControlPoint(p.getLocation(), name, Teams.NEUTRAL,
                        new ArrayList<>(List.of(Teams.BLU, Teams.RED)), Integer.parseInt(captureTime), Integer.parseInt(radius)));
                p.sendMessage("Controlpoint successfully created!");
                return true;
            }

            if (args[0].equalsIgnoreCase("modify")) {

            }


        }
        return false;
    }
}
