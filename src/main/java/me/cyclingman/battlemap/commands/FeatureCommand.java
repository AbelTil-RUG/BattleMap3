package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.BattleMap;
import me.cyclingman.battlemap.Map;
import me.cyclingman.battlemap.mapfeatures.MapFeature;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class FeatureCommand implements CommandExecutor {

    private final BattleMap plugin;

    public FeatureCommand(BattleMap plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return false;

        switch (args[0].toLowerCase()) {
            case "delete":
                deleteCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "list":
                listCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "goto":
                gotoCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
        }

        return false;
    }

    private void deleteCommand(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /feature delete <mapName> <featureName>");
        } else {
            Map map = plugin.getCatalog().getMap(args[0]);
            if (map == null) {
                sender.sendMessage(ChatColor.RED + "Map does not exist. Is the name correct?");
                return;
            }
            if (map.getGamemode().removeFeature(args[1])) {
                sender.sendMessage(ChatColor.GREEN + "Feature successfully removed.");
            } else {
                sender.sendMessage(ChatColor.RED + "Feature does not exist. Is the name correct?");
            }
        }
    }

    private void listCommand(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /feature list <mapName>");
        } else {
            Map map = plugin.getCatalog().getMap(args[0]);
            if (map == null) {
                sender.sendMessage(ChatColor.RED + "Map does not exist. Is the name correct?");
                return;
            }
            map.getGamemode().printFeatures(sender);
        }
    }

    private void gotoCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player p) {
            if (args.length != 2) {
                p.sendMessage("Usage: /feature goto <mapName> <featureName>");
            } else {
                Map map = plugin.getCatalog().getMap(args[0]);
                if (map == null) {
                    sender.sendMessage(ChatColor.RED + "Map does not exist. Is the name correct?");
                    return;
                }
                MapFeature feature = map.getGamemode().getFeature(args[1]);
                if (feature == null) {
                    sender.sendMessage(ChatColor.RED + "Feature does not exist. Is the name correct?");
                    return;
                }
                p.teleport(feature.getLocation());
                sender.sendMessage(ChatColor.GREEN + "TP successful.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be executed as a player.");
        }
    }
}
