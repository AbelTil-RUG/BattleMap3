package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.BattleMap;
import me.cyclingman.battlemap.Map;
import me.cyclingman.battlemap.mapfeatures.ControlPoint;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;


public class ControlPointCommand implements CommandExecutor {

    private final BattleMap plugin;

    public ControlPointCommand (BattleMap plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return false;

        switch (args[0].toLowerCase()) {
            case "create":
                createCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
        }

        return false;
    }

    private void createCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player p) {
            if (args.length != 4) {
                sender.sendMessage(ChatColor.RED + "Usage: /controlpoint create <mapName> <featureName> <captureTime> <radius>");
            } else {
                Map map = plugin.getCatalog().getMap(args[0]);
                if (map == null) {
                    sender.sendMessage(ChatColor.RED + "Map does not exist. Is the name correct?");
                    return;
                }
                if (!(StringUtils.isNumeric(args[2]) || StringUtils.isNumeric(args[3]))) {
                    sender.sendMessage(ChatColor.RED + "Usage: /controlpoint create <mapName> <featureName> <captureTime> <radius>");
                    return;
                }
                boolean success = map.getGamemode().addFeature(new ControlPoint(p.getLocation(), args[1], Integer.parseInt(args[2]), Double.parseDouble(args[3])));
                if (success) {
                    sender.sendMessage(ChatColor.GREEN + "Control point successfully created.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Could not create control point, does name already exist?");
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be executed as a player.");
        }
    }
}
