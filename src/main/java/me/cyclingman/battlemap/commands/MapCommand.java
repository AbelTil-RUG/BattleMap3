package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.BattleMap;
import me.cyclingman.battlemap.Map;
import me.cyclingman.battlemap.gamemodes.KOTH;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MapCommand implements CommandExecutor {

    private final BattleMap plugin;
    public MapCommand(BattleMap plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length < 1) {
                return false;
            }


            if (args[0].equalsIgnoreCase("create")) {
                if (args.length < 2) {
                    return false;
                }

                String mapName = args[1];

                plugin.getCatalog().addMap(new Map(mapName, p.getLocation(), new KOTH()));
                p.sendMessage("Map created.");
                return true;
            }

            if (args[0].equalsIgnoreCase("get")) {
                if (args.length < 2) {
                    return false;
                }

                String mapName = args[1];

                Map map = plugin.getCatalog().getMap(mapName);
                if (map == null) {
                    p.sendMessage("Provided name does not match with any map.");
                    return false;
                }

                map.getGamemode().printFeatures(p);
                return true;
            }

            if (args[0].equalsIgnoreCase("print")) {

                plugin.getCatalog().printMaps(p);
                return true;
            }

            if (args[0].equalsIgnoreCase("remove")) {
                if (args.length < 3) {
                    return false;
                }

                String mapName = args[1];
                String featureIndex = args[2];

                if (!(StringUtils.isNumeric(featureIndex))) {
                    p.sendMessage("Given value for featureIndex is not of type integer.");
                    return false;
                }

                Map map = plugin.getCatalog().getMap(mapName);
                if (map == null) {
                    p.sendMessage("Provided name does not match with any map.");
                    return false;
                }

                map.getGamemode().removeFeature(Integer.parseInt(featureIndex));
                return true;
            }
        }
        return false;
    }
}
