package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.mapfeatures.ControlPoint;
import me.cyclingman.battlemap.mapfeatures.MapFeature;
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

    MapFeature controlPoint = null;

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

                String name = args[1];
                String radius = args[2];
                String captureTime = args[3];

                if (!(StringUtils.isNumeric(radius) || StringUtils.isNumeric(captureTime))) {
                    return false;
                }

                controlPoint = new ControlPoint(p.getLocation(), name, Teams.NEUTRAL,
                        new ArrayList<>(List.of(Teams.BLU, Teams.RED)), Integer.parseInt(captureTime), Integer.parseInt(radius));
                p.sendMessage("Controlpoint successfully created!");
                return true;
            }

            if (args[0].equalsIgnoreCase("modify")) {

            }

            if (args[0].equalsIgnoreCase("get")) {

            }

            if (args[0].equalsIgnoreCase("activate")) {
                controlPoint.activate();
                controlPoint.addPlayer(p);
            }

            if (args[0].equalsIgnoreCase("deactivate")) {
                controlPoint.deactivate();
            }
        }
        return false;
    }
}
