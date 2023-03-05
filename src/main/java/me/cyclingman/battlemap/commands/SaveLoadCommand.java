package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.BattleMap;
import me.cyclingman.battlemap.Map;
import me.cyclingman.battlemap.MapCatalog;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SaveLoadCommand implements CommandExecutor {

    private MapCatalog catalog;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0) return false;

        sender.sendMessage(args[0]);

        if (args[0].equalsIgnoreCase("save")) {
            BattleMap.plugin.save();
            sender.sendMessage("Saved successfully");
            return true;
        }

        if (args[0].equalsIgnoreCase("load")) {
            catalog = BattleMap.plugin.load();
            sender.sendMessage("Loaded successfully");
            return true;
        }

        if (args[0].equalsIgnoreCase("create")) {
            BattleMap.plugin.create();
            sender.sendMessage("created successfully");
            return true;
        }

        if (sender instanceof Player p) {
            catalog = BattleMap.plugin.catalog;
            if (args[0].equalsIgnoreCase("add")) {
                catalog.addMap(new Map(args[1], p.getLocation(), catalog));
                sender.sendMessage("added successfully");
                return true;
            }
            if (args[0].equalsIgnoreCase("get")) {
                sender.sendMessage(catalog.getMapNames());
                return true;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                catalog.removeMap(Integer.valueOf(args[1]));
                sender.sendMessage("executed successfully");
                return true;
            }
        }







        return false;
    }
}
